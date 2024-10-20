package com.example.redis.service;

import com.example.redis.dto.MessageDto;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
public class RedisPulisher {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisPulisher(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * Object Publish
     * @param topic
     * @param dto
     */
    public void publish(ChannelTopic topic, MessageDto dto) {
        redisTemplate.convertAndSend(topic.getTopic(), dto);

    }


    /**
     * String publish
     * @param topic
     * @param data
     */
    public void publish(ChannelTopic topic, String data) {
        redisTemplate.convertAndSend(topic.getTopic(), data);
    }
}
