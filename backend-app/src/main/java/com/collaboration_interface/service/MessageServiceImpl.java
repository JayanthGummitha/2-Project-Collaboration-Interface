package com.collaboration_interface.service;

import com.collaboration_interface.modal.Chat;
import com.collaboration_interface.modal.Message;
import com.collaboration_interface.modal.User;
import com.collaboration_interface.repository.MessageRepository;
import com.collaboration_interface.repository.ProjectRepository;
import com.collaboration_interface.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class MessageServiceImpl implements MessageService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private ProjectService projectService;

    @Override
    public Message sendMessage(Long senderId, Long projectId, String content) throws Exception {

        User sender=userRepository.findById(senderId).orElseThrow(()->new Exception("user not found with id:" + senderId));

        Chat chat=projectService.getProjectById(projectId).getChat();
        Message message=new Message();
        message.setContent(content);
        message.setSender(sender);
        message.setChat(chat);
        message.setCreatedAt(LocalDateTime.now());
        Message savedMessage=messageRepository.save(message);
        chat.getMessages().add(savedMessage);

        return savedMessage;

    }



    @Override
    public List<Message> getMessagesByProjectId(Long projectId) throws Exception {

        Chat chat=projectService.getChatByProjectId(projectId);

        List<Message> message=messageRepository.findByChatIdOrderByCreatedAtAsc(chat.getId());



        return message;
    }
}
