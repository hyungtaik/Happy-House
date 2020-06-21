package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.HouseDeal;
import com.ssafy.happyhouse.dto.HouseInfo;
import com.ssafy.happyhouse.dto.HousePageBean;
public interface HouseInfoService {
	
	public List<HouseInfo> searchAll(HousePageBean  bean);
	
	public HouseInfo search(HouseDeal deal);

	public int insertHouseInfo1(HouseInfo houseInfo);
	
	public int insertHouseInfo2(HouseDeal deal);
	
	/**HouseInfo DB에 위도 경도 입력하기 위해서  등록된 모든 집의 동과 지번을 추출한다.  */
	public List<HouseInfo> searchAllHouseInfo(String dong);
	public int searchAllHouseInfoCnt(String dong);
	
	public List<HouseInfo> searchAllHouseInfoPage(HousePageBean dong);
}
