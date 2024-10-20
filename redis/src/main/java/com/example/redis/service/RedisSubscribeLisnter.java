package com.example.redis.service;

import com.example.redis.dto.MessageDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisSubscribeLisnter implements MessageListener {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;


    @Override
    public void onMessage(Message message, byte[] pattern) {

        String publishMessage = redisTemplate
                .getStringSerializer().deserialize(message.getBody());

        try {
            MessageDto messageDto = objectMapper.readValue(publishMessage, MessageDto.class);

            log.info("Redis Subcribe Channel : " + messageDto.getRoomId());
            log.info("Redis SUB Message : {}", publishMessage);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }


    }
}
