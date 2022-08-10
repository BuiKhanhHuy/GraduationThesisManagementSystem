package com.buikhanhhuy.configs;

import java.util.Objects;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

@Configuration
@ComponentScan(basePackages = "com.buikhanhhuy")
@PropertySource("classpath:application.properties")
public class EmailConfig {
    @Autowired
    private Environment environment;

    /*
     * Application configuration
     */
    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        // Gmail SMTP configuration.
        mailSender.setHost(environment.getProperty("email.host"));
        mailSender.setPort(Integer.parseInt(Objects.requireNonNull(environment.getProperty("email.port"))));

        /*
         *  gmail id and password
         */
        mailSender.setUsername(environment.getProperty("email.host.user"));
        mailSender.setPassword(environment.getProperty("email.host.password"));

        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.starttls.enable", environment.getProperty("mail.smtp.starttls.enable"));
        javaMailProperties.put("mail.smtp.auth", environment.getProperty("mail.smtp.auth"));
        javaMailProperties.put("mail.transport.protocol", environment.getProperty("mail.transport.protocol"));
        javaMailProperties.put("mail.debug", environment.getProperty("mail.debug"));

        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;
    }


    /*
     * FreeMarker configuration.
     */
    @Bean
    public FreeMarkerConfigurationFactoryBean getFreeMarkerConfiguration()
    {
        FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
        bean.setDefaultEncoding("utf-8");
        bean.setTemplateLoaderPaths("classpath:/mailtemplate/");
        return bean;
    }
}

