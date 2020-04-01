package com.zeed.kafka.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zeed.kafka.entity.User;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class UserSerializer implements Serializer<User> {

    ObjectMapper objectMapper = new ObjectMapper();

    public void configure(Map configs, boolean isKey) {

    }

    public byte[] serialize(String stringValue, User o) {
        byte[] value = null;
        try {
            value = objectMapper.writeValueAsString(o).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }


    public void close() {

    }
}
