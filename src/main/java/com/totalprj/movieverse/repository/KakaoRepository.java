package com.totalprj.movieverse.repository;

import com.totalprj.movieverse.entity.Kakao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KakaoRepository extends JpaRepository<Kakao, Long> {
}
