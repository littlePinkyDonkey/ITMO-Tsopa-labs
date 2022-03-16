package andrei.teplyh.config;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnection;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.JMSException;

@Configuration
@EnableJms
public class JmsConfig {
    private static String queueName = "ymq_jms_example";

    @Bean
    public SQSConnection connectionFactory() throws JMSException {
        final SQSConnectionFactory factory = new SQSConnectionFactory(
                new ProviderConfiguration(),
                AmazonSQSClientBuilder.standard()
                        .withRegion("ru-central1")
                        .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                                "https://message-queue.api.cloud.yandex.net",
                                "ru-central1"
                        ))
        );

        return factory.createConnection();
    }
}
