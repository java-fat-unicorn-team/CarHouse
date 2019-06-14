package com.spring.rest.dao.impl;

import com.spring.rest.dao.CommentDao;
import com.spring.rest.model.CarSale;
import com.spring.rest.model.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.junit.jupiter.api.Assertions.*;

@SpringTestConfiguration
class CommentDaoImplTest {

    CommentDao commentDao;

    @Autowired
    CommentDaoImplTest(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Test
    void getCarSaleComments() {
        assertEquals(2, commentDao.getCarSaleComments(4).size());
    }

    @Test
    void getComment() {
        assertEquals("Good car", commentDao.getComment(1).getComment());
        assertEquals("Alex", commentDao.getComment(2).getUserName());
        assertEquals(4, commentDao.getComment(3).getCarSale().getCarSaleId());
    }

    @Test
    void getNonExistentComment() {
        assertThrows(EmptyResultDataAccessException.class, () -> commentDao.getComment(9));
    }

    @Test
    void addComment() {
        int size = commentDao.getCarSaleComments(4).size();
        int index = commentDao.addComment(new Comment(4, "David", "Good",
                new CarSale(4)));
        assertEquals(size + 1, commentDao.getCarSaleComments(4).size());
        assertEquals("David", commentDao.getComment(index).getUserName());
        assertEquals(4, commentDao.getComment(index).getCarSale().getCarSaleId());
    }

    @Test
    void addCommentWithWrongReference() {
        assertThrows(DataIntegrityViolationException.class, () -> commentDao.addComment(new Comment(2,
                "Vasya", "Nice car", new CarSale(9))));
    }

    @Test
    void updateComment() {
        commentDao.updateComment(new Comment(2, "David", "Very Good",
                new CarSale(4)));
        assertEquals("Very Good", commentDao.getComment(2).getComment());
        assertEquals("David", commentDao.getComment(2).getUserName());
        assertEquals(1, commentDao.getComment(2).getCarSale().getCarSaleId());
    }

    @Test
    void deleteComment() {
        int size = commentDao.getCarSaleComments(4).size();
        commentDao.deleteComment(3);
        assertEquals(size- 1, commentDao.getCarSaleComments(4).size());
        assertThrows(EmptyResultDataAccessException.class, () -> commentDao.getComment(3));
    }
}
