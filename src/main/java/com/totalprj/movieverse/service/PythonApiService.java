package com.totalprj.movieverse.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;


@Slf4j
@Service
@RequiredArgsConstructor
@EnableScheduling
public class PythonApiService {

//    @Bean
//    @Scheduled(cron = "0 1 * * * *")
//    public void startScheduler(){
//        log.info("schedule start!");
//        List<Map<String, List<Map<String, String>>>> response = fetchDataFromPythonServer();
//        log.info("python response : {}", response);
//    }
//
//    public List<Map<String, List<Map<String, String>>>> fetchDataFromPythonServer() {
//        RestTemplate restTemplate = new RestTemplate();
//        String apiUrl = "http://127.0.0.1:5000/api/kmdblist";
//        ResponseEntity<List<Map<String, List<Map<String, String>>>>> responseEntity = restTemplate.exchange(
//                apiUrl,
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<Map<String, List<Map<String, String>>>>>() {});
//
//        if (responseEntity.getStatusCode().is2xxSuccessful()) {
//            return responseEntity.getBody();
//        } else {
//            log.error("Request failed with status code: {}", responseEntity.getStatusCodeValue());
//            return null;
//        }
//    }

//// DB에 저장한 정보 비우기
//
//// 현재상영작 list(Map) 저장
//
//// ott(Netflix) list(Map) 저장
//
//// 현재상영작(watcha) list(Map) 저장
//
//// 현재상영작(tving) list(Map) 저장


}


