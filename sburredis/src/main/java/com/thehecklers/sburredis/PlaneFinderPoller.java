package com.thehecklers.sburredis;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@EnableScheduling   // 스케줄 작업 활성화...
@Component
public class PlaneFinderPoller {


    // Reactive를 이용해서 연결할 서버를 지정.
    private WebClient client = WebClient.create("http://localhost:7634/aircraft");
    // 레디스 연결 요소를 통해서 연결 값 불러오기. - RedisConnectionFactory
    private final RedisConnectionFactory connectionFactory;
    private final RedisOperations<String, Aircraft> redisOperations;

    // 생성자를 통해서 클래스의 기능을 사용할 값들을 초기화...(생성자 DI)
    PlaneFinderPoller(RedisConnectionFactory connectionFactory,
                      RedisOperations<String,Aircraft> redisOperations) {
        this.connectionFactory = connectionFactory;
        this.redisOperations = redisOperations;
    }

    // 스케줄 잘업을 통해서 특정 시점마다 동자가하게 설정...
    @Scheduled(fixedDelay = 1000)   // 1초
    private void pollPlanes() {
        // redis 서버의 데이터를 삭제
        connectionFactory.getConnection().flushDb();

        // 리액티브 웹을 통해서 지정된 주소에서 정보를 읽어 들임.
        client.get()
                .retrieve()
                .bodyToFlux(Aircraft.class)
                .filter(plane -> !plane.getReg().isEmpty())
                .toStream()
                .forEach(ac -> redisOperations.opsForValue().set(ac.getReg(), ac));

        redisOperations.opsForValue()
                .getOperations()
                .keys("*")
                .forEach(ac -> System.out.println(redisOperations.opsForValue().get(ac)));
    }



}
