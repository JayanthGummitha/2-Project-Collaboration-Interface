package com.collaboration_interface.service;

import com.collaboration_interface.modal.Issue;
import com.collaboration_interface.modal.User;
import com.collaboration_interface.request.IssueRequest;
import jdk.jshell.spi.ExecutionControl;

import java.util.List;
import java.util.Optional;

public interface IssueService {
    Issue getIssueById(Long issueId)throws Exception;

    List<Issue> getIssueByProjectId(Long ProjectId) throws Exception;

    Issue createIssue(IssueRequest issue, User userid)throws Exception;


    void deleteIssue(Long issueId,Long userId) throws  Exception;

    Issue addUserToIssue(Long issueId, Long userId) throws Exception;

    Issue updateStatus(Long issueId, String status) throws Exception;



}
