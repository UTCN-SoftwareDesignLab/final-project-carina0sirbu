package service.email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.*;
import java.security.Security;
import java.util.Properties;

@Component
public class SendEmail {

    @Autowired
    public SendEmail() {
    }


    @Scheduled(fixedDelay = 8000)
    public void send(String fromUsername, String toUsername, String password, String emailContent) throws Exception {

        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

        Properties props = new Properties();

        props.setProperty("mail.transport.protocol", "smtp");

        props.setProperty("mail.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.debug", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {

                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(fromUsername, password);
                    }
                });

        session.setDebug(true);


        Transport transport = session.getTransport("smtp");
        InternetAddress addressFrom = new InternetAddress(fromUsername);

        MimeMessage message = new MimeMessage(session);
        message.setSender(addressFrom);
        message.setSubject("Testing javamail plain");
        message.setContent(emailContent, "text/plain");
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(toUsername));

        transport.connect();

        transport.send(message);
        transport.close();
    }
}
