package org.example.risprojekatv2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("emailService")
public class MailService {

    @Autowired
    private JavaMailSender sender;

    public void sendEmail(String toEmail, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ppettarrm@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        sender.send(message);
    }





}
