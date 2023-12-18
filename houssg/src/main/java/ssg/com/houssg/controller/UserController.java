package ssg.com.houssg.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.function.ServerRequest.Headers;

import com.cloudinary.Url;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import ssg.com.houssg.dto.UserDto;

import ssg.com.houssg.service.UserService;
import ssg.com.houssg.util.UserUtil;

@RestController
@RequestMapping("user")
public class UserController {


	@Autowired
	private UserService service;

	@Autowired
	private UserUtil userUtil;


	// 로그인
	@PostMapping("/log-in")
	public ResponseEntity<?> login(@RequestBody UserDto user, HttpSession session) {
		System.out.println("UserController login(UserDto user) " + new Date());
		System.out.println("클라이언트로 부터 받은 데이터 : " + user.toString());

		user.setPassword(userUtil.hashPassword(user.getPassword())); // 사용자가 입력한 비밀번호를 DB에 있는 hashedPW로 변경한 후에 로그인 진행
		UserDto dto = service.login(user);
		if (dto != null) {
            // 로그인이 성공한 경우 세션에 사용자 정보 저장
            session.setAttribute("userId", dto.getUserId());
            session.setAttribute("email", dto.getEmail());
            session.setAttribute("nickname", dto.getNickname());
            session.setAttribute("point", dto.getPoint());
            session.setAttribute("department", dto.getDepartment());
            
            System.out.println("로그인 성공" + new Date());

            return ResponseEntity.ok().build(); // 로그인 성공 응답
        } else {
            return ResponseEntity.badRequest().build(); // 로그인 실패 응답
        }
    }
	
	// 로그아웃
	@PostMapping("/log-out")
	public ResponseEntity<?> logout(HttpSession session) {
	    // 세션을 무효화하여 로그아웃 처리
	    session.invalidate();
	    System.out.println("로그아웃 성공" + new Date());
	    return ResponseEntity.ok().build();
	}


	// 닉네임 중복확인
	@PostMapping("nickname-check")
	public String nicknameCheck(String nickname) {
		System.out.println("UserController nicknameCheck(String nickname) " + new Date());

		int count = service.nicknameCheck(nickname);
		if (count == 0) {
			return "YES";
		}

		return "NO";
	}

	// 회원가입
	@PostMapping("/sign-up")
	public String signUp(@RequestBody UserDto user) {
		System.out.println("UserController signUp(UserDto dto) " + new Date());

		System.out.println("클라이언트로 부터 받은 데이터 : " + user.toString());

		// UserDto 객체를 UserUtil을 사용하여 검사
		UserUtil userUtil = new UserUtil();
		if (!userUtil.isValidUser(user)) {
			System.out.println("회원가입 실패");
			return "NO";
		}
		int count = service.signUp(user);
		if (count > 0) {
			return "YES";
		}
		System.out.println("회원가입 실패2");

		return "NO";
	}

	
	// 비밀번호 변경
	@PostMapping("update-pw")
	public ResponseEntity<String> updatePassword(@RequestParam("userId") String userId,
			@RequestParam("newPassword") String newPassword) {
		UserUtil userUtil = new UserUtil();

		if (userUtil.isNullOrEmpty(userId) || userUtil.isNullOrEmpty(newPassword)) {
			System.out.println("ID, 패스워드 입력 오류");
			return ResponseEntity.badRequest().body("아이디, 비밀번호 란이 공백입니다.");
		}

		// 비밀번호 유효성 검사
		if (!userUtil.isValidPassword(newPassword)) {
			System.out.println("비밀번호 유효성 검사 실패");
			return ResponseEntity.badRequest().body("비밀번호가 유효하지 않습니다.");
		}

		// 아이디를 사용하여 비밀번호 변경 대상 사용자를 검색
		UserDto foundUser = service.findUserById(userId);

		if (foundUser == null) {
			System.out.println("사용자 없음");
			return ResponseEntity.badRequest().body("해당 아이디를 가진 사용자를 찾을 수 없습니다.");
		}

		// 비밀번호 해싱
		String hashedPassword = userUtil.hashPassword(newPassword);

		// UserDto에 해싱된 새로운 비밀번호 설정
		foundUser.setPassword(hashedPassword);

		// 비밀번호 업데이트 메서드 호출
		int rowsAffected = service.updatePassword(foundUser);

		if (rowsAffected > 0) {
			return ResponseEntity.ok("비밀번호가 성공적으로 재설정되었습니다.");
		} else {
			return ResponseEntity.badRequest().body("비밀번호 재설정에 실패했습니다.");
		}
	}

	
}
