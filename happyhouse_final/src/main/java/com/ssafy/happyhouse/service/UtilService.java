package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.dto.UtilDto;
import com.ssafy.happyhouse.dto.HouseDeal;

public interface UtilService {

	/**
	 * getcity - city(시/도) 구하기
	 * 
	 * @return 시/도 리스트 반환
	 */
	public List<UtilDto> getcity();

	/**
	 * getgu - gu(구) 구하기
	 * 
	 * @param city 시/도
	 * @return 군/구 리스트 반환
	 */
	public List<UtilDto> getgu(String city);

	/**
	 * getdong - dong(동) 구하기
	 * 
	 * @param gu 군/구
	 * @return 동 리스트 반환
	 */
	public List<UtilDto> getdong(String gu);
	
	public List<HouseDeal> selectApt(String dong);
	
	public String getdongcode(String dong);
}
