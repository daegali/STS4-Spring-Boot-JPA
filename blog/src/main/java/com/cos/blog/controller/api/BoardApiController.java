package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.service.BoardService;

@RestController
public class BoardApiController {

	@Autowired
	private BoardService boardService;
	
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board,  @AuthenticationPrincipal PrincipalDetail principal) {
		System.out.println("BoardApiController : save 호출됨" + board);
		boardService.write(board, principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); 
	}
	// 삭제하기
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id){
		boardService.deletes(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); 
	}
	
	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board){
		boardService.writeupdate(id, board);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); 
	}
}

/*
 * @Autowired private HttpSession session;
 */
//		전통적인 로그인
/*
 * @PostMapping("api/user/login") public ResponseDto<Integer> login(@RequestBody
 * User user, HttpSession session){
 * System.out.println("UserApiController : login호출");
 * 
 * User principal = userService.log(user); // principal (접근 주체)
 * 
 * if(principal != null) { session.setAttribute("principal", principal); }
 * return new ResponseDto<Integer>(HttpStatus.OK.value(),1); }
 */
