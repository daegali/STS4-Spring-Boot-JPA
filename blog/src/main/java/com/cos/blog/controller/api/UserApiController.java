package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;

	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {// json이라 RequestBody 사용
		System.out.println("UserApiController : save 호출됨" + user);
		user.setRole(RoleType.USER);
		userService.join(user);
		// 실제로 DB에 insert를 하고 아래에서 return이 되면 된다.
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 자바오브젝트를 JSON으로 변환해서 리턴 (Jackson)
	}
	
	// 회원 수정
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user){ // @RequestBody가 없으면 json코드로 받지 못한다.
		userService.userUpdate(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
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
