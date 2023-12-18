package ssg.com.houssg.util;

import java.security.MessageDigest;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import ssg.com.houssg.dto.UserDto;

@Component
public class UserUtil {

    public boolean isValidUser(UserDto user) {
        // 필수 입력 여부 확인
        if (isNullOrEmpty(user.getUserId()) || isNullOrEmpty(user.getPassword()) || isNullOrEmpty(user.getNickname()) || isNullOrEmpty(user.getEmail()) ) {
            System.out.println("회원가입 실패: 필수 입력 항목이 비어 있습니다.");
            return false;
        }

        if (!isValidUserId(user.getUserId())) {
            System.out.println("회원가입 실패: 아이디가 유효하지 않습니다.");
            return false;
        }

        if (!isValidPassword(user.getPassword())) {
            System.out.println("회원가입 실패: 비밀번호가 유효하지 않습니다.");
            return false;
        }

        if (!isValidNickname(user.getNickname())) {
            System.out.println("회원가입 실패: 닉네임이 유효하지 않습니다.");
            return false;
        }

        // 이메일 유효성 검사 (선택사항)
        if (!isValidEmail(user.getEmail())) {
            System.out.println("회원가입 실패: 이메일 형식이 유효하지 않습니다.");
            return false;
        }

        // 비밀번호 해시화
        String hashedPassword = hashPassword(user.getPassword());
        System.out.println("원본 비밀번호: " + user.getPassword());
        System.out.println("암호화된 비밀번호: " + hashedPassword);
        user.setPassword(hashedPassword);

        return true;
    }

    public boolean isValidUserId(String userId) {
        // 아이디 길이와 특수문자 검사
        return userId.length() >= 5 && userId.length() <= 10 && Pattern.matches("^[a-zA-Z0-9_]+$", userId);
    }

    public boolean isValidPassword(String password) {
        // 비밀번호 길이와 조합 검사
        return password.length() >= 8 && password.length() <= 20 &&
               Pattern.matches("^(?=.*[a-z])(?=.*\\d)(?=.*[!@#_])[A-Za-z\\d!@#_]+$", password);
    }

    public boolean isValidNickname(String nickname) {
        // 닉네임 길이와 특수문자 검사
        return nickname.length() >= 2 && nickname.length() <= 8 && Pattern.matches("^[가-힣a-zA-Z0-9]+$", nickname);
    }

    public boolean isValidEmail(String email) {
        // 이메일 유효성 검사
        return Pattern.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", email);
    }
    
    // 현재 비밀번호 검증
    public boolean verifyCurrentPassword(String password, String PasswordFromDB) {
    	String hashedEnteredPassword = hashPassword(password);
    	System.out.println("입력한 것" + hashedEnteredPassword);
    	System.out.println(PasswordFromDB);
        return hashedEnteredPassword.equals(PasswordFromDB);
    }


    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
