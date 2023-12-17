package com.totalprj.movieverse.service;

import com.totalprj.movieverse.entity.Boxoffice;
import com.totalprj.movieverse.entity.OttNetflix;
import com.totalprj.movieverse.entity.OttTving;
import com.totalprj.movieverse.entity.OttWatcha;
import com.totalprj.movieverse.repository.BoxofficeRepository;
import com.totalprj.movieverse.repository.OttNetflixRepository;
import com.totalprj.movieverse.repository.OttTvingRepository;
import com.totalprj.movieverse.repository.OttWatchaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OttBoxService {
    private final OttNetflixRepository netflixRepository;
    private final OttWatchaRepository watchaRepository;
    private final OttTvingRepository tvingRepository;
    private final BoxofficeRepository boxofficeRepository;







}
