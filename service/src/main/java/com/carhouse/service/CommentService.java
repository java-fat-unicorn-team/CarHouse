package com.carhouse.service;

import com.carhouse.model.Comment;

import java.util.List;

/**
 * The interface of comment service.
 * provides methods to manage Comment models.
 * @author Katuranau Maksimilyan
 * @see Comment
 */
public interface CommentService {
    /**
     * Gets comments of car sale with provided id.
     *
     * @param carSaleId the car sale id
     * @return the list of car sale comments
     */
    List<Comment> getCarSaleComments(int carSaleId);

    /**
     * Gets comment by id.
     *
     * @param id the comment id
     * @return the comment
     */
    Comment getComment(int id);

    /**
     * Add comment to car sale advertisement.
     * Gets car sale id from comment object
     *
     * @param carSaleId the car sale id
     * @param comment   the comment
     * @return comment id
     */
    Integer addComment(int carSaleId, Comment comment);

    /**
     * Update comment.
     * Gets id from comment object
     *
     * @param comment the comment
     */
    void updateComment(Comment comment);

    /**
     * Delete comment by id.
     *
     * @param id the index
     */
    void deleteComment(int id);
}
