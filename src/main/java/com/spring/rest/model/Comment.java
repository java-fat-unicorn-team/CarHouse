package com.spring.rest.model;

public class Comment {
    private int commentId;
    private String userName;
    private String comment;
    private int carSaleId;


    public Comment() {
    }

    public Comment(int commentId, String userName, String comment, int carSaleId) {
        this.commentId = commentId;
        this.userName = userName;
        this.comment = comment;
        this.carSaleId = carSaleId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String pUserName) {
        this.userName = pUserName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(final String pComment) {
        this.comment = pComment;
    }

    public int getCarSaleId() {
        return carSaleId;
    }

    public void setCarSaleId(int carSaleId) {
        this.carSaleId = carSaleId;
    }

    @Override
    public String toString() {
        return "userName='" + userName + '\'' +
                ", comment='" + comment + '\'' +
                ", carSaleId=" + carSaleId;
    }
}
