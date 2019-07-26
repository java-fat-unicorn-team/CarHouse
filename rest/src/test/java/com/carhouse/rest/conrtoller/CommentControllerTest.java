package com.carhouse.rest.conrtoller;

import com.carhouse.model.Comment;
import com.carhouse.service.CommentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CommentControllerTest {

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    private ObjectMapper objectMapper = new ObjectMapper();
    private List<Comment> listComment;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();
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
    void updateComment() throws Exception {
        Comment comment = listComment.get(1);
        mockMvc.perform(put("/carSale/comment")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(comment)))
                .andExpect(status().isOk());
        verify(commentService, times(1)).updateComment(any(Comment.class));
    }

    @Test
    void deleteComment() throws Exception {
        int commentId = 1;
        mockMvc.perform(delete("/carSale/comment/{id}", commentId))
                .andExpect(status().isOk());
        verify(commentService, times(1)).deleteComment(commentId);
    }
}