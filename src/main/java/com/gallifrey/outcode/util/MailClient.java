package com.gallifrey.outcode.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Component
public class MailClient {
    private static final Logger logger= LoggerFactory.getLogger(MailClient.class);


    private JavaMailSender sender;
    @Autowired
    public void setSender(JavaMailSender sender){
        this.sender=sender;
    }

    @Value("${spring.mail.username}")
    private String from;

    public void sendMail(String to,String subject,String content){
        try {
        MimeMessage mimeMessage=sender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(mimeMessage);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content,true);
        sender.send(helper.getMimeMessage());
        }catch (MessagingException e){
            logger.error("发送邮件失败："+e.getMessage());
        }
    }
}
