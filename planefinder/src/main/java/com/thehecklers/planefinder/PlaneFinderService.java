package com.thehecklers.planefinder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PlaneFinderService {

    // 필드
    private final PlaneFinderRepository repo;
    private final FlightGenerator generator;
    private URL acURL;
    private final ObjectMapper om;

    // 생성자 ... DI ( 의존성 주입 - Dependency Injection)
    @SneakyThrows
    public PlaneFinderService(PlaneFinderRepository repo, FlightGenerator generator) {
        this.repo = repo;
        this.generator = generator;

        this.acURL = new URL("http://localhost/ajax/aircraft"); // 실제 통신 X - 비행기 정보를 얻는 주소
        this.om = new ObjectMapper();
    }

    // 메서드 구현...
    public Iterable<Aircraft> getAircrafts() throws IOException {
        // 비행기 위치 정보를 저장할 List 객체 생성
        List<Aircraft> positions = new ArrayList<>();

        // JsonNode 객체 선언
        JsonNode aircraftNodes = null;
        try {
            // 지정 URL에서 JSON 정보를 받아올 경우의 코드...
            aircraftNodes = om.readTree(acURL.openStream()).get("aircraft");
            aircraftNodes.iterator().forEachRemaining(node -> {
               try {
                   positions.add(om.treeToValue(node, Aircraft.class));
               } catch (JsonProcessingException e) {
                   e.printStackTrace();
               }
            });
        }catch (IOException e){
            // 테스트에서 사용될 영역 !! 여기..
            System.out.println("\n>>> IO Exception : "+e.getMessage() +",generating and providing sample data\n");
            // 샘플 데이터 만드는 코드
            return saveSamplePositions();

        }

        // 정상적인 응답이 있는 경우..
        if (positions.size() >0) {
            positions.forEach(System.out::println);
            repo.deleteAll();
            return repo.saveAll(positions);
        }else {
            System.out.println("\n No positions to report, generating and providing sample data. \n");
            return saveSamplePositions();
        }

    }

    private  Iterable<Aircraft> saveSamplePositions(){
        final Random rand = new Random();

        repo.deleteAll();   // 기존 DB 정보를 삭제
        // 새롭게 Aircraft 정보를 새엇ㅇ
        for (int i = 0;i< rand.nextInt(15)+1;i++){
            repo.save(generator.generate()); // 생성된 Aircraft 객체 정보를 DB에 저장 
        }
        return repo.findAll();      // 저장된 전체값을 반환
    }



}
