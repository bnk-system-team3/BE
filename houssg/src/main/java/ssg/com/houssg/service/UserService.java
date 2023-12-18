package ssg.com.houssg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ssg.com.houssg.dto.UserDto;
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

}