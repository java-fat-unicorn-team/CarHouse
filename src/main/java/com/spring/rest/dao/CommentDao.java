package com.spring.rest.dao;

import com.spring.rest.model.Comment;

import java.util.List;

/**
 * The interface Comment dao.
 */
public interface CommentDao {
    /**
     * Gets all comments of car sale.
     *
     * @param index the index
     * @return the all comments of car sale
     */
    List<Comment> getAllCommentsOfCarSale(int index);

    /**
     * Gets comment.
     *
     * @param index the index
     * @return the comment
     */
    Comment getComment(int index);

    /**
     * Add comment.
     *
     * @param userName  the user name
     * @param comment   the comment
     * @param carSaleId the car sale id
     */
    void addComment(String userName, String comment, int carSaleId);

    /**
     * Update comment.
     *
     * @param index    the index
     * @param userName the user name
     * @param comment  the comment
     */
    void updateComment(int index, String userName, String comment);

    /**
     * Delete comment.
     *
     * @param index the index
     */
    void deleteComment(int index);
}
