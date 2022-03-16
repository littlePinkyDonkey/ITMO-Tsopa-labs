package andrei.teplyh.services.impl;

import andrei.teplyh.services.JmsService;
import com.amazon.sqs.javamessaging.AmazonSQSMessagingClientWrapper;
import com.amazon.sqs.javamessaging.SQSConnection;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jms.*;

@Service
public class JmsServiceImpl implements JmsService {
    private final SQSConnection connection;
    private final ObjectMapper mapper;

    @Autowired
    public JmsServiceImpl(final SQSConnection connection, final ObjectMapper mapper) {
        this.mapper = mapper;
        this.connection = connection;
    }

    @Override
    public void send(Object o) throws JsonProcessingException, JMSException {
        AmazonSQSMessagingClientWrapper client = connection.getWrappedAmazonSQSClient();

        if( !client.queueExists("ymq_jms_example") ) {
            client.createQueue( "ymq_jms_example" );
        }

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue("ymq_jms_example");

        MessageProducer producer = session.createProducer(queue);

        Message message = session.createTextMessage(mapper.writeValueAsString(o));
        producer.send(message);
    }
}
