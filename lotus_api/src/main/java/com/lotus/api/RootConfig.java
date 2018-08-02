package com.lotus.api;

import com.lotus.api.util.MailUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"com.lotus.api"},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)})
public class RootConfig {

    private static String MAIL_126_SMTP = "smtp.126.com";
    private static Integer MAIL_126_PORT = 25;
    private static String MAIL_USERNAME =  "weirdoqi@126.com";
    private static String MAIL_PASSWORD = "qiqiweirdo";
    private static String MAIL_AUTH_PASSWORD = "qiqiweirdo110";//第三方登陆授权码之类的鬼

    /**
     * 配置邮件服务器
     * @return
     */
    @Bean
    public MailSender mailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setDefaultEncoding("UTF-8");
        mailSender.setHost(MAIL_126_SMTP);//邮件服务器
        mailSender.setUsername(MAIL_USERNAME);//发送人服务器用户名
        mailSender.setPassword(MAIL_AUTH_PASSWORD);//此处为授权码，而非邮箱密码

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.timeout", "25000");
        properties.setProperty("mail.smtp.port", "25");
        mailSender.setJavaMailProperties(properties);
        return mailSender;
    }

    /**
     * 配置邮件信息工具类
     * @return
     */
    @Bean
    public SimpleMailMessage simpleMailMessage(){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(MAIL_USERNAME);
        return mailMessage;
    }

    /**
     * 配置对外邮件工具类
     * @param mailMessage
     * @param mailSender
     * @return
     */
    @Bean
    public MailUtil mailUtil(SimpleMailMessage mailMessage, MailSender mailSender){
        MailUtil mailUtil = new MailUtil();
        mailUtil.setMailSender(mailSender);
        mailUtil.setSimpleMailMessage(mailMessage);
        return mailUtil;
    }
}