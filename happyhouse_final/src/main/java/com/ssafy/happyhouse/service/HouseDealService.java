package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.config.PageNavigation;
import com.ssafy.happyhouse.dto.HouseDeal;
import com.ssafy.happyhouse.dto.HousePageBean;

public interface HouseDealService {
   public int searchHouseDealCnt(HousePageBean bean);
   
   public List<HouseDeal> searchDong(HousePageBean bean);
   public List<HouseDeal> searchHouseName(HousePageBean bean);
   //Dong, HouseName 받아서 검색
   public List<HouseDeal> searchMulti(HousePageBean  bean);
   
   public List<HouseDeal> searchAll(HousePageBean  bean);
   
   public List<HouseDeal> searchDetail(int no);
   
   public int insertHouseDeal(HouseDeal houseDeal);

   public List<HouseDeal> searchDong(String dong);
   
   public List<HouseDeal> searchDetail(String AptName);

   PageNavigation makePageNavigation(int currentPage, int sizePerPage, HousePageBean bean) throws Exception;
   
}
