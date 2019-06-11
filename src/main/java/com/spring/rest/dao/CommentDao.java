package com.spring.rest.dao;

import com.spring.rest.model.Comment;

import java.util.List;

/**
 * The interface provides CRUD operations with Comment model.
 */
public interface CommentDao {
    /**
     * Gets all comments of car sale.
     *
     * @param index the index
     * @return the all comments of car sale
     */
    List<Comment> getCarSaleComments(int index);

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
     * @param comment the comment
     */
    void addComment(Comment comment);

    /**
     * Update comment.
     *
     * @param comment the comment
     */
    void updateComment(Comment comment);

    /**
     * Delete comment.
     *
     * @param index the index
     */
    void deleteComment(int index);
}
