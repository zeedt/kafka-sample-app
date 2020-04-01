package com.zeed.kafka;

import com.zeed.kafka.entity.User;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class KafkaConsumerWithCustomizedSerializer {

    public static void main (String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "com.zeed.kafka.serializer.UserDeSerializer");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group1");

        KafkaConsumer<String, User> consumer = new KafkaConsumer<String, User>(props);
        List<String> topics = new ArrayList<String>();
        topics.add("customised_app_test_topic");
        consumer.subscribe(topics);

        try {
            while (true) {
                ConsumerRecords<String, User> records = consumer.poll(10);

                for (ConsumerRecord<String, User> record : records) {
                    System.out.println(String.format("Topic: %s , Partition: %s, offset: %s, Key: %s, Value: { -Name: %s -Age: %d -Matured: %s}  ",
                            record.topic(), record.partition(), record.offset(), record.key(), record.value().getName(), record.value().getAge(), record.value().isMatured()));
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            consumer.close();
        }
    }


}
