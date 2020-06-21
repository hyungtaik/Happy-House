package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.HappyHouseException;
import com.ssafy.happyhouse.dao.FavDao;
import com.ssafy.happyhouse.dto.FavDto;
import com.ssafy.happyhouse.dto.HouseDeal;
import com.ssafy.happyhouse.dto.StoreDto;

@Service
public class FavServiceImpl implements FavService {

	@Autowired
	FavDao dao;
	
	@Override
	public List<FavDto> searchFavArea(String id) {
		try {
			return dao.searchFavArea(id);
		} catch (SQLException e) {
			throw new HappyHouseException("관심 지역 검색중 오류 발생");
		}
	}

	@Override
	public int insertFavArea(FavDto area) {
		try {
			return dao.insertFavArea(area);
		} catch (SQLException e) {
			throw new HappyHouseException("관심 지역 등록중 오류 발생");
		}
	}

	@Override
	public int deleteFavArea(String id) {
		try {
			return dao.deleteFavArea(id);
		} catch (SQLException e) {
			throw new HappyHouseException("관심 지역 삭제중 오류 발생");
		}
	}
	
	@Override
	public List<HouseDeal> selectApt(Map map){
		try {
			List<HouseDeal> list = dao.searchDong(map);
			return list;
		} catch (SQLException e) {
			throw new HappyHouseException("주택 정보 조회중 오류 발생");
		}
	}
	
	@Override
	public int selectAptCnt(String dong){
		try {
			int cnt = dao.searchDongCnt(dong);
			return cnt;
		} catch (SQLException e) {
			throw new HappyHouseException("주택 정보 조회중 오류 발생");
		}
	}

	
	@Override
	public List<StoreDto> searchFavStore(Map map){
		try {
			List<StoreDto> list = dao.searchFavStore(map);
			return list;
		} catch (SQLException e) {
			throw new HappyHouseException("상권 정보 조회중 오류 발생");
		}
	}
	
	@Override
	public int selectStoreCnt(String dong){
		try {
			int cnt = dao.searchStoreCnt(dong);
			return cnt;
		} catch (SQLException e) {
			throw new HappyHouseException("상권 정보 조회중 오류 발생");
		}
	}

}
