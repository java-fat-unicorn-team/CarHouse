package com.carhouse.dao.impl;

import com.carhouse.dao.CommentDao;
import com.carhouse.model.Comment;
import config.TestConfig;
import config.TestSpringJDBCConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class, TestSpringJDBCConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CommentDaoImplTest {

    private CommentDao commentDao;

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
    }

    @Test
    void getNonExistentComment() {
        assertThrows(EmptyResultDataAccessException.class, () -> commentDao.getComment(9));
    }

    @Test
    void addComment() {
        int size = commentDao.getCarSaleComments(4).size();
        Comment newComment = new Comment(4, "David", "Good");
        int index = commentDao.addComment(4, newComment);
        Comment obtainedComment = commentDao.getComment(index);
        assertEquals(size + 1, commentDao.getCarSaleComments(4).size());
        assertEquals(newComment.getUserName(), obtainedComment.getUserName());
    }

    @Test
    void addCommentWithWrongReference() {
        assertThrows(DataIntegrityViolationException.class, () -> commentDao.addComment(9,
                new Comment(2, "Vasya", "Nice car")));
    }

    @Test
    void updateComment() {
        Comment newComment = new Comment(2, "David", "Very Good");
        commentDao.updateComment(newComment);
        Comment obtainedComment = commentDao.getComment(2);
        assertEquals(newComment.getComment(), obtainedComment.getComment());
        assertEquals(newComment.getUserName(), obtainedComment.getUserName());
    }

    @Test
    void deleteComment() {
        int size = commentDao.getCarSaleComments(4).size();
        commentDao.deleteComment(3);
        assertEquals(size- 1, commentDao.getCarSaleComments(4).size());
        assertThrows(EmptyResultDataAccessException.class, () -> commentDao.getComment(3));
    }
}
