package com.carhouse.service.impl;

import com.carhouse.dao.CarSaleDao;
import com.carhouse.dao.CommentDao;
import com.carhouse.model.Comment;
import com.carhouse.service.CommentService;
import com.carhouse.service.exception.WrongReferenceException;
import javassist.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
    private CarSaleDao carSaleDao;

    private static final Logger LOGGER = LogManager.getLogger(CommentServiceImpl.class);

    /**
     * Instantiates a new Comment service.
     *
     * @param commentDao the class is provided CRUD operations for fuel type model.
     */
    @Autowired
    public CommentServiceImpl(final CommentDao commentDao, final CarSaleDao carSaleDao) {
        this.commentDao = commentDao;
        this.carSaleDao = carSaleDao;
    }

    /**
     * Gets comments of car sale with provided id.
     *
     * @param carSaleId the car sale id
     * @return the list of car sale comments
     * @throws NotFoundException throws if there is not such car sale to get comments
     */
    @Override
    public List<Comment> getCarSaleComments(final int carSaleId) throws NotFoundException {
        LOGGER.debug("method getCarSaleComments with parameter: [{}]", carSaleId);
        try {
            carSaleDao.getCarSale(carSaleId);
            return commentDao.getCarSaleComments(carSaleId);
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException("there is not such car sale");
        }
    }

    /**
     * Gets comment by id.
     *
     * @param id the comment id
     * @return the comment
     * @throws NotFoundException throws if there is not such comment
     */
    @Override
    public Comment getComment(final int id) throws NotFoundException {
        LOGGER.debug("method getComment with parameter: [{}]", id);
        try {
            return commentDao.getComment(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException("there is not such comment");
        }
    }

    /**
     * Add comment to car sale advertisement.
     * Gets car sale id from comment object
     *
     * @param carSaleId the car sale id
     * @param comment   the comment
     * @return comment id
     * @throws NotFoundException throws if there is not such car sale to add comment
     */
    @Override
    public Integer addComment(final int carSaleId, final Comment comment) {
        LOGGER.debug("method addComment with parameters: [{}, {}]", carSaleId, comment);
        try {
            carSaleDao.getCarSale(carSaleId);
            return commentDao.addComment(carSaleId, comment);
        } catch (EmptyResultDataAccessException ex) {
            throw new WrongReferenceException("there is not car sale with id=" + carSaleId + " to add comment");
        }
    }

    /**
     * Update comment.
     * Gets id from comment object
     *
     * @param comment the comment
     * @return check or car is updated
     * @throws NotFoundException throws if there is not such comment to update
     */
    @Override
    public boolean updateComment(final Comment comment) throws NotFoundException {
        LOGGER.debug("method updateComment with parameter: [{}]", comment);
        getComment(comment.getCommentId());
        return commentDao.updateComment(comment);
    }

    /**
     * Delete comment by id.
     *
     * @param id the index
     * @return check or car is deleted
     * @throws NotFoundException throws if there is not such comment to delete
     */
    @Override
    public boolean deleteComment(final int id) throws NotFoundException {
        LOGGER.debug("method deleteComment with parameter: [{}]", id);
        if (!commentDao.deleteComment(id)) {
            throw new NotFoundException("comment with id = " + id + "  you are trying to delete does not exist");
        }
        return true;
    }
}
