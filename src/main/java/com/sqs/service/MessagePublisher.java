package com.example.SQS_Integration_POC.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Date;
import com.example.SQS_Integration_POC.model.Message;
@Service
@Log4j2
public class MessagePublisher {

    @Value("${aws.sqs.queueName}")
    private String queueName;

    @Value("${aws.sqs.queueUrl}")
    private String queueUrl;

    private final AmazonSQS amazonSQSClient;
    private final ObjectMapper objectMapper;

    public MessagePublisher(AmazonSQS amazonSQSClient, ObjectMapper objectMapper) {
        this.amazonSQSClient = amazonSQSClient;
        this.objectMapper = objectMapper;
    }

    public void publishMessage(String id) {
        try {
            Message message = Message.builder()
                    .id(id)
                    .content("message check")
                    .createdAt(new Date()).build();
            SendMessageResult result = amazonSQSClient.sendMessage(queueUrl, objectMapper.writeValueAsString(message));
        } catch (Exception e) {
            log.error("Queue Exception Message: {}", e.getMessage());
        }

    }

}
