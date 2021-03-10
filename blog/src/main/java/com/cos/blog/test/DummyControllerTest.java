package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		System.out.println("id : " + user.getId());
		System.out.println("createDate : " + user.getCreateDate());
		System.out.println("role : " + user.getRole());

		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다";
	}

	// 주소로 파라미터를 전달받을 수 있음
	// select 1명
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// return type은 optionaly이다 . 만약 니가 user/4을 찾으면 내가 데이터베이스에서 찾지 못하고 프로그램에 문제가 생긴다
		// Optional로 너의 User 객체를 감싸서 가져와서 null인지 아닌지 판단 해줘				
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. " + id);
			}
		});
		// 람다식
//		User user = userRepository.findById(id).orElseThrow(()->{
//			return new IllegalArgumentException("해당 사용자는 없습니다.");
//		});
		// 요청 : 웹 브라우저
		// user 객체 = 자바 오브젝트
		// 웹 브라우저가 이해할수 있는 데이터료 변환 -> json 사용해서 변경
		// 스프링부트 = MessageConverter라는 애가 응답시에 자동 작동 , 만약에 자바 오브젝트를 리턴하면 
		// MessageConverter가 Jackson이라는 라이브러리를 호출해서
		// user 오브젝트를 json으로 변환해서 브라우저에게 던져준다.
		return user;
	}
	
	// 데이터를 한번에 받아오기
	@GetMapping("/dummy/user")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	// 페이지 설정
	@GetMapping("/dummy/user/page")
	public Page<User> pagelist(@PageableDefault(size = 2, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
		
		Page<User> pagingUser = userRepository.findAll(pageable);
		List<User> users = pagingUser.getContent();
		return pagingUser;
	}
	// save 함수는 id를 전달하지 않으면 insert 해주고, 아이디를 전달해 주면 해당 아이디에 대한 데이터가 있으면 update 해주고
	// 해당 아이디에 대한 데이터가 없으면 insert 한다.
	// 업데이트
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정 실패");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		//userRepository.save(user);
		
		// 더티 체킹
		return user;
	}
	
	// 삭제
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
		userRepository.deleteById(id);
		}catch(EmptyResultDataAccessException e){
			return "삭제 실패. 해당 id디는 DB에 없음";
		}
		return "삭제 완료" +id;
	}
	
}
