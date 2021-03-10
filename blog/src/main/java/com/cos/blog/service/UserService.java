package com.cos.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해준다. IoC를 해준다. 
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	// 회원가입
	@Transactional
	public void join(User user) {
		System.out.println("join user = " + user);
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		// 해쉬화 
		user.setRole(RoleType.USER);
		// role 타입은
		// 강제로 넣어줘야 한다.
		user.setPassword(encPassword);
		userRepository.save(user);
	}
	
	// 회원수정
	@Transactional
	public void userUpdate(User user) {
		// 수정시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고, 영속화된 User 오브젝트를 수정하면 된다.
		// select를 해서 User 오브젝트를 DB로 부터 가져오는 이유는 영속화를 하기 위해서
		// 영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려준다.
		
		User persistance = userRepository.findById(user.getId())
				.orElseThrow(()->{
					return new IllegalArgumentException("회원 찾기 실패");
				});
				String rawPassword = user.getPassword();
				String encPassword = encoder.encode(rawPassword);
				persistance.setPassword(encPassword);
				persistance.setEmail(user.getEmail());
				// 회원수정 함수 종료시 = 서비스 종료 = 트랜잭션종료 = 자동 commit된다.
				// 영속화된 persistance 객체의 변화가 감지되면 더티체킹이 되어 update문을 날려준다.
	}
}

//// 전통적인 로그인
//@Transactional(readOnly = true) // select할 때 트랜잭션 시작, 해당 서비스 종료식에 트랜잭션 종료(정합성 유지)
//public User log(User user) {
//	return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//}
