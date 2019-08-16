package com.carhouse.rest.controllerIT;

import com.carhouse.model.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

class CommentControllerTestIT {

    private static final String HOST = "http://localhost:8080";
    private static final String CAR_SALE_COMMENT_LIST_GET_URL = "/carSale/3/comment";
    private static final String NOT_EXIST_CAR_SALE_COMMENT_LIST_GET_URL = "/carSale/150/comment";
    private static final String CAR_SALE_COMMENT_ADD_URL = "/carSale/3/comment";
    private static final String TO_NOT_EXIST_CAR_SALE_COMMENT_ADD_URL = "/carSale/93/comment";
    private static final String CAR_SALE_COMMENT_UPDATE_URL = "/carSale/comment";
    private static final String CAR_SALE_COMMENT_DELETE_URL = "/carSale/comment/";

    RestTemplate restTemplate = new RestTemplate();

    @Test
    void getCarSaleComments() {
        ResponseEntity<String> response = restTemplate.getForEntity(HOST + CAR_SALE_COMMENT_LIST_GET_URL,
                String.class);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void getCommentsOfNotExistentCarSale() {
        assertThrows(HttpClientErrorException.NotFound.class, () -> restTemplate.getForEntity(HOST
                + NOT_EXIST_CAR_SALE_COMMENT_LIST_GET_URL, String.class));
    }

    @Test
    void addComment() {
        Comment comment = new Comment(12, "vova", "good");
        HttpEntity<Comment> request = new HttpEntity<>(comment);
        ResponseEntity<String> response = restTemplate.postForEntity(HOST + CAR_SALE_COMMENT_ADD_URL, request,
                String.class);
        assertEquals(200, response.getStatusCodeValue());
        Integer id = Integer.valueOf(response.getBody());
        assertTrue(id > 0);
        restTemplate.delete(HOST + CAR_SALE_COMMENT_DELETE_URL + id);
    }

    @Test
    void addCommentWithWrongReference() {
        Comment comment = new Comment(12, "vova", "good");
        HttpEntity<Comment> request = new HttpEntity<>(comment);
        assertThrows(HttpClientErrorException.class, () -> restTemplate.postForEntity(HOST
                + TO_NOT_EXIST_CAR_SALE_COMMENT_ADD_URL, request, String.class));
    }

    @Test
    void updateComment() {
        Comment comment = new Comment(2, "vova", "good");
        HttpEntity<Comment> request = new HttpEntity<>(comment);
        ResponseEntity response = restTemplate.exchange(HOST + CAR_SALE_COMMENT_UPDATE_URL, HttpMethod.PUT,
                request, String.class);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void updateNotExistComment() {
        Comment comment = new Comment(73, "vova", "good");
        HttpEntity<Comment> request = new HttpEntity<>(comment);
        assertThrows(HttpClientErrorException.class, () -> restTemplate.exchange(HOST + CAR_SALE_COMMENT_UPDATE_URL,
                HttpMethod.PUT, request, String.class));
    }

    @Test
    void deleteComment() {
        int commentId = 4;
        ResponseEntity response = restTemplate.exchange(HOST + CAR_SALE_COMMENT_DELETE_URL + commentId,
                HttpMethod.DELETE, null, String.class);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void deleteNotExistComment() {
        assertThrows(HttpClientErrorException.class, () ->
                restTemplate.delete(HOST + CAR_SALE_COMMENT_DELETE_URL + 123));
    }
}