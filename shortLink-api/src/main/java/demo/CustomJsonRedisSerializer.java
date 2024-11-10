package demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @Author : just do it
 * @Date : 2024/11/5 21:00
 * @ApiNote :
 */

public class CustomJsonRedisSerializer implements RedisSerializer<Object> {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(Object o) throws SerializationException {
        try {
            return objectMapper.writeValueAsString(o).getBytes(StandardCharsets.UTF_8);
        } catch (JsonProcessingException e) {
            throw new SerializationException("Error serializing object to JSON", e);
        }
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null) {
            return null;
        }
        try {
            return objectMapper.readValue(bytes, Object.class);
        } catch (IOException e) {
            throw new SerializationException("Error deserializing JSON", e);
        }
    }
}