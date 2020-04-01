package com.zeed.kafka;

import com.zeed.kafka.entity.User;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaProducerWithCustomizedSerializer {

    public static void main (String[] args) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "com.zeed.kafka.serializer.UserSerializer");

        KafkaProducer<String, User> kafkaProducer = new KafkaProducer<String, User>(props);

        try {
            kafkaProducer.send(new ProducerRecord<String, User>("customised_app_test_topic", new User("Olamide", 9, true)));
            kafkaProducer.send(new ProducerRecord<String, User>("customised_app_test_topic", new User("Shola", 9, false)));
            kafkaProducer.send(new ProducerRecord<String, User>("customised_app_test_topic", new User("Shola", 9, true)));
        } finally {
            kafkaProducer.close();
        }
    }


}
