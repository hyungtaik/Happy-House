package com.ssafy.happyhouse.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.dto.UtilDto;

public interface UtilDao {
	/**
	 * getcity - 시/도 구하기
	 * 
	 * @param city 시/도
	 * @return 군/구 리스트 반환
	 */
	public List<UtilDto> getcity() throws SQLException;

	/**
	 * getgu - gu(구) 구하기
	 * 
	 * @param city 시/도
	 * @return 군/구 리스트 반환
	 */
	public List<UtilDto> getgu(String city) throws SQLException;

	/**
	 * getdong - dong(동) 구하기
	 * 
	 * @param gu 군/구
	 * @return 동 리스트 반환
	 */
	public List<UtilDto> getdong(String gu) throws SQLException;
	
	
	public String getdongcode(String dong) throws SQLException;
	
}
