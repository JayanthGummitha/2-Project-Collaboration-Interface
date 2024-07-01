package com.collaboration_interface.service;

import com.collaboration_interface.modal.Comment;

import java.util.List;

public interface CommentService {

    Comment createComment(Long issueId, Long userId,String comment) throws Exception;

    void deleteComment(Long commentId, Long userId) throws Exception;
    List<Comment> findCommentByIssueId(Long issueId);
}
