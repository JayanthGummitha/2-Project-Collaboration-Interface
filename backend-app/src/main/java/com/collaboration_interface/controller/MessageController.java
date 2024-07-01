package com.collaboration_interface.controller;

import com.collaboration_interface.modal.Chat;
import com.collaboration_interface.modal.Message;
import com.collaboration_interface.modal.User;
import com.collaboration_interface.repository.MessageRepository;
import com.collaboration_interface.repository.UserRepository;
import com.collaboration_interface.request.CreateMessageRequest;
import com.collaboration_interface.service.MessageService;
import com.collaboration_interface.service.MessageServiceImpl;
import com.collaboration_interface.service.ProjectService;
import com.collaboration_interface.service.UserService;
import org.springframework.aop.aspectj.annotation.MetadataAwareAspectInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    public MessageService messageService;

    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody CreateMessageRequest request)throws Exception{
        User user =userService.findUserById(request.getSenderId());

        Chat chats=projectService.getProjectById(request.getProjectId()).getChat();

        Message message=messageService.sendMessage(request.getSenderId(), request.getProjectId(), request.getContent());
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/chat/{projectId}")
    public ResponseEntity<List<Message>> getMessageByChatId(@PathVariable Long projectId)throws Exception{
    List<Message> messages=messageService.getMessagesByProjectId(projectId);
        return ResponseEntity.ok(messages);
    }





}
