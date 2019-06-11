package com.spring.rest.dao.impl;

import com.spring.rest.dao.CommentDao;
import com.spring.rest.model.Comment;
import com.spring.rest.model.mappers.CommentMapper;
import com.spring.rest.model.mappers.ParameterSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The class provides CRUD operations with Comment model.
 * The class stores date in database
 */
@Repository
public class CommentDaoImpl implements CommentDao {
    /**
     * SQL query to get comment.
     */
    @Value("${comment.get}")
    private String GET_COMMENT_SQL;
    /**
     * SQL query to get comments.
     */
    @Value("${car.sale.comments.get}")
    private String GET_CAR_SALE_COMMENTS_SQL;
    /**
     * SQL query to add comment.
     */
    @Value("${comment.add}")
    private String ADD_COMMENT_SQL;
    /**
     * SQL query to update comment.
     */
    @Value("${comment.update}")
    private String UPDATE_COMMENT_SQL;
    /**
     * SQL query to delete comment.
     */
    @Value("${comment.delete}")
    private String DELETE_COMMENT_SQL;

    /**
     * named parameter JDBC template.
     */
    private final NamedParameterJdbcTemplate jdbcTemplate;
    /**
     * mapper to get Comment object.
     */
    private final CommentMapper commentMapper;
    /**
     * class is used to get parameters for sql query.
     */
    private final ParameterSource parameterSource;

    /**
     * Instantiates a new Comment dao.
     *
     * @param jdbcTemplate    the jdbc template
     * @param commentMapper   the comment mapper
     * @param parameterSource the parameter source
     */
    public CommentDaoImpl(final NamedParameterJdbcTemplate jdbcTemplate,
                          final CommentMapper commentMapper,
                          final ParameterSource parameterSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.commentMapper = commentMapper;
        this.parameterSource = parameterSource;
    }

    /**
     * Gets car sale comments.
     *
     * @param index the index
     * @return the car sale comments
     */
    @Override
    public List<Comment> getCarSaleComments(final int index) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", index);
        return jdbcTemplate.query(GET_CAR_SALE_COMMENTS_SQL, parameters,
                commentMapper);
    }

    /**
     * Gets comment.
     *
     * @param index the index
     * @return the comment
     */
    @Override
    public Comment getComment(final int index) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", index);
        return jdbcTemplate.queryForObject(GET_COMMENT_SQL, parameters,
                commentMapper);
    }

    /**
     * Add comment.
     *
     * @param comment the comment
     */
    @Override
    public void addComment(final Comment comment) {
        jdbcTemplate.update(ADD_COMMENT_SQL,
                parameterSource.getCommentParameters(comment));
    }

    /**
     * Update comment.
     *
     * @param comment the comment
     */
    @Override
    public void updateComment(final Comment comment) {
        jdbcTemplate.update(UPDATE_COMMENT_SQL,
                parameterSource.getCommentParameters(comment));
    }

    /**
     * Delete comment.
     *
     * @param index the index
     */
    @Override
    public void deleteComment(final int index) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", index);
        jdbcTemplate.update(DELETE_COMMENT_SQL, parameters);
    }
}
