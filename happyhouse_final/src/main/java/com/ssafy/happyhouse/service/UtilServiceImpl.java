package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.HappyHouseException;
import com.ssafy.happyhouse.dao.UtilDao;
import com.ssafy.happyhouse.dto.UtilDto;
import com.ssafy.happyhouse.dao.HouseDealDao;
import com.ssafy.happyhouse.dto.HouseDeal;

@Service
public class UtilServiceImpl implements UtilService {
	
	@Autowired
	UtilDao dao;
	
	@Autowired
	HouseDealDao hdao;

	@Override
	public List<UtilDto> getcity(){
		try {
			return dao.getcity();
		} catch (SQLException e) {
			throw new HappyHouseException("시/도 검색중 오류 발생");
		}
	}
	
	@Override
	public List<UtilDto> getgu(String city){
		try {
			return dao.getgu(city);
		} catch (SQLException e) {
			throw new HappyHouseException("군/구 검색중 오류 발생");
		}
	}

	@Override
	public List<UtilDto> getdong(String gu){
		try {
			return dao.getdong(gu);
		} catch (SQLException e) {
			throw new HappyHouseException("동 검색중 오류 발생");
		}
	}
	
	@Override
	public List<HouseDeal> selectApt(String dong){
		try {
			List<HouseDeal> list = hdao.searchDong2(dong);
			
			return list;
		} catch (SQLException e) {
			throw new HappyHouseException("주택 정보 조회중 오류 발생");
		}
	}
	
	@Override
	public String getdongcode(String dong){
		try {
			return dao.getdongcode(dong);
		} catch (SQLException e) {
			throw new HappyHouseException("주택 정보 조회중 오류 발생");
		}
	}
}
