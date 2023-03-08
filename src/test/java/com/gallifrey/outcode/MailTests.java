package com.gallifrey.outcode;

import com.gallifrey.outcode.util.MailClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = OutCodeApplication.class)

public class MailTests {
    String user="Car";
    String toMail="956465331@qq.com";

    @Autowired
    private MailClient mailClient;
    @Autowired
    private TemplateEngine templateEngine;
    @Test
    public void textTextMail(){
        mailClient.sendMail(toMail,"TEST","Hello World");
    }

    @Test
    public void testHtmlMail(){

        Context context=new Context();
        context.setVariable("username",user);
        String html=templateEngine.process("/mail/demo",context);
        mailClient.sendMail(toMail,"TEST_HTML",html);

    }
}
