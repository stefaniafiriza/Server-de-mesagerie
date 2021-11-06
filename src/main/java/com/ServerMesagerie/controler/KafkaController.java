package com.ServerMesagerie.controler;

import com.ServerMesagerie.consumer.TopicConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KafkaController {

    @Autowired
    private KafkaTemplate<String, String> template;
    private TopicConsumer topicConsumer;

    public KafkaController(KafkaTemplate<String, String> template, TopicConsumer topicConsumer) {
        this.template = template;
        this.topicConsumer = topicConsumer;
    }

    @GetMapping("/producer")
    public void producer(@RequestParam String message) {
        template.send("myTopic", message);
    }

    @GetMapping("/messages")
    public List<String> getMessages() {
        return topicConsumer.getMessages();
    }

}
