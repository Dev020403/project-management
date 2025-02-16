package com.project_managment.projectManagmentSystem.controller;

import com.project_managment.projectManagmentSystem.model.Comment;
import com.project_managment.projectManagmentSystem.model.User;
import com.project_managment.projectManagmentSystem.request.CreateCommentRequest;
import com.project_managment.projectManagmentSystem.response.MessageResponse;
import com.project_managment.projectManagmentSystem.service.CommentService;
import com.project_managment.projectManagmentSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<Comment> createComment(@RequestBody CreateCommentRequest req,
                                                 @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Comment createdComment = commentService.createComment(req.getIssueId(), user.getId(), req.getContent());
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }
    @DeleteMapping("/{commentId}")
    public ResponseEntity<MessageResponse> deleteComment(@PathVariable Long commentId,
                                                         @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        commentService.deleteComment(commentId,user.getId());
        MessageResponse res = new MessageResponse("comment deleted successfully");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @GetMapping("/{issueId}")
    public ResponseEntity<List<Comment>> getCommentsByIssueId(@PathVariable Long issueId) throws Exception {
        List<Comment> comments = commentService.findCommentByIssueId(issueId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
