package com.collaboration_interface.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{
    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public void sendEmailWithToken(String userEmail, String link) throws MessagingException {

        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,"utf-8");
        String subject="Join Project team Invitation";
        String text="Click link to join the project team "+link;
        helper.setSubject(subject);
        helper.setText(text);

        try{
            javaMailSender.send(mimeMessage);
        }
        catch (MailSendException e){
            throw new MailSendException("Fail to send email ");
        }


    }
}
