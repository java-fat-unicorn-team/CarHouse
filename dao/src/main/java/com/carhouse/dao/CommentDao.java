package com.carhouse.dao;

import com.carhouse.model.Comment;

import java.util.List;

/**
 * The interface provides methods to manage Comment model.
 * @author Katuranau Maksimilyan
 * @see Comment
 */
public interface CommentDao {
    /**
     * Gets comments of car sale with provided id.
     *
     * @param id the car sale id
     * @return the list of car sale comments
     */
    List<Comment> getCarSaleComments(int id);

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
     * @param comment the comment
     * @return comment id
     */
    Integer addComment(Comment comment);

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
