package ssg.com.houssg.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ssg.com.houssg.dto.BoardDto;
import ssg.com.houssg.dto.LanguageCategoryDto;
import ssg.com.houssg.dto.UserDto;
import ssg.com.houssg.dto.UserProfileDto;
import ssg.com.houssg.dao.UserDao;

@Service
@Transactional
public class UserService {

	@Autowired
	UserDao dao;

	// 회원가입
	public int signUp(UserDto dto) {
		return dao.signUp(dto);
	}

	// 닉네임 중복체크
	public int nicknameCheck(String nickname) {
		return dao.nicknameCheck(nickname);
	}

	// 로그인
	public UserDto login(UserDto dto) {
		return dao.login(dto);
	}

	// 유저 식별
	public UserDto findUserById(String userId) {
		return dao.findUserById(userId);
	}

	// 닉네임 변경
	public void changeNickname(String userId, String nickname) {
		dao.changeNickname(userId, nickname);
	}

	// 비밀번호 찾기
	public UserDto findUserByIdPhonNumber(String userId, String phone_number) {
		return dao.findUserByIdPhonNumber(userId, phone_number);
	}

	// 비밀번호 업데이트
	public int updatePassword(UserDto user) {
		return dao.updatePassword(user);
	}

	// 마이페이지 비밀번호 찾기
	public String findPasswordById(String userId) {
		return dao.findPasswordById(userId);
	}

	// 마지막 로그인 날짜 업데이트
	public void updateLastLoginDate(UserDto user) {
		dao.updateLastLoginDate(user);
	}

	// 기술스택 조회
	public List<LanguageCategoryDto> findLanguage() {
		return dao.findLanguage();
	}

	// 포비션 업데이트
	public void updatePosition(UserDto user) {
		dao.updatePosition(user);
	}

	// 사용자의 기술 스택 삭제

	public void deleteUserTechStack(String userId) {
		dao.deleteUserTechStack(userId);
	}

	// 사용자의 기술 스택 업데이트
	public void updateUserTechStack(String userId, List<String> techStackList) {

		// 새로운 기술 스택 추가
		for (String tech : techStackList) {
			Map<String, Object> params = new HashMap<>();

			params.put("userId", userId);
			params.put("tech", tech);

			// languageId 조회
			Integer languageId = dao.selectLanguageId(tech);
			params.put("languageId", languageId);

			// 사용자 기술 스택 삽입
			dao.insertUserTechStack(params);
		}
	}

	// 유저 프로필 조회
	public UserProfileDto getUserProfile(String userId) {
		return dao.getUserProfile(userId);
	}

	// 마이페이지 - 내가 가입한 모임 조회
	public List<BoardDto> getMyboard(String userId) {
		return dao.getMyboard(userId);
	}

}