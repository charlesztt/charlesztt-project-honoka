package com.prinzkarl.honoka.mail;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * Created by charlesztt on 2/28/16.
 */
public class SendMail implements Runnable {
    private String title;
    private String content;
    private String target;

    public void run() {
        try {
            send();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void send() throws IOException, ParseException, MessagingException {
        String[] mailCredentials = ObtainCredentials.obtainCredentials();
        String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        Properties props = System.getProperties();

        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");

        String username = mailCredentials[0];
        String password = mailCredentials[1];

        Session session = Session.getDefaultInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        // -- Create a new message --
        Message msg = new MimeMessage(session);

        // -- Set the FROM and TO fields --
        msg.setFrom(new InternetAddress(username));// + "@mo168.com"));
        msg.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(this.target, false));
        msg.setSubject(this.title);
        msg.setText(this.content);
        msg.setSentDate(new Date());
        Transport.send(msg);
        System.out.println("Message sent.");
    }

    public SendMail(String title, String content, String target) {
        this.title = title;
        this.content = content;
        this.target = target;
    }
}
