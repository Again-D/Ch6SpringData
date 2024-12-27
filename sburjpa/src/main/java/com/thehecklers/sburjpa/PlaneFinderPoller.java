package com.thehecklers.sburjpa;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@EnableScheduling
@Component
@RequiredArgsConstructor    // 필수로 인자를 넘겨야 하는 생성자
public class PlaneFinderPoller {

    @NonNull  // lombok.NonNull
    private final AircraftRepository repository;
    private WebClient client = WebClient.create("http://localhost:7634/aircraft");

    @Scheduled(fixedDelay = 1000)
    private void pollPlanes(){
//        repository.deleteAll();

        client.get()
                .retrieve()
                .bodyToFlux(Aircraft.class)
                .filter(plane -> !plane.getReg().isEmpty())
                .toStream()
                .forEach(repository::save);

        repository.findAll().forEach(System.out::println);
    }

}
