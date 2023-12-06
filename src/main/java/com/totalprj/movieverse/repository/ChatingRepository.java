package com.totalprj.movieverse.repository;

import com.totalprj.movieverse.entity.Chating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatingRepository extends JpaRepository<Chating, Long> {
//    List<Chating> findByChating(Chating chating);
}
