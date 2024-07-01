package com.collaboration_interface.service;

import com.collaboration_interface.modal.Comment;
import com.collaboration_interface.modal.Issue;
import com.collaboration_interface.modal.User;
import com.collaboration_interface.repository.CommentRepository;
import com.collaboration_interface.repository.IssueRepository;
import com.collaboration_interface.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public Comment createComment(Long issueId, Long userId, String content) throws Exception{
        Optional<Issue> issueOptional=issueRepository.findById(issueId);
        Optional<User> userOptional=userRepository.findById(userId);

        if(issueOptional.isEmpty()){
            throw new Exception("issue id not found "+issueId);
        }
        if(userOptional.isEmpty()){
            throw new Exception("user id is not found "+userId);
        }

        Issue issue=issueOptional.get();
        User user = userOptional.get();

        Comment comment=new Comment();
        comment.setIssue(issue);
        comment.setUser(user);
        comment.setCreatedDateTime(LocalDateTime.now());
        comment.setContent(content);

        Comment savedComment=commentRepository.save(comment);
        issue.getComments().add(savedComment);
        return savedComment;

    }

    @Override
    public void deleteComment(Long commentId, Long userId)throws Exception {
        Optional<Comment> commentOptional=commentRepository.findById(commentId);
        Optional<User> userOptional=userRepository.findById(userId);

        if(commentOptional.isEmpty()){
            throw new Exception("coment id not found "+commentId);
        }
        if(userOptional.isEmpty()){
            throw new Exception("user id is not found "+userId);
        }

        Comment comment=commentOptional.get();
        User user = userOptional.get();


        if (comment.getUser().equals(user)) {
            commentRepository.delete(comment);
        }else{
            throw new Exception("User does not have permission to delete comment !");
        }



    }

    @Override
    public List<Comment> findCommentByIssueId(Long issueId) {


        return commentRepository.findCommentByIssueId(issueId);
    }
}
