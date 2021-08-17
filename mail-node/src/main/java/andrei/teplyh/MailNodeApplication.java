package andrei.teplyh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MailNodeApplication {
    public static void main(String[] args) {
        SpringApplication.run(MailNodeApplication.class, args);
    }
}
