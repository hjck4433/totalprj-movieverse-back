package com.totalprj.movieverse.service;

import com.totalprj.movieverse.entity.Member;
import com.totalprj.movieverse.entity.Movie;
import com.totalprj.movieverse.repository.BookmarkRepository;
import com.totalprj.movieverse.repository.MemberRepository;
import com.totalprj.movieverse.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookMarkService {
    private final BookmarkRepository bookmarkRepository;
    private final MovieRepository movieRepository;
    private final MemberRepository memberRepository;

    public boolean isBookMarked(Long memberId, Long movieId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("해당 회원이 존재 하지 않습니다."));
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("영화가 존재 하지 않습니다."));

        boolean isBookMark = bookmarkRepository.existsByMemberAndMovie(member, movie);
        log.info("{} bookMarked {} ",movie.getTitle(), isBookMark );
        return isBookMark;
    }
}
