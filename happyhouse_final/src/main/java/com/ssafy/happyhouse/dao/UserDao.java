package com.ssafy.happyhouse.dao;


import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.UserDto;

@Mapper
public interface UserDao {

	/**
	 * 로그인 - userId와 userPw로 회원 확인 
	 * @param userId 사용자 아이디
	 * @param uwerPw 사용자 패스워드
	 * @return 로그인된 사용자, 로그인 성공시 해당 사용자 정보 return
	 */
	public UserDto login(UserDto dto) throws SQLException;
	
	/**
	 * 회원가입 - userId 겹치는지 확인하고 회원가입시키기
	 * @paramfavoritArea	선택된 관심구역 (~~구)
	 * @param User	회원가입할 정보
	 * @return		회원가입 성공 여부, 회원가입 성공시 true return
	 */
	public boolean register(UserDto user) throws SQLException;
	
	/**
	 * 비밀번호 찾기 - userId와 email로 회원 비밀번호 찾기
	 * @param userId 사용자 아이디
	 * @param email  사용자 이메일
	 * @return 비밀번호, 찾기 성공시 비밀번호 반환
	 */
	public UserDto findPwd(UserDto dto) throws SQLException;
	
	/**
	 * 정보수정 - userId로 회원찾아서 정보수정
	 * @param User	수정할 정보
	 * @return		수정 성공 여부, 수정 성공시 true return
	 */
	public boolean modify(UserDto user) throws SQLException;
	
	/**
	 * 검색 - 	
	 * @param userId	id로 사용자 검색
	 * @return			해당 user 반환
	 */
	public UserDto search(String id) throws SQLException;
	
	/**
	 * 삭제
	 * @param userId 	id로 사용자 검색
	 * @return 			삭제 성공 여부, 삭제 성공시 false return
	 */
	public boolean delete(String id) throws SQLException;
	
	public UserDto emailcheck(String email) throws SQLException;
}

