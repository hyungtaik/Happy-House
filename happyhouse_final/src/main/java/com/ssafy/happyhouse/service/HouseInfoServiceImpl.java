package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.HappyHouseException;
import com.ssafy.happyhouse.dao.HouseInfoDao;
import com.ssafy.happyhouse.dto.HouseDeal;
import com.ssafy.happyhouse.dto.HouseInfo;
import com.ssafy.happyhouse.dto.HousePageBean;

@Service
public class HouseInfoServiceImpl implements HouseInfoService{
	
	@Autowired
	private HouseInfoDao dao;

	/**
	 * 검색 조건(key) 검색 단어(word)에 해당하는 아파트 거래 정보(HouseInfo)를  검색해서 반환.  
	 * @param bean  검색 조건과 검색 단어가 있는 객체
	 * @return 조회한 주택 목록
	 */
	@Override
	public List<HouseInfo> searchAll(HousePageBean  bean){
		try {
			if(bean.getDong()!=null) {
				return dao.searchAllDong(bean);
			}else {
				return dao.searchAllApt(bean);
			}
		} catch (SQLException e) {
			throw new HappyHouseException("주택정보 조회 중 오류 발생");
		}
	}
	
	/**
	 * 아파트 식별 번호에 해당하는 아파트 거래 정보를 검색해서 반환. 
	 * @param no	검색할 아파트 식별 번호
	 * @return		아파트 식별 번호에 해당하는 아파트 거래 정보를 찾아서 리턴한다, 없으면 null이 리턴됨
	 */
	@Override
	public HouseInfo search(HouseDeal deal) {
		try {
			HouseInfo info = dao.search(deal);
			if(info==null) {
				throw new HappyHouseException(String.format("거래번호 %d번에 해당하는 주택 거래정보가 존재하지 않습니다.", deal.getNo()));
			}
			return info;
		} catch (SQLException e) {
			throw new HappyHouseException("주택정보 조회 중 오류 발생");
		}
	}

	@Override
	public int insertHouseInfo1(HouseInfo houseInfo) {
		try {
			int res = dao.insertHouseInfo1(houseInfo);
			if(res <= 0) {
				throw new HappyHouseException(String.format("거래번호 %d번에 해당하는 주택 거래정보가 존재하지 않습니다.", houseInfo.getNo()));
			}
			return res;
		} catch (SQLException e) {
			throw new HappyHouseException("주택정보 조회 중 오류 발생");
		}
	}
	@Override
	public int insertHouseInfo2(HouseDeal deal) {
		try {
			int res = dao.insertHouseInfo2(deal);
			if(res <= 0) {
				throw new HappyHouseException(String.format("거래번호 %d번에 해당하는 주택 거래정보가 존재하지 않습니다.", deal.getNo()));
			}
			return res;
		} catch (SQLException e) {
			throw new HappyHouseException("주택정보 조회 중 오류 발생");
		}
	}
	@Override
	public List<HouseInfo> searchAllHouseInfo(String dong) {
		try {
			return dao.searchAllHouseInfo(dong);
		} catch (SQLException e) {
			throw new HappyHouseException("주택정보 조회 중 오류 발생");
		}
	}
	@Override
	public int searchAllHouseInfoCnt(String dong) {
		try {
			return dao.searchAllHouseInfoCnt(dong);
		} catch (SQLException e) {
			throw new HappyHouseException("주택정보 조회 중 오류 발생");
		}
	}
	
	@Override
	public List<HouseInfo> searchAllHouseInfoPage(HousePageBean bean) {
		try {
			return dao.searchAllHouseInfoPage(bean);
		} catch (SQLException e) {
			throw new HappyHouseException("주택정보 조회 중 오류 발생");
		}
	}
}
