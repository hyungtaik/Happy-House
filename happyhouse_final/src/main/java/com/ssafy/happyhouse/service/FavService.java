package com.ssafy.happyhouse.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.dto.FavDto;
import com.ssafy.happyhouse.dto.HouseDeal;
import com.ssafy.happyhouse.dto.StoreDto;


public interface FavService {

	/**
	 * 사용자의 관심지역 찾기 - userid로 사용자가 설정해놓은 관심지역리스트 찾기
	 * @param id 사용자 아이디
	 * @return 찾기 성공시 관심지역 리스트 반환
	 */
	public List<FavDto> searchFavArea(String id);
	
	/**
	 * 사용자의 관심지역 등록 - favoritearea(관심지역)에 등록
	 * @param area 등록할 지역
	 * @return 등록 결과 반환
	 */
	public int insertFavArea(FavDto area);
	
	/**
	 * 사용자의 관심지역 삭제 - 관심지역 삭제
	 * @param id 사용자 아이디
	 * @return 삭제 결과 반환
	 */
	public int deleteFavArea(String id);
	
	
	/**
	 * 사용자의 관심지역의 주택정보
	 * @param dong 동명, limit, offset
	 * @return 주택리스트 반환
	 */
	public List<HouseDeal> selectApt(Map map);

	
	/**
	 * 사용자의 관심지역의 주택정보의 총갯수
	 * @param dong 동명
	 * @return 주택리스트 갯수 반환
	 */
	public int selectAptCnt(String dong);
	
	
	/**
	 * 사용자의 관심지역의 상권정보
	 * @param dong 동명, limit, offset
	 * @return 상권리스트 반환
	 */
	public List<StoreDto> searchFavStore(Map map);
	
	/**
	 * 사용자의 관심지역의 상점정보의 총갯수
	 * @param dong 동명
	 * @return 상권리스트 갯수 반환
	 */
	public int selectStoreCnt(String dong);
}
