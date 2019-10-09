package com.carhouse.rest.controller;

import com.carhouse.model.Comment;
import com.carhouse.service.CommentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

/**
 * The Transmission controller.
 * Provide endpoints to manage comment model
 *
 * @author Katuranau Maksimilyan
 */
@RequestMapping("/carSale")
@RestController
@Validated
public class CommentController {

    private static final Logger LOGGER = LogManager.getLogger(CommentController.class);

    private CommentService commentService;

    /**
     * Instantiates a new Comment controller.
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
     * @throws NotFoundException throws if there is not such car sale to get comments
     */
    @ApiOperation("get comments of car sale with provided id")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not Found")})
    @GetMapping("/{carSaleId}/comment")
    public List<Comment> getComments(@PathVariable
                                     @PositiveOrZero(message = "car sale id can't be negative") final int carSaleId)
            throws NotFoundException {
        LOGGER.debug("method getComments wit parameter: [{}]", carSaleId);
        return commentService.getCarSaleComments(carSaleId);
    }

    /**
     * Send comment with provided id.
     * Get comment id as path variable
     *
     * @param commentId the comment id
     * @return the comment with provided id
     * @throws NotFoundException throws if there is not such comment
     */
    @ApiOperation("get comment by id")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not Found")})
    @GetMapping("/comment/{commentId}")
    public Comment getComment(@PathVariable
                              @PositiveOrZero(message = "comment id can't be negative") final int commentId)
            throws NotFoundException {
        LOGGER.debug("method getComment wit parameter: [{}]", commentId);
        return commentService.getComment(commentId);
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
    @ApiOperation("add comment to car sale with id provided as path variable 'carSaleId'")
    @ApiResponses(value = {
            @ApiResponse(code = 424, message = "Wrong References")})
    @PostMapping("/{carSaleId}/comment")
    public Integer addComment(@PathVariable
                              @PositiveOrZero(message = "car sale id can't be negative") final int carSaleId,
                              @RequestBody @Valid final Comment comment) {
        LOGGER.debug("method addComment wit parameters: [{}, {}]", carSaleId, comment);
        return commentService.addComment(carSaleId, comment);
    }

    /**
     * Update comment.
     * Replace comment with id provided in obtained object on this obtained object
     *
     * @param comment the comment object to update
     * @throws NotFoundException throws if there is not such comment to update
     */
    @ApiOperation("update comment, gets comment id to update from object provided as request body")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 424, message = "Wrong References")})
    @PutMapping("/comment")
    public void updateComment(@RequestBody @Valid final Comment comment) throws NotFoundException {
        LOGGER.debug("method updateComment wit parameter: [{}]", comment);
        commentService.updateComment(comment);
    }

    /**
     * Delete comment by id.
     * Get comment's id to delete as path variable
     *
     * @param id the id
     * @throws NotFoundException throws if there is not such comment to delete
     */
    @ApiOperation("delete comment by id")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not Found")})
    @DeleteMapping("/comment/{id}")
    public void deleteComment(@PathVariable
                              @PositiveOrZero(message = "comment id can't be negative") final int id)
            throws NotFoundException {
        LOGGER.debug("method deleteComment wit parameter: [{}]", id);
        commentService.deleteComment(id);
    }
}
