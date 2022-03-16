package andrei.teplyh.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.context.event.ContextRefreshedEvent;

import javax.jms.JMSException;

public interface JmsService {
    public void receive(ContextRefreshedEvent event) throws JsonProcessingException, JMSException;
}
