package com.thehecklers.planefinder;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class PlaneFinderController {
    // 서비스 호출
    private final PlaneFinderService pService;

    // 생성자 DI
    public PlaneFinderController(PlaneFinderService pService) {
        this.pService = pService;
    }
    
    @ResponseBody   // 데이터 넘기기
    @GetMapping("/aircraft")
    public Iterable<Aircraft> getCurrentAircrafts() throws IOException {
        return pService.getAircrafts();
    }
    
    
}
