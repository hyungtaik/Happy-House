package com.ssafy.happyhouse.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.FavDto;
import com.ssafy.happyhouse.dto.HouseDeal;
import com.ssafy.happyhouse.dto.StoreDto;
@Mapper
public interface FavDao {
	/**
	 * 관심지역 검색
	 * @param userId 	id로 사용자 검색
	 * @return 			해당 list 반환
	 */
	public List<FavDto> searchFavArea(String userid) throws SQLException;
	
	/**
	 * 관심지역 등록
	 * @param area	 	관심지역에 등록할 FavDto 데이터
	 * @return 			등록 결과 반환
	 */
	public int insertFavArea(FavDto area) throws SQLException;
	
	/**
	 * 관심지역 삭제
	 * @param userId 	id로 사용자 검색
	 * @return 			삭제 결과 반환
	 */
	public int deleteFavArea(String id) throws SQLException;
	
	
	/**
	 * 사용자의 관심지역의 주택정보
	 * @param dong 동명, limit, offset
	 * @return 주택리스트 반환
	 */
	public List<HouseDeal> searchDong(Map map) throws SQLException;
	
	/**
	 * 사용자의 관심지역의 주택정보의 총개수
	 * @param dong 동명
	 * @return 주택리스트 갯수 반환
	 */
	public int searchDongCnt(String dong) throws SQLException;
	
	/**
	 * 사용자의 관심지역의 상권정보
	 * @param dong 동명
	 * @return 상권리스트 반환
	 */
	public List<StoreDto> searchFavStore(Map map) throws SQLException;
	
	/**
	 * 사용자의 관심지역의 상권정보의 총개수
	 * @param dong 동명
	 * @return 주택리스트 갯수 반환
	 */
	public int searchStoreCnt(String dong) throws SQLException;
}
