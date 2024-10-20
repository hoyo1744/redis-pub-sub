package com.example.redis.controller;

import com.example.redis.dto.MessageDto;
import com.example.redis.service.RedisPubService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/redis/pubsub")
public class publishController {


    private final RedisPubService redisPubService;

    @PostMapping("/send")
    public void sendMessage(@RequestParam(required = true) String channel, @RequestBody MessageDto messageDto) {
        log.info("Redis Pub MSG Channel = {}", channel);

        redisPubService.pubMsgChannel(channel, messageDto);
    }

    @PostMapping("/sendMsg")
    public void sendOnlyMessage(@RequestParam(required = true) String channel, String message) {
        log.info("Redis Pub Only MSG Channel = {}", channel);
        redisPubService.pubMsg(channel, message);
    }

    @PostMapping("/sendMsgDto")
    public void sendOnlyMessageDto(@RequestParam(required = true) String channel, @RequestBody MessageDto messageDto) {
        log.info("Redis Pub Only MSG Dto Channel = {}", channel);
        redisPubService.pubMsg(channel, messageDto);
    }

    @PostMapping("/cancel")
    public void cancelSubChannel(@RequestParam String channel) {
        redisPubService.cancelSubChannel(channel);
    }





}
