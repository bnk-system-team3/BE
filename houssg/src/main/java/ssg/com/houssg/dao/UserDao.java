package ssg.com.houssg.dao;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import ssg.com.houssg.dto.UserDto;

@Mapper
@Repository
public interface UserDao {
	
	// 닉네임 중복확인
	int nicknameCheck(String nickname);
	
	// 회원가입
	int signUp(UserDto dto);
	
	// 로그인
	UserDto login(UserDto dto);
	
	// 유저 조회
	UserDto findUserById(String userId);
	
	// 마이페이지 닉네임 변경
	void changeNickname(String userId, String nickname);

	// 패스워드 업데이트
	int updatePassword(UserDto user);
	
	// 비밀번호 찾기
	UserDto findUserByIdPhonNumber(String userId, String phone_number);
	
	// 마이페이지 비밀번호 찾기
	String findPasswordById(String userId);

}
