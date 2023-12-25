package com.totalprj.movieverse.repository;

import com.totalprj.movieverse.entity.Board;
import com.totalprj.movieverse.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByTitleContaining(String keyword);
    Page<Board> findAll(Pageable pageable);
    List<Board> findByMember(Member member);

//    @Query("SELECT b FROM Board b WHERE " +
//            "(LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//            "LOWER(b.boardContent) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND " +
//            "LOWER(b.category.categoryName) LIKE LOWER(CONCAT('%', :categoryName, '%')) AND " +
//            "LOWER(b.gatherType) LIKE LOWER(CONCAT('%', :gatherType, '%'))")
//    Page<Board> findByKeywordAndCategoryNameAndGatherType(
//            @Param("keyword") String keyword,
//            @Param("categoryName") String categoryName,
//            @Param("gatherType") String gatherType,
//            Pageable pageable
//    );
@Query("SELECT b FROM Board b WHERE " +
        "(:keyword IS NULL OR (:keyword IS NOT NULL AND " +
        "(LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
        "LOWER(b.boardContent) LIKE LOWER(CONCAT('%', :keyword, '%'))))) AND " +
        "LOWER(b.category.categoryName) LIKE LOWER(CONCAT('%', :categoryName, '%')) AND " +
        "LOWER(b.gatherType) LIKE LOWER(CONCAT('%', :gatherType, '%'))")
Page<Board> findByKeywordAndCategoryNameAndGatherType(
        @Param("keyword") String keyword,
        @Param("categoryName") String categoryName,
        @Param("gatherType") String gatherType,
        Pageable pageable
);
}