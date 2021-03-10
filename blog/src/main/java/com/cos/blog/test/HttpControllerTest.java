package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
//사용자가 요청 -> 응답(Html 파일) @Controller
// 사용자가 요청 -> 응답(Data) @RestController
@RestController 
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = Member.builder().username("asd123").password("1234").email("asd123@nate.com").build();
		System.out.println(TAG + ": getter : " + m.getUsername());
		m.setUsername("1234");
		System.out.println(TAG + ": setter : " + m.getUsername());
		
		return "lombok test 완료";
	}
	
	@GetMapping("/http/get")
	public String getTest(Member m) {
	
		return "get 요청, " + m.getId() + "," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
	}
	@PutMapping("http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청, " + m.getId() + "," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
	}
	@PostMapping("http/post")
	public String postTest(@RequestBody Member m) { // MessageConverter(스프링부트) 클래스가 자동으로 일해준다.
		return "post 요청, " + m.getId() + "," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();

	}
	@DeleteMapping("http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}
