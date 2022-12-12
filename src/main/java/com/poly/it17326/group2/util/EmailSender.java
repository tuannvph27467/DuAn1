/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group2.util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author quynhncph26201
 */
public class EmailSender extends Thread {

    private static String toEmailInput;
    private static String title;
    private static String body;

    @Override
    public void run() {
        try {
            final String fromEmail = "anhntph27418@fpt.edu.vn";
            // Mat khai email cua ban
            final String password = "0963692153";
            // dia chi email nguoi nhan
            final String toEmail = toEmailInput;
            final String subject = "Java Example Test";
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
            props.put("mail.smtp.port", "587"); //TLS Port
            props.put("mail.smtp.auth", "true"); //enable authentication
            props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

            Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            message.setSubject(subject);
            message.setSubject(title);
            message.setSubject(title, "UTF-8");
            message.setText(body, "UTF-8");
            String htmlContent = "<h1>" + body + "</h1>";
            message.setContent(htmlContent, "text/html");
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void main(String args[]) {
//        MyThread t1 = new MyThread();
//        t1.start();
//    }
    public static void emailSender(String toEmail, String tit, String bod) {
        toEmailInput = toEmail;
        title = tit;
        body = bod;
        EmailSender emailSender = new EmailSender();
        emailSender.start();
    }
}
