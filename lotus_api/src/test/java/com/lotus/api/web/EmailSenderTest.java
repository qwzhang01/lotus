package com.lotus.api.web;

import com.lotus.api.RootConfig;
import com.lotus.api.WebConfig;
import com.lotus.api.util.MailUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RootConfig.class, WebConfig.class})
@WebAppConfiguration
public class EmailSenderTest {
    @Autowired
    private MailUtil mailUtil;

    @Test
    public void sendSingleTest(){
        mailUtil.send("qwzhang01@126.com", "This is a test single mail", "Hello Single!");
    }

    //@Test
    public void sendMassTest(){
        //不允许群发
        List<String> recipients=new ArrayList<String>();
        recipients.add("qwzhang01@126.com");
        recipients.add("782264826@qq.com");
        mailUtil.send(recipients, "This is a test mail", "Hello Test!");
    }
}