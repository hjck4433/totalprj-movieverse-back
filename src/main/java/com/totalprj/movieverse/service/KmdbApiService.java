package com.totalprj.movieverse.service;

import com.totalprj.movieverse.dto.MovieDto;
import com.totalprj.movieverse.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@EnableScheduling
public class KmdbApiService {
    private final MovieRepository movieRepository;
    private final MovieService movieService;

    @Bean
    @Scheduled(cron = "0 5 * * * *") // 스케줄러에 등록 후 매분 실행
    public void movieScheduler() {
        log.info("schedule start!");
        List<MovieDto> response = kmdbApiList();
        log.info("python response : {}", response);
        List<MovieDto> checkedList = movieService.checkExist(response);
        movieService.saveMovieList(checkedList);
        log.info("schedule end!");
    }

    public List<MovieDto> kmdbApiList() {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:5000/api/apilist";
        ResponseEntity<List<MovieDto>> responseEntity = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MovieDto>>() {});
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            log.error("Request failed with status code: {}", responseEntity.getStatusCodeValue());
            return null;
        }
    }


}
