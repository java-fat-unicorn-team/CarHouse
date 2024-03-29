package com.carhouse.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

/**
 * The comment model is used to create comments for car sale announcement.
 * The model includes user's name who added the comment, comment and car sale announcement to which this comment applies
 * @author Katuranau Maksimilyan
 */
public class Comment {
    @PositiveOrZero(message = "car sale comment id can't be negative")
    private int commentId;
    @NotBlank(message = "User name must not be empty")
    @Size(max = 45)
    private String userName;
    @NotBlank(message = "Comment text must not be empty")
    @Size(max = 225)
    private String comment;

    /**
     * Instantiates a new Comment.
     */
    public Comment() {
    }

    /**
     * Instantiates a new Comment.
     *
     * @param commentId the comment id
     * @param userName  the user name
     * @param comment   the comment
     */
    public Comment(final int commentId, final String userName, final String comment) {
        this.commentId = commentId;
        this.userName = userName;
        this.comment = comment;
    }

    /**
     * Gets comment id.
     *
     * @return the comment id
     */
    public int getCommentId() {
        return commentId;
    }

    /**
     * Sets comment id.
     *
     * @param commentId the comment id
     */
    public void setCommentId(final int commentId) {
        this.commentId = commentId;
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public void setUserName(final String userName) {
        this.userName = userName;
    }

    /**
     * Gets comment.
     *
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets comment.
     *
     * @param comment the comment
     */
    public void setComment(final String comment) {
        this.comment = comment;
    }

    @Override
    public final String toString() {
        return "userName='" + userName + '\''
                + ", comment='" + comment;
    }
}
