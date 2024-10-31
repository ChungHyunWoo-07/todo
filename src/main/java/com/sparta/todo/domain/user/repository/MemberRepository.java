package com.sparta.todo.domain.user.repository;


import com.sparta.todo.domain.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}