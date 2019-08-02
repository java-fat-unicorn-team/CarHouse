package com.carhouse.service;

import com.carhouse.model.Comment;
import javassist.NotFoundException;

import java.util.List;

/**
 * The interface of comment service.
 * provides methods to manage Comment models.
 *
 * @author Katuranau Maksimilyan
 * @see Comment
 */
public interface CommentService {
    /**
     * Gets comments of car sale with provided id.
     *
     * @param carSaleId the car sale id
     * @return the list of car sale comments
     * @throws NotFoundException throws if there is not such car sale to get comments
     */
    List<Comment> getCarSaleComments(int carSaleId) throws NotFoundException;

    /**
     * Gets comment by id.
     *
     * @param id the comment id
     * @return the comment
     * @throws NotFoundException throws if there is not such comment
     */
    Comment getComment(int id) throws NotFoundException;

    /**
     * Add comment to car sale advertisement.
     * Gets car sale id from comment object
     *
     * @param carSaleId the car sale id
     * @param comment   the comment
     * @return comment id
     * @throws NotFoundException throws if there is not such car sale to add comment
     */
    Integer addComment(int carSaleId, Comment comment);

    /**
     * Update comment.
     * Gets id from comment object
     *
     * @param comment the comment
     * @throws NotFoundException throws if there is not such comment to update
     */
    void updateComment(Comment comment) throws NotFoundException;

    /**
     * Delete comment by id.
     *
     * @param id the index
     * @throws NotFoundException throws if there is not such comment to delete
     */
    void deleteComment(int id) throws NotFoundException;
}
