package com.poly.datn.be.util;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class MailUtil {
    public static void sendmail(String receive, Long orderId) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("tanvxph13005@fpt.edu.vn", "Mozilla Firefox");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("tanvxph13005@fpt.edu.vn", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receive));
        msg.setSubject("Công Minh Idol thông báo");
        msg.setContent("Mãi yêu <3", "text/html");
        msg.setSentDate(new Date());

        Transport.send(msg);
    }
}
