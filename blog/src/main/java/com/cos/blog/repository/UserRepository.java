package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

// DAO, 자동으로 bean으로 등록이 된다.
// @Repository 생략 가능
public interface UserRepository extends JpaRepository<User, Integer> {

		// SELECT * FROM user WHERE username = ?
		Optional<User> findByUsername(String username);
}

// JPA Naming 전략 쿼리 전략
// SELECT * FROM user WHERE username = ? and password = ? 쿼리문 자동 실행
//User findByUsernameAndPassword(String username, String password); 전통적인 로그인

//@Query(value = "SELECT * FROM user WHERE username = ?1 and password = ?2", nativeQuery = true )
//User login(String username, String password);
