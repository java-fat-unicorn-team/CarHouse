package com.spring.rest.dao;

import com.spring.rest.model.Comment;

import java.util.List;

public interface CommentDao {
    List<Comment> getAllCommentsOfCarSale(int index);
    Comment getComment(int index);
    void addComment(String userName, String comment, int CarSaleId);
    void updateComment(int index, String userName, String comment, int carSaleId);
    void deleteComment(int index);
}
