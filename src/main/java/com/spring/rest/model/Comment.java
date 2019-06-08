package com.spring.rest.model;

/**
 * The type Comment.
 */
public class Comment {
    private int commentId;
    private String userName;
    private String comment;
    private int carSaleId;


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
     * @param carSaleId the car sale id
     */
    public Comment(final int commentId, final String userName,
                   final String comment, final int carSaleId) {
        this.commentId = commentId;
        this.userName = userName;
        this.comment = comment;
        this.carSaleId = carSaleId;
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

    /**
     * Gets car sale id.
     *
     * @return the car sale id
     */
    public int getCarSaleId() {
        return carSaleId;
    }

    /**
     * Sets car sale id.
     *
     * @param carSaleId the car sale id
     */
    public void setCarSaleId(final int carSaleId) {
        this.carSaleId = carSaleId;
    }

    @Override
    public final String toString() {
        return "userName='" + userName + '\''
                + ", comment='" + comment + '\''
                + ", carSaleId=" + carSaleId;
    }
}
