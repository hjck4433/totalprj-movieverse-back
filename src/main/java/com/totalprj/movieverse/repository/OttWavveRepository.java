package com.totalprj.movieverse.repository;

import com.totalprj.movieverse.entity.OttWavve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OttWavveRepository extends JpaRepository<OttWavve, Long> {
}
