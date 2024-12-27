package com.thehecklers.sburmongo;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
@EnableScheduling
public class PlaneFinderPoller {

    // reactive web 클라이언트로 데이터 로드...
    private WebClient client = WebClient.create("http://localhost:7634/aircraft");

    @NonNull
    private final AircraftRepository repository;

    @Scheduled(fixedDelay = 5000)
    private void pollPlanes() {
        repository.deleteAll();

        client.get()
                .retrieve()
                .bodyToFlux(Aircraft.class)
                .filter(plane -> !plane.getReg().isEmpty())
                .toStream()
                .forEach(repository::save);

        repository.findAll().forEach(System.out::println);
    }


}
