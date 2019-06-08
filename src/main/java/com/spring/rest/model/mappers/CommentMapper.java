package com.spring.rest.model.mappers;

import com.spring.rest.model.Comment;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CommentMapper implements RowMapper<Comment> {
    public static final String COMMENT_ID = "comment_id";
    public static final String USER_NAME = "user_name";
    public static final String COMMENT = "comment";
    public static final String CAR_SALE_ID = "car_sale_id";


    @Override
    public Comment mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Comment(resultSet.getInt(COMMENT_ID),
                resultSet.getString(USER_NAME), resultSet.getString(COMMENT),
                resultSet.getInt(CAR_SALE_ID));
    }
}
