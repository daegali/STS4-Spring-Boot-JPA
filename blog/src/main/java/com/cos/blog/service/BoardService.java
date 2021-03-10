package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;


// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해준다. IoC를 해준다. 
@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	

	// 회원가입
	@Transactional
	public void write(Board board, User user) {
		System.out.println("Board = " + board);
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	
	}
	// 글 목록
	public Page<Board> writeList(Pageable pageable){
		return boardRepository.findAll(pageable);
	}
	
	// 글 상세보기
	@Transactional(readOnly = true)
	public Board view(int id) {
		return boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
				});
	}
	
	// 삭제하기
	@Transactional
	public void deletes(int id) {
		 boardRepository.deleteById(id);			
	}
	
	// 글 수정하기
	@Transactional
	public void writeupdate(int id, Board requestBoard) {
		//영속화
		Board board = boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
				});
				board.setTitle(requestBoard.getTitle());
				board.setContent(requestBoard.getContent());
				// 해당 함수 종료시 Service가 종료될 때 트랜잭션 종료. 이때 더티체킹 발생 - 자동 업데이트가 됨. db flush
	}
}

//// 전통적인 로그인
//@Transactional(readOnly = true) // select할 때 트랜잭션 시작, 해당 서비스 종료식에 트랜잭션 종료(정합성 유지)
//public User log(User user) {
//	return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//}
