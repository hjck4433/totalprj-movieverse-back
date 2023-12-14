package com.totalprj.movieverse.service;


import com.totalprj.movieverse.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class MovieSaveService {
//    @Value("${api.serviceKey}")`
    private String key;
    private final MovieRepository movieRepository;

    @Scheduled(cron = "0 0 0 * * *") // 스케줄러에 등록 후 매일 자정에 실행하는 어노테이션
    public void startScheduler(){
        String response = movieListApi();
//        listFromJsonObj(response);
    }


    public String movieListApi(){
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);
        UriComponents uri = UriComponentsBuilder
                .fromUriString("http://api.koreafilm.or.kr")
                .path("/openapi-data2/wisenut/search_api/search_json2.jsp")
                .queryParam("service", key) // 인증키 (필수)
                .queryParam("collection")
                .queryParam("title")
                .queryParam("releaseDate", 100) // 페이지 당 목록 수 (필수)
                .encode() // utf-8 로 인코딩
                .build();

        ResponseEntity<String> responseEntity = rest.exchange(uri.toUri(), HttpMethod.GET, requestEntity, String.class);
        String response = responseEntity.getBody();
        return response;
    }

//    public boolean listFromJsonObj(String result){
//        // xml 데이터를 json 데이터로 변환
//        JSONObject xmlToJson = XML.toJSONObject(result);
//
//        // JSONObject로 데이터 가져오기
//        JSONObject jsonObj = xmlToJson.getJSONObject("dbs");
//
//        // 배열형식이니 JSONArray로 가져오기
//        JSONArray jsonArr = jsonObj.getJSONArray("db");
//
//        //db에 저장
//        for(int i = 0; i < jsonArr.length(); i++){
//            JSONObject item = (JSONObject) jsonArr.get(i);
////            PlayInfo playInfo = new PlayInfo(item);
//            movieRepository.save(playInfo);
//        }
//        System.out.println("DB에 저장");
//        return true;
//    }
}

