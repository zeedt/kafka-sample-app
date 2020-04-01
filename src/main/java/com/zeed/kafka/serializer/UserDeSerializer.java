package com.zeed.kafka.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zeed.kafka.entity.User;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class UserDeSerializer implements Deserializer<User> {

    ObjectMapper objectMapper = new ObjectMapper();


    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    public User deserialize(String s, byte[] bytes) {
        try {
            return objectMapper.readValue(bytes, User.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void close() {

    }
}
