package com.spring.rest.dao.impl;

import com.spring.rest.dao.CommentDao;
import com.spring.rest.model.Comment;
import com.spring.rest.model.mappers.CommentMapper;
import com.spring.rest.model.mappers.ParameterSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The class provides methods to manage Comment model.
 * The class stores date in database
 * @author Katuranau Maksimilyan
 */
@Repository
public class CommentDaoImpl implements CommentDao {
    /**
     * SQL query to get comment.
     */
    @Value("${comment.get}")
    private String GET_COMMENT_SQL;
    /**
     * SQL query to get list of car sale comments.
     */
    @Value("${list.car.sale.comments.get}")
    private String GET_LIST_CAR_SALE_COMMENTS_SQL;
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
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    /**
     * mapper to get Comment object.
     */
    private final CommentMapper commentMapper;
    /**
     * class is used to get parameters for sql query.
     */
    private final ParameterSource parameterSource;
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger(CommentDaoImpl.class);

    /**
     * Instantiates a new Comment dao.
     *
     * @param namedParameterJdbcTemplate the named parameter jdbc template
     * @param commentMapper              the comment mapper
     * @param parameterSource            the parameter source
     */
    public CommentDaoImpl(final NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                          final CommentMapper commentMapper, final ParameterSource parameterSource) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.commentMapper = commentMapper;
        this.parameterSource = parameterSource;
    }

    /**
     * Gets car sale comments.
     *
     * @param index the index
     * @return the list of car sale comments
     */
    @Override
    public List<Comment> getCarSaleComments(final int index) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", index);
        LOGGER.debug("method getCarSaleComments with parameter: {} was called", index);
        return namedParameterJdbcTemplate.query(GET_LIST_CAR_SALE_COMMENTS_SQL, parameters, commentMapper);
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
        LOGGER.debug("method getComment with parameter: {} was called", index);
        return namedParameterJdbcTemplate.queryForObject(GET_COMMENT_SQL, parameters, commentMapper);
    }

    /**
     * Add comment.
     *
     * @param comment the comment
     * @return comment id
     */
    @Override
    public Integer addComment(final Comment comment) {
        LOGGER.debug("method addComment with parameter: {}", comment);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(ADD_COMMENT_SQL, parameterSource.getCommentParameters(comment), keyHolder);
        return keyHolder.getKey().intValue();
    }

    /**
     * Update comment.
     *
     * @param comment the comment
     */
    @Override
    public void updateComment(final Comment comment) {
        LOGGER.debug("method updateComment with parameter: {}", comment);
        namedParameterJdbcTemplate.update(UPDATE_COMMENT_SQL, parameterSource.getCommentParameters(comment));
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
        LOGGER.debug("method deleteComment with parameter: {} was called", index);
        namedParameterJdbcTemplate.update(DELETE_COMMENT_SQL, parameters);
    }
}
