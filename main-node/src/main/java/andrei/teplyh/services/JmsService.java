package andrei.teplyh.services;

import com.fasterxml.jackson.core.JsonProcessingException;

import javax.jms.JMSException;

public interface JmsService {
    void send(Object o) throws JsonProcessingException, JMSException;
}
