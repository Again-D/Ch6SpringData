package com.thehecklers.sburredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@SpringBootApplication
public class SburredisApplication {

    // redisOperations 객체는 redis에 저장할 키와 값 형태의 테이터 타입에 대해 시리얼라이즈를 사용
    // 상호간 변화가 잘 이뤄지게 만들어 전달하는 템플릿을 반환.
    @Bean
    public RedisOperations<String, Aircraft> redisOperations(RedisConnectionFactory factory) {
        // 객체 정보를 JSON 형태로 Redis에 시리얼라이즈 작업 진행
        Jackson2JsonRedisSerializer<Aircraft> serializer = new Jackson2JsonRedisSerializer<>(Aircraft.class);
        // 템플릿을 통해서 키와 값을 구분하여 데이터를 처리
        RedisTemplate<String, Aircraft> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);  // template에 연결 정보를 설정.
        template.setDefaultSerializer(serializer);
        template.setKeySerializer(new StringRedisSerializer());
        // 만들어진 템플릿 반환.
        return template;
    }


    public static void main(String[] args) {
        SpringApplication.run(SburredisApplication.class, args);
    }

}
