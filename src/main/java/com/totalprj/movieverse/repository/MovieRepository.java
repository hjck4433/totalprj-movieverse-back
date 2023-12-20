package com.totalprj.movieverse.repository;

import com.totalprj.movieverse.entity.Member;
import com.totalprj.movieverse.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByTitleAndDirectorNm(String title, String directorNm);
    boolean existsByTitleAndDirectorNm(String title, String directorNm);
    Optional<Movie> findByTitle(String title);
    List<Movie> findByTitleContaining(String title);
}
