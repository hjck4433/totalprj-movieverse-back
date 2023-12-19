package com.totalprj.movieverse.repository;

import com.totalprj.movieverse.entity.Bookmark;
import com.totalprj.movieverse.entity.Member;
import com.totalprj.movieverse.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    boolean existsByMemberAndMovie(Member member, Movie movie);
}
