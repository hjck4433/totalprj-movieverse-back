package com.totalprj.movieverse.repository;

import com.totalprj.movieverse.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByMemberEmail(String email);

}
