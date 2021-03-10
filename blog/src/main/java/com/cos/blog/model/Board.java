package com.cos.blog.model;


import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.transaction.Transactional;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
			
				@Id
				@GeneratedValue(strategy = GenerationType.IDENTITY)
				private int id;
				
				@Column(nullable = false, length = 100)
				private String title;
				
				@Lob
				private String content; // 섬머노트 라이브러리 <html> 태그가 섞여서 저장된다.
				
	
				private int count; // 조회수
				
				// Many=Board, User = One
				@ManyToOne(fetch = FetchType.EAGER)  //ManyToOne 기본 전략이 fetch=FetchType.EAGER이다.
				@JoinColumn(name = "userId")
				private User user;  // DB는 오브젝트를 저장할 수 없다.  FK, 자바는 오브젝트를 저장할 수 있다. 
				
				 // mappendBy 를 들고 있으면 연관관계의 주인이 아니다 (난 FK가 아니다)DB에 칼럼을 만들지 마라.
				@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // OneToMany 기본 전략이 fetch=FetchType.LAZY 전략이다. 안적어저도 된다.
				private List<Reply> reply;
				
				@CreationTimestamp
				private Timestamp createDate; 
				
}
