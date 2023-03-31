package com.example.hong.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hong.entity.VO.BehaviorVO;
import com.example.hong.param.BehaviorQueryParam;
import com.example.hong.param.KafkaParam;
import com.example.hong.utils.kafka.KafkaProducerCreator;
import com.example.hong.utils.kafka.KafkaProperties;
import com.example.hong.utils.kafka.KafkaTemplateProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * @ClassName KafkaController
 * @Author Aiden
 * @Version 1.0
 * @Date 2023/3/31 15:40
 * @Description:
 */
@RestController
@RequestMapping("/kafka")
@Slf4j
public class KafkaController {

    private KafkaProducerCreator kafkaProducerCreator;

    private KafkaTemplateProducer kafkaTemplateProducer;

    private Producer<String, String> kafkaProducer;


    public KafkaController(KafkaProducerCreator kafkaProducerCreator, KafkaTemplateProducer kafkaTemplateProducer) {
        this.kafkaProducerCreator = kafkaProducerCreator;
        this.kafkaTemplateProducer = kafkaTemplateProducer;
        this.kafkaProducer = kafkaProducerCreator.createProducer();
    }

    @PostMapping("/produce")
    public void produce(@RequestBody @Validated KafkaParam param) {
        ProducerRecord<String, String> record = new ProducerRecord<>(KafkaProperties.TOPIC, "hello, Kafka! " + param.getIndex());
        try {
            // 发送消息
            RecordMetadata metadata = kafkaProducer.send(record).get();
            log.info("Record sent to partition {} with offset {}", metadata.partition(), metadata.offset());
        } catch (ExecutionException | InterruptedException e) {
            log.debug("Error in sending record,{}", e.getMessage());
        }
    }
    @PostMapping("/send")
    public void send(@RequestBody @Validated KafkaParam param) {
        kafkaTemplateProducer.send("KafkaTemplat hello, Kafka! " + param.getIndex());
    }
}
