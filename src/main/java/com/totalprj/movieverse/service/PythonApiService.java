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
    public static void pythonData() {
        String apiUrl = "http://127.0.0.1:5000/api/kmdblist";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Map<String, List<Map<String, String>>>>> responseEntity = restTemplate.getForEntity(
                apiUrl,
                new ParameterizedTypeReference<List<Map<String, List<Map<String, String>>>>>() {
                });
        )

    }

// 파이썬에서 끌어온 내용 DB저장 (영화가 있으면 패스 없으면 추가)
    public boolean moviePythonSave(String title, String directorNm) {
        Movie movie = new Movie();
        try {
//            Optional을 사용하면 호출자는 null 체크를 하지 않아도 되며,
//            대신 Optional의 메서드들을 사용하여 데이터의 존재 여부를 확인하고
//            데이터를 가져올 수 있음.
            Optional<Movie> existingMovie = movieRepository.findBytitleAnddirectorNm(title, directorNm);

            if (existingMovie.isPresent()) {
                // 이미 데이터가 존재하면 추가 작업을 수행하지 않고 패스
                System.out.println("영화가 이미 존재합니다.");
            } else {
                // 데이터가 없으면 새로운 데이터 추가 작업을 수행
                System.out.println("영화 존재하지않아 추가합니다.");
                movie.setTitle();
                movie.setPosters(movie.getPosters());
                movie.setTitleEng();
                movie.setReprlsDate();
                movie.setGenre();
                movie.setNation();
                movie.setRating();
                movie.setRuntime();
                movie.setAudiAcc();
                movie.setDirectorNm();
                movie.setActorNm();
                movie.setPlotText();
                movie.setStlls();

            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

// DB에 저장한 정보 비우기

// 현재상영작 list(Map) 저장

// ott(Netflix) list(Map) 저장

// 현재상영작(watcha) list(Map) 저장

// 현재상영작(tving) list(Map) 저장


}


