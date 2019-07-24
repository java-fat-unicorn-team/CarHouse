package com.carhouse.service.impl;

import com.carhouse.dao.CommentDao;
import com.carhouse.model.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {

    @Mock
    private CommentDao commentDao;

    @InjectMocks
    private CommentServiceImpl commentService;

    private List<Comment> listComments;

    @BeforeEach
    public void addComments() {
        listComments = new ArrayList<>() {{
            add(new Comment(1, "Kolya", "Very good"));
            add(new Comment(2, "Julia", "Nice"));
            add(new Comment(3, "Nikita", "Good"));
        }};
    }

    @Test
    void getCarSaleComments() {
        int carSaleId = 2;
        when(commentDao.getCarSaleComments(carSaleId)).thenReturn(listComments);
        assertEquals(listComments.size(), commentService.getCarSaleComments(carSaleId).size());
        verify(commentDao, times(1)).getCarSaleComments(carSaleId);
    }

    @Test
    void getComment() {
        int commentId = 2;
        when(commentDao.getComment(commentId)).thenReturn(listComments.get(commentId));
        assertEquals(listComments.get(commentId).getUserName(), commentService.getComment(commentId).getUserName());
        verify(commentDao, times(1)).getComment(commentId);
    }

    @Test
    void getNonExistentComment() {
        int commentId = 2;
        when(commentDao.getComment(commentId)).thenThrow(EmptyResultDataAccessException.class);
        assertThrows(EmptyResultDataAccessException.class, () -> commentService.getComment(commentId));
    }

    @Test
    void addComment() {
        int carSaleId = 3;
        Comment comment = new Comment(5, "Sasha", "Cool");
        commentService.addComment(carSaleId, comment);
        verify(commentDao, times(1)).addComment(carSaleId, comment);
    }

    @Test
    void addCommentWithWrongReference() {
        int carSaleId = 7;
        Comment comment = new Comment(5, "Pasha", "Good");
        when(commentDao.addComment(carSaleId, comment)).thenThrow(DataIntegrityViolationException.class);
        assertThrows(DataIntegrityViolationException.class, () -> commentService.addComment(carSaleId, comment));
    }

    @Test
    void updateComment() {
        Comment comment = new Comment(5, "Masha", "Very good");
        commentService.updateComment(comment);
        verify(commentDao, times(1)).updateComment(comment);
    }

    @Test
    void deleteComment() {
        int commentId = 2;
        commentService.deleteComment(commentId);
        verify(commentDao, times(1)).deleteComment(commentId);
    }
}
