package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.HappyHouseException;
import com.ssafy.happyhouse.dao.UserDao;
import com.ssafy.happyhouse.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao dao;
	
	@Override
	public UserDto login(UserDto dto) {
		try {
			UserDto user = dao.login(dto);
			return user;
		} catch (SQLException e) {
			throw new HappyHouseException("로그인 정보 조회중 오류 발생");
		}
	}

	@Override
	public boolean register(UserDto user) {
		try {
			return dao.register(user);
		} catch (SQLException e) {
			throw new HappyHouseException("회원 정보 등록중 오류 발생");
		}
	}

	@Override
	public UserDto findPwd(UserDto dto) {
		try {
			return dao.findPwd(dto);
		} catch (SQLException e) {
			throw new HappyHouseException("회원 비밀번호 검색중 오류 발생");
		}
	}
	
	@Override
	public UserDto search(String id) {
		try {
			return dao.search(id);
		} catch (SQLException e) {
			throw new HappyHouseException("회원 정보 조회중 오류 발생");
		}
	}

	@Override
	public boolean modify(UserDto user) {
		try {
			return dao.modify(user);
		} catch (SQLException e) {
			throw new HappyHouseException("회원 정보 수정중 오류 발생");
		}
	}

	@Override
	public boolean delete(String id) {
		try {
			return dao.delete(id);
		} catch (SQLException e) {
			throw new HappyHouseException("회원 정보 삭제중 오류 발생");
		}
	}
	@Override
	public UserDto emailcheck(String email) {
		try {
			return dao.emailcheck(email);
		} catch (SQLException e) {
			throw new HappyHouseException("이메일 체크 중 오류 발생");
		}
	}
}
