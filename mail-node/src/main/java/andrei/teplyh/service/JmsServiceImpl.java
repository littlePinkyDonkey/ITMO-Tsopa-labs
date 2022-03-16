package andrei.teplyh.service;

import andrei.teplyh.dto.MailDto;
import com.amazon.sqs.javamessaging.AmazonSQSMessagingClientWrapper;
import com.amazon.sqs.javamessaging.SQSConnection;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.jms.*;

@Service
public class JmsServiceImpl implements JmsService {

    @Value("${mail.subject}")
    private String subject;

    @Value("${mail.message}")
    private String message;

    private final ObjectMapper objectMapper;
    private final MailService mailService;
    private final SQSConnection connection;

    @Autowired
    public JmsServiceImpl(
            SQSConnection connection,
            ObjectMapper objectMapper,
            MailService mailService
    ) {
        this.connection = connection;
        this.objectMapper = objectMapper;
        this.mailService = mailService;
    }

    @Override
    @EventListener
    public void receive(ContextRefreshedEvent event) throws JsonProcessingException, JMSException {
        AmazonSQSMessagingClientWrapper client = connection.getWrappedAmazonSQSClient();

        if( !client.queueExists("ymq_jms_example") ) {
            client.createQueue( "ymq_jms_example" );
        }

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue("ymq_jms_example");

        MessageConsumer consumer = session.createConsumer(queue);
        connection.start();
        Message jmsMessage = consumer.receive(1000);

        MailDto mailDto = objectMapper.readValue(((TextMessage) jmsMessage).getText(), MailDto.class);
        mailService.send(mailDto.getEmail(), subject, String.format(message, mailDto.getMessage()));
    }
}
