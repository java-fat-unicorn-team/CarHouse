package com.carhouse.rest.controller;

import com.carhouse.model.Comment;
import com.carhouse.service.CommentService;
import javassist.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The Transmission com.carhouse.rest.com.carhouse.rest.controller.
 * Provide endpoints to manage comment model
 *
 * @author Katuranau Maksimilyan
 */
@RequestMapping("/carSale")
@RestController
public class CommentController {

    private static final Logger LOGGER = LogManager.getLogger(CommentController.class);

    private CommentService commentService;

    /**
     * Instantiates a new Comment com.carhouse.rest.com.carhouse.rest.controller.
     *
     * @param commentService the comment service to manage comment object
     */
    @Autowired
    public CommentController(final CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * Send all comments of selected car sale.
     *
     * @param carSaleId the car sale id
     * @return the list of comments in JSON
     */
    @GetMapping("/{carSaleId}/comment")
    public List<Comment> getComments(@PathVariable final int carSaleId) throws NotFoundException {
        LOGGER.debug("method getComments wit parameter: [{}]", carSaleId);
        return commentService.getCarSaleComments(carSaleId);
    }

    /**
     * Add new comment.
     * Get car sale's id to which it is necessary to add as path variable
     * Get comment object to add as request body
     *
     * @param carSaleId the car sale id
     * @param comment   the comment object to add
     * @return the id of added comment
     */
    @PostMapping("/{carSaleId}/comment")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Integer addComment(@PathVariable final int carSaleId, @RequestBody final Comment comment) {
        LOGGER.debug("method addComment wit parameters: [{}, {}]", carSaleId, comment);
        return commentService.addComment(carSaleId, comment);
    }

    /**
     * Update comment.
     * Replace comment with id provided in obtained object on this obtained object
     *
     * @param comment the comment object to update
     */
    @PutMapping("/comment")
    public void updateComment(@RequestBody final Comment comment) throws NotFoundException {
        LOGGER.debug("method updateComment wit parameter: [{}]", comment);
        commentService.updateComment(comment);
    }

    /**
     * Delete comment by id.
     * Get comment's id to delete as path variable
     *
     * @param id the id
     */
    @DeleteMapping("/comment/{id}")
    public void deleteComment(@PathVariable final int id) throws NotFoundException {
        LOGGER.debug("method deleteComment wit parameter: [{}]", id);
        commentService.deleteComment(id);
    }
}
