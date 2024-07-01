package com.collaboration_interface.controller;

import com.collaboration_interface.modal.Comment;
import com.collaboration_interface.modal.User;
import com.collaboration_interface.request.CreateCommentRequest;
import com.collaboration_interface.response.MessageResponse;
import com.collaboration_interface.service.CommentService;
import com.collaboration_interface.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ListIterator;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<Comment> createComment(
            @RequestBody CreateCommentRequest req,
            @RequestHeader("Authorization")String jwt
    )throws Exception{
        User user=userService.findUserProfileByJwt(jwt);
        Comment createdComment=commentService.createComment(req.getIssueId(),user.getId(),req.getContent());

        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);

    }
    @DeleteMapping("/{commentId}")
    public ResponseEntity<MessageResponse> deleteMessage(@PathVariable Long commentId, @RequestHeader("Authorization") String jwt)throws Exception{

        User user=userService.findUserProfileByJwt(jwt);
        commentService.deleteComment(commentId,user.getId());
        MessageResponse message=new MessageResponse();
        message.setMessage("comment is deleted Successfully");
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @GetMapping("/{issueId}")
    public ResponseEntity<List<Comment>> getCommentByIssueId(@PathVariable Long issueId){
        List<Comment> comments=commentService.findCommentByIssueId(issueId);

        return new ResponseEntity<>(comments,HttpStatus.OK);

    }
}
