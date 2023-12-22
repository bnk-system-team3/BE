package ssg.com.houssg.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import ssg.com.houssg.dto.BoardDto;
import ssg.com.houssg.dto.LanguageCategoryDto;
import ssg.com.houssg.dto.UserDto;
import ssg.com.houssg.dto.UserProfileDto;
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
	@PostMapping("/login")
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
			System.out.println(dto.getUserId());
			user.setLastLoginDate(new Date());
			service.updateLastLoginDate(user);

			System.out.println("로그인 성공" + new Date());

			return ResponseEntity.ok().build(); // 로그인 성공 응답
		} else {
			return ResponseEntity.badRequest().build(); // 로그인 실패 응답
		}
	}

	// 로그아웃
	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpSession session) {
		// 세션을 무효화하여 로그아웃 처리
		session.invalidate();
		System.out.println("로그아웃 성공" + new Date());
		return ResponseEntity.ok().build();
	}

	// 닉네임 중복확인
	@PostMapping("nicknameCheck")
	public String nicknameCheck(String nickname) {
		System.out.println("UserController nicknameCheck(String nickname) " + new Date());

		int count = service.nicknameCheck(nickname);
		if (count == 0) {
			return "YES";
		}

		return "NO";
	}

	// 회원가입
	@PostMapping("/signUp")
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
	@PostMapping("updatePw")
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

	// 기술스택 조회
	@GetMapping("/languageCategories")
	public ResponseEntity<List<LanguageCategoryDto>> getLanguageCategories() {
		List<LanguageCategoryDto> languageCategories = service.findLanguage();
		return ResponseEntity.ok(languageCategories);
	}

	// 포지션 업데이트
	@PatchMapping("updatePosition")
	public ResponseEntity<String> updatePosition(@RequestParam("position") String position, HttpSession session) {

		String userId = (String) session.getAttribute("userId");

		if (userId == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("세션이 유효하지 않습니다.");
		}

		UserDto user = new UserDto();
		user.setUserId(userId);
		user.setPosition(position);

		service.updatePosition(user);

		return ResponseEntity.ok("포지션이 업데이트되었습니다.");
	}

	// 사용자의 기술 스택 업데이트
	@PostMapping("updateTechStack")
	public ResponseEntity<String> updateTechStack(@RequestParam("techStack") List<String> techStack,
			HttpSession session) {

		// 세션에서 userId 가져오기
		String userId = (String) session.getAttribute("userId");

		if (userId == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("세션이 유효하지 않습니다.");
		}

		try {
			// 기존 기술 스택 삭제
			service.deleteUserTechStack(userId);

			// 새로운 기술 스택 추가
			service.updateUserTechStack(userId, techStack);

			return ResponseEntity.ok("기술 스택이 업데이트되었습니다.");
		} catch (Exception e) {
			// 예외 처리 로직 추가 (예: 로깅)
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("기술 스택 업데이트에 실패했습니다.");
		}
	}

	@GetMapping("/findOtherProfile")
	public ResponseEntity<UserProfileDto> getOtherProfile(@RequestParam String userId) {
		UserProfileDto userProfile = service.getUserProfile(userId);
		if (userProfile == null) {
			// 사용자를 찾을 수 없는 경우
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(userProfile);
		}
	}

	// 내 프로필 조회
	@GetMapping("/findUserProfile")
	public ResponseEntity<UserProfileDto> getUserProfile(HttpSession session) {
		// 세션에서 userId 가져오기
		String userId = (String) session.getAttribute("userId");

		if (userId == null) {
			// 세션에 userId가 없는 경우
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}

		UserProfileDto userProfile = service.getUserProfile(userId);

		if (userProfile == null) {
			// 사용자를 찾을 수 없는 경우
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(userProfile);
		}
	}

	// 마이페이지 - 내가 가입한 보드 조회
	@GetMapping("/myBoards")
	public ResponseEntity<List<BoardDto>> getMyBoards(HttpSession session) {
		// 세션에서 사용자 아이디 가져오기
		String userId = (String) session.getAttribute("userId");

		// 세션에 사용자 아이디가 없으면 UNAUTHORIZED 응답 반환
		if (userId == null) {
			return ResponseEntity.status(401).build();
		}

		// 사용자가 가입한 게시글 조회
		List<BoardDto> myBoards = service.getMyboard(userId);

		// 조회 결과에 따라 응답 반환
		if (myBoards != null && !myBoards.isEmpty()) {
			return ResponseEntity.ok(myBoards);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

}
