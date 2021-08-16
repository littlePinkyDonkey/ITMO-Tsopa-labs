package andrei.teplyh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {
    private final JavaMailSender emailSender;

    @Autowired
    public MailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void send(String address, String subject, String message) {
        SimpleMailMessage simpleMessage = new SimpleMailMessage();

        simpleMessage.setTo(address);
        simpleMessage.setSubject(subject);
        simpleMessage.setText(message);

        emailSender.send(simpleMessage);
    }
}
