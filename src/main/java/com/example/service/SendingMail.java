package com.example.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

@Service
public class SendingMail {

    @Value("${img.whiskyMail.path}")
    private String imagePath;

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendingMessage(String toAddress) throws MessagingException {

        MimeMessage mimeMessage =  javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setTo(toAddress);

        String subject = "Test";
        helper.setSubject(subject);
        helper.setSentDate(new Date());

        String someText = "text";
        helper.setText(someText);
        // let's attach the infamous windows Sample file (this time copied to c:/)
        FileSystemResource file = new FileSystemResource(new File(imagePath));
        helper.addAttachment("UserSeeThisNameFile.jpg", file);

        helper.setFrom(emailFrom);

        javaMailSender.send(mimeMessage);



    }

}
