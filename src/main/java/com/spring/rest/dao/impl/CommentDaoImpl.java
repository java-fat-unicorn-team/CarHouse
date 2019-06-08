package com.spring.rest.dao.impl;

import com.spring.rest.dao.CommentDao;
import com.spring.rest.model.Comment;
import com.spring.rest.model.mappers.CommentMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDaoImpl implements CommentDao {
    @Value("${get.comment}")
    private String GET_COMMENT_SQL;
    @Value("${get.all.comments.of.car.sale}")
    private String GET_ALL_COMMENTS_OF_CAR_SALE_SQL;
    @Value("${add.comment}")
    private String ADD_COMMENT_SQL;
    @Value("${update.comment.sql}")
    private String UPDATE_COMMENT_SQL;
    @Value("${delete.comment.sql}")
    private String DELETE_COMMENT_SQL;

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final CommentMapper commentMapper;

    public CommentDaoImpl(NamedParameterJdbcTemplate jdbcTemplate, CommentMapper commentMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.commentMapper = commentMapper;
    }

    @Override
    public List<Comment> getAllCommentsOfCarSale(int index) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", index);
        return jdbcTemplate.query(GET_ALL_COMMENTS_OF_CAR_SALE_SQL, parameters, commentMapper);
    }

    @Override
    public Comment getComment(int index) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", index);
        return jdbcTemplate.queryForObject(GET_COMMENT_SQL, parameters, commentMapper);
    }

    @Override
    public void addComment(String userName, String comment, int carSaleId) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("userName", userName)
                .addValue("comment", comment)
                .addValue("carSaleId", carSaleId);
        jdbcTemplate.update(ADD_COMMENT_SQL, parameters);
    }

    @Override
    public void updateComment(int index, String userName, String comment, int carSaleId) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", index)
                .addValue("userName", userName)
                .addValue("comment", comment)
                .addValue("carSaleId", carSaleId);
        jdbcTemplate.update(UPDATE_COMMENT_SQL, parameters);
    }

    @Override
    public void deleteComment(int index) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", index);
        jdbcTemplate.update(DELETE_COMMENT_SQL, parameters);
    }
}
