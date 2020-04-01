package com.zeed.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class KafkaConsumerApp4 {


    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group");


        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        List<String> topics = new ArrayList<String>();
//        topics.add("replicated_topic");
        topics.add("app_test_topic");
        consumer.subscribe(topics);

            try {
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(10);

                    for (ConsumerRecord<String, String> record : records) {
                        System.out.println(String.format("Topic: %s , Partition: %s, offset: %s, Key: %s, Value: %s", record.topic(), record.partition(), record.offset(), record.key(), record.value()));
                    }
                }
            } catch(Exception e){
                e.printStackTrace();
            } finally{
                consumer.close();
            }


    }
}
