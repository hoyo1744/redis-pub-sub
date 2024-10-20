package com.example.redis.service;

import com.example.redis.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisPubService {
    
    private final RedisMessageListenerContainer redisMessageListenerContainer;
    
    private final RedisPulisher redisPulisher;
    
    private final RedisSubscribeLisnter redisSubscribeLisnter;


    /**
     * Channel 별 Message 전송
     * @param channel
     * @param message
     */
    public void pubMsgChannel(String channel, MessageDto message) {
//        1. 요청한 Channel을 구독
        redisMessageListenerContainer.addMessageListener(redisSubscribeLisnter, new ChannelTopic(channel));
        
//        2. Message 전송
        redisPulisher.publish(new ChannelTopic(channel), message);
    }

    /**
     * 메시지만 전송(String)
     */
    public void pubMsg(String channel, String message) {
        redisPulisher.publish(new ChannelTopic(channel), message);
    }

    /**
     * 메시지만 전송(Dto)
     */
    public void pubMsg(String channel, MessageDto messageDto) {
        redisPulisher.publish(new ChannelTopic(channel), messageDto);
    }



    /**
     * Channel 구독 취소
     * @param channel
     */
    public void cancelSubChannel(String channel) {
        redisMessageListenerContainer.removeMessageListener(redisSubscribeLisnter, new ChannelTopic(channel));
    }
    
    
}
