package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.dto.UserDto;

public interface UserService {

	/**
	 * 로그인 - userId와 userPw로 회원 확인 
	 * @param userId 사용자 아이디
	 * @param uwerPw 사용자 패스워드
	 * @return 로그인 성공 여부, 로그인 성공시 true return
	 */
	public UserDto login(UserDto dto);
	
	/**
	 * 회원가입 - userId 겹치는지 확인하고 회원가입시키기
	 * @paramfavoritArea	선택된 관심구역 (~~구)
	 * @param User	회원가입할 정보
	 * @return		회원가입 성공 여부, 회원가입 성공시 true return
	 */
	public boolean register(UserDto user);
	
	/**
	 * 비밀번호 찾기 - userId와 email로 회원 비밀번호 찾기
	 * @param userId 사용자 아이디
	 * @param email  사용자 이메일
	 * @return 비밀번호, 찾기 성공시 비밀번호 반환
	 */
	public UserDto findPwd(UserDto dto);
	
	/**
	 * 정보수정 - userId로 회원찾아서 정보수정
	 * @param User	수정할 정보
	 * @return		수정 성공 여부, 수정 성공시 true return
	 */
	public boolean modify(UserDto user);
	
	/**
	 * 검색 - 	
	 * @param userId	id로 사용자 검색
	 * @return			해당 user 반환
	 */
	public UserDto search(String id);
	
	/**
	 * 삭제
	 * @param userId 	id로 사용자 검색
	 * @return 			삭제 성공 여부, 삭제 성공시 false return
	 */
	public boolean delete(String id);
	
	public UserDto emailcheck(String email);
}
