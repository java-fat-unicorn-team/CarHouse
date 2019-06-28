package com.carhouse.dao.impl;

import com.carhouse.dao.CommentDao;
import com.carhouse.model.Comment;
import com.carhouse.dao.mappers.CommentMapper;
import com.carhouse.dao.mappers.ParameterSource;
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
 * It is realisation of CommentDao interface
 * @see CommentDao
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
    @Value("${car.sale.comments.list.get}")
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
     * @param parameterSource            the class which provides parameters for sql query
     */
    public CommentDaoImpl(final NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                          final CommentMapper commentMapper, final ParameterSource parameterSource) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.commentMapper = commentMapper;
        this.parameterSource = parameterSource;
    }

    /**
     * Gets comments of car sale with provided id.
     *
     * @param id the car sale id
     * @return the list of car sale comments
     */
    @Override
    public List<Comment> getCarSaleComments(final int id) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);
        LOGGER.debug("method getCarSaleComments with parameter: [{}]", id);
        return namedParameterJdbcTemplate.query(GET_LIST_CAR_SALE_COMMENTS_SQL, parameters, commentMapper);
    }

    /**
     * Gets comment by id.
     *
     * @param id the comment id
     * @return the comment
     */
    @Override
    public Comment getComment(final int id) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);
        LOGGER.debug("method getComment with parameter: [{}]", id);
        return namedParameterJdbcTemplate.queryForObject(GET_COMMENT_SQL, parameters, commentMapper);
    }

    /**
     * Add comment to car sale advertisement.
     * Gets car sale id from comment object
     *
     * @param comment the comment
     * @return comment id
     */
    @Override
    public Integer addComment(final Comment comment) {
        LOGGER.debug("method addComment with parameter: [{}]", comment);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(ADD_COMMENT_SQL, parameterSource.getCommentParameters(comment), keyHolder);
        return keyHolder.getKey().intValue();
    }

    /**
     * Update comment.
     * Gets id from comment object and rewrite comment with such id in database
     *
     * @param comment the comment
     */
    @Override
    public void updateComment(final Comment comment) {
        LOGGER.debug("method updateComment with parameter: [{}]", comment);
        namedParameterJdbcTemplate.update(UPDATE_COMMENT_SQL, parameterSource.getCommentParameters(comment));
    }

    /**
     * Delete comment by id.
     *
     * @param id the index
     */
    @Override
    public void deleteComment(final int id) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);
        LOGGER.debug("method deleteComment with parameter: [{}]", id);
        namedParameterJdbcTemplate.update(DELETE_COMMENT_SQL, parameters);
    }
}
