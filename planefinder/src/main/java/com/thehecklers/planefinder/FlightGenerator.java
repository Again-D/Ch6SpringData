package com.thehecklers.planefinder;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

// 비행기 정보를 랜덤하게 만들어주는 컴포넌트
@Component
public class FlightGenerator {
    // 랜덤 객체 생성
    private final Random rnd = new Random();
    // 비행기 타입 리스트 생성
    List<String> typeList = List.of(
            "A319", "A320", "A321",
            "BE33", "BE36",
            "B737", "B739", "B763",
            "C172", "C402", "C560",
            "E50P", "E75L",
            "MD11",
            "PA28", "PA32", "PA46");

    // Aircraft 객체 생성 메서드
    Aircraft generate() {
        String csfn = "SAL" + rnd.nextInt(1000);
        return new Aircraft(csfn,
                "N" + String.format("%1$5s",rnd.nextInt(10000)).replace(' ','0'),
                csfn,
                typeList.get(rnd.nextInt(typeList.size())), // type
                rnd.nextInt(400000),    // altitude
                rnd.nextInt(360),       // heading
                rnd.ints(1,100,500).iterator().next().intValue(),   // speed
                rnd.doubles(1,35d,42d).iterator().next().floatValue(),  // latitude
                rnd.doubles(1,-115d,-83d).iterator().next().floatValue()    // longitude
                );
    }

}
