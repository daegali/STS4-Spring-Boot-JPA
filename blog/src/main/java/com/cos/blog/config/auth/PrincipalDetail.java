package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

import lombok.Data;
import lombok.Getter;

// 스프링 시큐리티가 로그인 요철을 가로채서 로그인을 진행, 완료가 되면 UserDetails 타입의 오브젝트를,
// 스프링 시큐리티의 고유한 세션저장소에 저장을 해준다.
@Getter
public class PrincipalDetail implements UserDetails {
	
			private User user;    // 객체를 품고 있는 것을 (콤포지션)이라 한다.

			public PrincipalDetail(User user) {
				this.user = user;
			}
			
			@Override
			public String getPassword() {
				return user.getPassword();
			}

			@Override
			public String getUsername() {
				return user.getUsername();
			}

			@Override
			public boolean isAccountNonExpired() { // 계정이 만료되지 않았는 지 리턴한다.(true : 만료안됨)
				return true;
			}

			@Override
			public boolean isAccountNonLocked() { // 계정이 잠겨있지 않았는지 리턴한다.(true : 잠기지 않음)
				return true;
			}

			@Override
			public boolean isCredentialsNonExpired() { // 비밀번호가 만료되지 않았는지 리턴한다. (true : 만료안됨)
				return true;
			}

			@Override
			public boolean isEnabled() {// 계정 활성화(사용가능)인지 리턴한다. (true : 활성화)
				return true;
			}
			
			// 계정이 갖고있는 권한 목록을 리턴한다.(권한이 여러개 있을 수 있어서 루프를 돌아야 하는데 우리는 한개만 있다.)
			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				
				Collection<GrantedAuthority> collectors = new ArrayList<>();
				
//				collectors.add(new GrantedAuthority() { // 익명클래스, 자바는 오브젝트를 담을 수 있지만 메소드만 넘길 수 없다.				
//					@Override
//					public String getAuthority() { // 추상클래스
//						return "ROLE_" + user.getRole();   // 스프링에서 ROLE_를 받을 때 앞에 ROLE_는 꼭 붙여야 한다.
//					}
//				});
				// 람다식
				collectors.add(()->{return "ROLE_" + user.getRole();});
				return collectors;
			}

			
			

}
