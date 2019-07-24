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
        int id = 2;
        when(commentDao.getComment(id)).thenReturn(listComments.get(id));
        assertEquals(listComments.get(id).getUserName(), commentService.getComment(id).getUserName());
        verify(commentDao, times(1)).getComment(id);
    }

    @Test
    void getNonExistentComment() {
        when(commentDao.getComment(5)).thenThrow(EmptyResultDataAccessException.class);
        assertThrows(EmptyResultDataAccessException.class, () -> commentService.getComment(5));
    }

    @Test
    void addComment() {
        Comment comment = new Comment(5, "Sasha", "Cool");
        commentService.addComment(3, comment);
        verify(commentDao, times(1)).addComment(3, comment);
    }

    @Test
    void addCommentWithWrongReference() {
        Comment comment = new Comment(5, "Pasha", "Good");
        when(commentDao.addComment(7, comment)).thenThrow(DataIntegrityViolationException.class);
        assertThrows(DataIntegrityViolationException.class, () -> commentService.addComment(7, comment));
    }

    @Test
    void updateComment() {
        Comment comment = new Comment(5, "Masha", "Very good");
        commentService.updateComment(comment);
        verify(commentDao, times(1)).updateComment(comment);
    }

    @Test
    void deleteComment() {
        commentService.deleteComment(2);
        verify(commentDao, times(1)).deleteComment(2);
    }
}
