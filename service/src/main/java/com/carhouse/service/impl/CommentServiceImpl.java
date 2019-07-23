package com.carhouse.service.impl;

import com.carhouse.dao.CommentDao;
import com.carhouse.model.Comment;
import com.carhouse.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The class provides method to manage comment models on service layer.
 * It is realisation of CommentService interface
 *
 * @author Katuranau Maksimilyan
 * @see CommentService
 */
@Service
public class CommentServiceImpl implements CommentService {

    private CommentDao commentDao;

    /**
     * Instantiates a new Comment service.
     *
     * @param commentDao the class is provided CRUD operations for fuel type model.
     */
    @Autowired
    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    /**
     * Gets comments of car sale with provided id.
     *
     * @param carSaleId the car sale id
     * @return the list of car sale comments
     */
    @Override
    public List<Comment> getCarSaleComments(int carSaleId) {
        return commentDao.getCarSaleComments(carSaleId);
    }

    /**
     * Gets comment by id.
     *
     * @param id the comment id
     * @return the comment
     */
    @Override
    public Comment getComment(int id) {
        return commentDao.getComment(id);
    }

    /**
     * Add comment to car sale advertisement.
     * Gets car sale id from comment object
     *
     * @param carSaleId the car sale id
     * @param comment   the comment
     * @return comment id
     */
    @Override
    public Integer addComment(int carSaleId, Comment comment) {
        return commentDao.addComment(carSaleId, comment);
    }

    /**
     * Update comment.
     * Gets id from comment object
     *
     * @param comment the comment
     */
    @Override
    public void updateComment(Comment comment) {
        commentDao.updateComment(comment);
    }

    /**
     * Delete comment by id.
     *
     * @param id the index
     */
    @Override
    public void deleteComment(int id) {
        commentDao.deleteComment(id);
    }
}
