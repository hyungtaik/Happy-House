package com.ssafy.happyhouse.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.dto.HouseDeal;
import com.ssafy.happyhouse.dto.HousePageBean;

public interface HouseDealDao {
	// 선택된 type의 총 거래 정보 량 구하기
	int searchHouseDealCnt(HousePageBean bean) throws SQLException;
	int searchHouseDealCnt1(HousePageBean bean) throws SQLException;
	int searchHouseDealCnt2(HousePageBean bean) throws SQLException;
	int searchHouseDealCnt3(HousePageBean bean) throws SQLException;

	public List<HouseDeal> searchDong1(HousePageBean bean) throws SQLException;
	public List<HouseDeal> searchDong2(String dong) throws SQLException;

	public List<HouseDeal> searchHouseName(HousePageBean bean) throws SQLException;

	// Dong, HouseName 받아서 검색
	public List<HouseDeal> searchMulti1(HousePageBean bean) throws SQLException;
	public List<HouseDeal> searchMulti2(HousePageBean bean) throws SQLException;
	public List<HouseDeal> searchMulti3(HousePageBean bean) throws SQLException;

	public List<HouseDeal> searchAll(HousePageBean bean) throws SQLException;

	public List<HouseDeal> searchDetail1(int no) throws SQLException;
	public List<HouseDeal> searchDetail2(String AptName) throws SQLException;

	public int insertHouseDeal(HouseDeal houseDeal) throws SQLException;

}