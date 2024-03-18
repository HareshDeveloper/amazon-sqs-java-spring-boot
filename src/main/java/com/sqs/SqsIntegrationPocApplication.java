package com.example.SQS_Integration_POC;

import com.example.SQS_Integration_POC.service.MessagePublisher;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SqsIntegrationPocApplication {

    public static void main(String[] args) {
        SpringApplication.run(SqsIntegrationPocApplication.class, args);
    }
    @Bean
    public ApplicationRunner runner(MessagePublisher publisher) {
        return args -> {
            Thread.sleep(3000);
            for (int i = 0; i < 10; i++) {
                publisher.publishMessage(String.valueOf(i));
            }
        };
    }
}
