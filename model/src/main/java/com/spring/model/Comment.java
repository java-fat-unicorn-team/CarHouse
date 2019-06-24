package com.spring.model;

/**
 * The comment model is used to create comments for car sale announcement.
 * The model includes user's name who added the comment, comment and car sale announcement to which this comment applies
 * @author Katuranau Maksimilyan
 */
public class Comment {
    private int commentId;
    private String userName;
    private String comment;
    private CarSale carSale;

    /**
     * Instantiates a new Comment.
     *
     * @param commentId the comment id
     * @param userName  the user name
     * @param comment   the comment
     * @param carSale   the car sale
     */
    public Comment(final int commentId, final String userName, final String comment, final CarSale carSale) {
        this.commentId = commentId;
        this.userName = userName;
        this.comment = comment;
        this.carSale = carSale;
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
     * Gets car sale.
     *
     * @return the car sale id
     */
    public CarSale getCarSale() {
        return carSale;
    }

    /**
     * Sets car sale.
     *
     * @param carSale the car sale id
     */
    public void setCarSale(final CarSale carSale) {
        this.carSale = carSale;
    }

    @Override
    public final String toString() {
        return "userName='" + userName + '\''
                + ", comment='" + comment + "', "
                + carSale;
    }
}