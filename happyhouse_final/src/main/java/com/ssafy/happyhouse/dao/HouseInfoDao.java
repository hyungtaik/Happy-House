package com.ssafy.happyhouse.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.dto.HouseDeal;
import com.ssafy.happyhouse.dto.HouseInfo;
import com.ssafy.happyhouse.dto.HousePageBean;

public interface HouseInfoDao {

	public List<HouseInfo> searchAllDong(HousePageBean bean) throws SQLException;

	public List<HouseInfo> searchAllApt(HousePageBean bean) throws SQLException;

	public HouseInfo search(HouseDeal deal) throws SQLException;

	/** HouseInfo DB에 위도 경도 입력하기 위해서 등록된 모든 집의 동과 지번을 추출한다. */
	public List<HouseInfo> searchAllHouseInfo(String dong) throws SQLException;
	public int searchAllHouseInfoCnt(String dong) throws SQLException;
	public List<HouseInfo> searchAllHouseInfoPage(HousePageBean dong) throws SQLException;
	/////////////////////////////////////////////////////////////////
	public int insertHouseInfo1(HouseInfo houseInfo) throws SQLException;

	public int insertHouseInfo2(HouseDeal deal) throws SQLException;
}
