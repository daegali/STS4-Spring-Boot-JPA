package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller  // 해당경로 이하에 있는 파일을 리턴
public class TempControllerTest {
	
			@GetMapping("/temp/home")
			public String tempHome() {
				System.out.println("tempHome");
				return "/home.html";
			}
			
			@GetMapping("/temp/img")
			public String tempimg() {
				return "/a.jpg";
			}
			// static 파일에는 정적인 파일만 넣을 수 있다. 하지만 jsp 파일은 동적인 파일이라 src/main/ 밑에 webapp/WEB-INF-views/로 바꿔주어야 한다.
}
