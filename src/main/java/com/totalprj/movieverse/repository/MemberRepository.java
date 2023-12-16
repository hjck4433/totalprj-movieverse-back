package com.totalprj.movieverse.repository;

import com.totalprj.movieverse.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByEmail(String email);
    boolean existsByAlias(String alias);
    boolean existsByPhone(String phone);

    boolean existsByPassword(String password);
    Optional<Member> findByEmail(String email);
    Optional<Member> findByEmailAndPassword(String email, String password);
}
