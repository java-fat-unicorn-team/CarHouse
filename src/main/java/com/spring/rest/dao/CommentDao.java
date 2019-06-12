package com.spring.rest.dao;

import com.spring.rest.model.Comment;

import java.util.List;

/**
 * The interface provides methods to manage Comment model.
 */
public interface CommentDao {
    /**
     * Gets car sale comments.
     *
     * @param index the index
     * @return the list of car sale's comments
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
