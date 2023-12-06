package com.totalprj.movieverse.repository;

import com.totalprj.movieverse.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

}
