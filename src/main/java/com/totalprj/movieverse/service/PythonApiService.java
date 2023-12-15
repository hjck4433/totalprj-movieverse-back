package com.totalprj.movieverse.service;

import com.totalprj.movieverse.entity.Movie;
import com.totalprj.movieverse.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PythonApiService {
    private final MovieRepository movieRepository;

    // 파이썬 DB 내용 불러오기
//    public static void pythonData() {
//        String apiUrl = "http://127.0.0.1:5000/api/kmdblist";
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<List<Map<String, List<Map<String, String>>>>> responseEntity = restTemplate.getForEntity(
//                apiUrl,
//                new ParameterizedTypeReference<List<Map<String, List<Map<String, String>>>>>() {
//                });
//        )
//    }


// DB에 저장한 정보 비우기

// 현재상영작 list(Map) 저장

// ott(Netflix) list(Map) 저장

// 현재상영작(watcha) list(Map) 저장

// 현재상영작(tving) list(Map) 저장


}


