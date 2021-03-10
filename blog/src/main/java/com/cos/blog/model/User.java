package com.cos.blog.model;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴
// @DynamicInsert
@Entity
public class User {
			@Id
			@GeneratedValue(strategy = GenerationType.IDENTITY)
			private int id; // 오라클에서는 시퀀스, mySQL에서는 auto_increment전략
			
			@Column(nullable = false, length = 30, unique = true) // unique = true 중복 방지
			private String username; //아이디
			
			@Column(nullable = false, length = 100)
			private String password; // 비밀번호
			
			@Column(nullable = false, length = 50)
			private String email;	// 이메일
			
			// DB는 RoleType라는 것이 없다. 따라서 밑에 어노테이션을 붙여서 해당 Enum이 스트링이라고 알려줘야 한다.
			// @ColumnDefault("user")
			@Enumerated(EnumType.STRING)
			private RoleType role; // 정확하게 하려면 Enum을 쓰는게 좋다.(회원의 등급을 설정), 도매인 = 어떤 범위
			
			@CreationTimestamp
			private Timestamp createDate;  
			
}
