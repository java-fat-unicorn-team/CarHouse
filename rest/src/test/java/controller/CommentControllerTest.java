package controller;

import com.carhouse.model.Comment;
import com.carhouse.rest.handler.RestExceptionHandler;
import com.carhouse.service.exception.NotFoundException;
import com.carhouse.service.exception.WrongReferenceException;
import config.RestTestConfig;
import com.carhouse.rest.controller.CommentController;
import com.carhouse.service.CommentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = RestTestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CommentControllerTest {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentController commentController;

    @Autowired
    private RestExceptionHandler restExceptionHandler;

    private ObjectMapper objectMapper = new ObjectMapper();
    private List<Comment> listComment;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(commentController)
                .setControllerAdvice(restExceptionHandler)
                .build();
        listComment = new ArrayList<>() {{
            add(new Comment(1, "Vova", "Good"));
            add(new Comment(2, "Sasha", "Nice"));
            add(new Comment(3, "David", "Cool"));
        }};
    }

    @Test
    void getComments() throws Exception {
        int carSaleId = 1;
        when(commentService.getCarSaleComments(carSaleId)).thenReturn(listComment);
        mockMvc.perform(get("/carSale/{carSaleId}/comment", carSaleId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        verify(commentService, times(1)).getCarSaleComments(carSaleId);
    }

    @Test
    void addComment() throws Exception {
        int carSaleId = 1;
        Comment comment = listComment.get(1);
        when(commentService.addComment(anyInt(), any(Comment.class))).thenReturn(comment.getCommentId());
        mockMvc.perform(post("/carSale/{carSaleId}/comment", carSaleId)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(comment)))
                .andExpect(status().isCreated());
        verify(commentService, times(1)).addComment(anyInt(), any(Comment.class));
    }

    @Test
    void addCommentWithWrongReference() throws Exception {
        int carSaleId = 1;
        Comment comment = listComment.get(1);
        when(commentService.addComment(anyInt(), any(Comment.class))).thenThrow(WrongReferenceException.class);
        mockMvc.perform(post("/carSale/{carSaleId}/comment", carSaleId)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(comment)))
                .andExpect(status().isFailedDependency());
        verify(commentService, times(1)).addComment(anyInt(), any(Comment.class));
    }

    @Test
    void updateComment() throws Exception {
        Comment comment = listComment.get(1);
        when(commentService.updateComment(any(Comment.class))).thenReturn(true);
        mockMvc.perform(put("/carSale/comment")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(comment)))
                .andExpect(status().isOk());
        verify(commentService, times(1)).updateComment(any(Comment.class));
    }

    @Test
    void updateNotExistComment() throws Exception {
        Comment comment = listComment.get(1);
        when(commentService.updateComment(any(Comment.class))).thenThrow(NotFoundException.class);
        mockMvc.perform(put("/carSale/comment")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(comment)))
                .andExpect(status().isNotFound());
        verify(commentService, times(1)).updateComment(any(Comment.class));
    }

    @Test
    void deleteComment() throws Exception {
        int commentId = 1;
        when(commentService.deleteComment(commentId)).thenReturn(true);
        mockMvc.perform(delete("/carSale/comment/{id}", commentId))
                .andExpect(status().isOk());
        verify(commentService, times(1)).deleteComment(commentId);
    }

    @Test
    void deleteNotExistCar() throws Exception {
        int commentId = 1;
        when(commentService.deleteComment(commentId)).thenThrow(NotFoundException.class);
        mockMvc.perform(delete("/carSale/comment/{id}", commentId))
                .andExpect(status().isNotFound());
        verify(commentService, times(1)).deleteComment(commentId);
    }
}