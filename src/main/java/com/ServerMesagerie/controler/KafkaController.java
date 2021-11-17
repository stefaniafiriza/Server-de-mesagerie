package com.ServerMesagerie.controler;

import com.ServerMesagerie.consumer.Messages;
import com.ServerMesagerie.consumer.TopicConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class KafkaController {

    @Autowired
    private KafkaTemplate<String, Object> template;
    private TopicConsumer topicConsumer;

    public KafkaController(KafkaTemplate<String, Object> template, TopicConsumer topicConsumer) {
        this.template = template;
        this.topicConsumer = topicConsumer;
    }

    @GetMapping("/producer")
    public void producer(
            @RequestParam ("destination") String destination,
            @RequestParam("textMessage") String textMessage
            ) {
        Messages message = new Messages(destination,textMessage);
        ListenableFuture<SendResult<String, Object>> future = template.send("myTopic", message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onSuccess(SendResult<String, Object> result) {
                System.out.println("Sent message=[" + message.getTextMessage() +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + message.getTextMessage() + "] due to : " + ex.getMessage());
            }
        });
    }

    @GetMapping("/messages")
    public List<String> getMessages() {
        return topicConsumer.getMessages();
    }

}
