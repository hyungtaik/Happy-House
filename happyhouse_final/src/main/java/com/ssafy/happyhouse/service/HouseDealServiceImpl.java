package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.HappyHouseException;
import com.ssafy.happyhouse.config.PageNavigation;
import com.ssafy.happyhouse.dao.HouseDealDao;
import com.ssafy.happyhouse.dto.HouseDeal;
import com.ssafy.happyhouse.dto.HousePageBean;

@Service
public class HouseDealServiceImpl implements HouseDealService {
	
	@Autowired
	HouseDealDao dao;

	@Override
	public int searchHouseDealCnt(HousePageBean bean) {
		int searchCnt = 0;
		try {
			String dong = bean.getDong();
			String apt = bean.getaptName();
			if(dong!=null && apt!=null ) {
				searchCnt = dao.searchHouseDealCnt1(bean);
			}else if(dong!=null) {
				searchCnt = dao.searchHouseDealCnt3(bean);
			}else if(apt!=null) {
				searchCnt = dao.searchHouseDealCnt2(bean);
			}else {
				searchCnt = dao.searchHouseDealCnt(bean);
			}
			return searchCnt;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HappyHouseException("주택 거래 정보 갯수 조회 중 오류 발생");
		}
	}
	

	@Override
	public List<HouseDeal> searchDong(HousePageBean bean) {
		List<HouseDeal> deal = null;
		List<HouseDeal> kmp_deal = null;
		
		try {
			String pattern = "";
			String dong = bean.getDong();
			String apt = bean.getaptName();
			String typeStr = "(";
			boolean[] type = bean.getSearchType();
			for(int i = 0; i < type.length; ++i) {
				if(type[i]) {
					typeStr+=(i+1)+",";
				}
			}
			typeStr = typeStr.substring(0,typeStr.length()-1)+")";
			boolean check = true;
			bean.setTypeStr(typeStr);
			if(dong!=null && apt!=null ) {
				check = false;
				deal = dao.searchMulti1(bean);
				pattern = bean.getDong();
			}else if(dong!=null) {
				deal = dao.searchMulti2(bean);
			}else if(apt!=null) {
				deal = dao.searchMulti3(bean);
				pattern = bean.getaptName();
			}
			kmp_deal = new ArrayList<HouseDeal>();
			int res=0;
			int minIndex = (bean.getCurPage() -1)*bean.getPageSize();
			int maxIndex = bean.getCurPage()*bean.getPageSize();
			for (int i = 0; i < deal.size(); i++) {
				if(KMP(deal.get(i).getDong(), pattern)) {	//문자열 매칭 실패하면 해당 deal을 deal에서 제거
					if(minIndex<= res++) {
						if(res>maxIndex) {
							break;
						}
						kmp_deal.add(deal.get(i));
					}
				}
				if(!check) {
					if(KMP(deal.get(i).getAptName(), pattern)) {	//문자열 매칭 실패하면 해당 deal을 deal에서 제거
						if(minIndex<= res++) {
							if(res>maxIndex) {
								break;
							}
							kmp_deal.add(deal.get(i));
						}
					}
				}
			}
			
			return kmp_deal;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new HappyHouseException("주택정보 조회 중 오류 발생_Dong");
		}
	}
	
	@Override
	public List<HouseDeal> searchHouseName(HousePageBean bean) {
		List<HouseDeal> deal = null;
		List<HouseDeal> kmp_deal = null;
		try {
			String pattern = bean.getaptName();
			
			String dong = bean.getDong();
			String apt = bean.getaptName();
			String typeStr = "(";
			boolean[] type = bean.getSearchType();
			for(int i = 0; i < type.length; ++i) {
				if(type[i]) {
					typeStr+=(i+1)+",";
				}
			}
			typeStr = typeStr.substring(0,typeStr.length()-1)+")";
			bean.setTypeStr(typeStr);
			if(dong!=null && apt!=null ) {
				deal = dao.searchMulti1(bean);
			}else if(dong!=null) {
				deal = dao.searchMulti2(bean);
			}else if(apt!=null) {
				deal = dao.searchMulti3(bean);
			}
			kmp_deal = new ArrayList<HouseDeal>();
			int res=0;
			int minIndex = (bean.getCurPage() -1)*bean.getPageSize();
			int maxIndex = bean.getCurPage()*bean.getPageSize();
			
			for (int i = 0; i < deal.size(); i++) {
				if(KMP(deal.get(i).getAptName(), pattern)) {	//문자열 매칭 실패하면 해당 deal을 deal에서 제거
					if(minIndex<= res++) {
						if(res>maxIndex) {
							break;
						}
						kmp_deal.add(deal.get(i));
					}
				}
			}
			return kmp_deal;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new HappyHouseException("주택정보 조회 중 오류 발생_Name");
		}
	}

	@Override
	public List<HouseDeal> searchMulti(HousePageBean bean) {
		List<HouseDeal> deal = null;
		List<HouseDeal> kmp_deal = null;
		try {
			String dong = bean.getDong();
			String apt = bean.getaptName();
			String typeStr = "(";
			boolean[] type = bean.getSearchType();
			for(int i = 0; i < type.length; ++i) {
				if(type[i]) {
					typeStr+=(i+1)+",";
				}
			}
			typeStr = typeStr.substring(0,typeStr.length()-1)+")";
			bean.setTypeStr(typeStr);
			if(dong!=null && apt!=null ) {
				deal = dao.searchMulti1(bean);
			}else if(dong!=null) {
				deal = dao.searchMulti2(bean);
			}else if(apt!=null) {
				deal = dao.searchMulti3(bean);
			}
			if(deal == null) {
				return deal;
			}
			String pattern = bean.getDong();
			kmp_deal = new ArrayList<HouseDeal>();
			int minIndex = (bean.getCurPage() -1)*bean.getPageSize();
			int maxIndex = bean.getCurPage()*bean.getPageSize();
			for (int i = 0; i < deal.size(); i++) {
				if(KMP(deal.get(i).getDong(), pattern)) {	//문자열 매칭 실패하면 해당 deal을 deal에서 제거
						kmp_deal.add(deal.get(i));
				}
			}
			
//			pattern = apt;
			List<HouseDeal> kmp_deal_aptName = new ArrayList<HouseDeal>();
			if(pattern!=null) {
				int res=0;
				for (int i = 0; i < kmp_deal.size(); i++) {
					if(KMP(deal.get(i).getAptName(), pattern)) {	//문자열 매칭 실패하면 해당 deal을 deal에서 제거
						if(minIndex<= res++) {
							if(res>maxIndex) {
								break;
							}
							kmp_deal_aptName.add(deal.get(i));
						}
					}
				}
			}
			return kmp_deal_aptName;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new HappyHouseException("주택정보 조회 중 오류 발생_Name,Dong");
		}
	}
	
	@Override
	public List<HouseDeal> searchAll(HousePageBean  bean) {
		try {
			boolean[] type = bean.getSearchType();
			int cnt = 0;
			for(boolean t: type) {
				if(t) {
					cnt++;
				}
			}
			if(cnt==0) {
				throw new HappyHouseException("주택타입 반드시 한개 이상을 선택해야 합니다.");
			}
			String typeStr = "(";
			for(int i = 0; i < type.length; ++i) {
				if(type[i]) {
					typeStr+=(i+1)+",";
				}
			}
			typeStr = typeStr.substring(0,typeStr.length()-1)+")";
			bean.setTypeStr(typeStr);
			System.out.println(dao.searchAll(bean));
			return dao.searchAll(bean);
			
		} catch (SQLException e) {
			throw new HappyHouseException("주택정보 조회 중 오류 발생_ALL");
		}
	}
	
	@Override
	public List<HouseDeal> searchDetail(int no) {
		try {
			List<HouseDeal> deals = dao.searchDetail1(no);
			if(deals == null || deals.isEmpty()) {
				throw new HappyHouseException(String.format("거래번호 %d번에 해당하는 주택 거래정보가 존재하지 않습니다.", no));
			}
			return deals;
		} catch (SQLException e) {
			throw new HappyHouseException("주택정보 조회 중 오류 발생");
		}
	}
	@Override
	public List<HouseDeal> searchDong(String dong) {
		try {
			List<HouseDeal> deals = dao.searchDong2(dong);
			if(deals == null || deals.isEmpty()) {
				throw new HappyHouseException(String.format("거래번호 %d번에 해당하는 주택 거래정보가 존재하지 않습니다.", dong));
			}
			return deals;
		} catch (SQLException e) {
			throw new HappyHouseException("주택정보 조회 중 오류 발생");
		}
	}
	@Override
	public int insertHouseDeal(HouseDeal houseDeal) {
		try {
			int res = dao.insertHouseDeal(houseDeal);
			if(res <= 0) {
				throw new HappyHouseException(String.format("넣을 수 없습니다.", houseDeal.getNo()));
			}
			return res;
		} catch (SQLException e) {
			throw new HappyHouseException("주택정보 조회 중 오류 발생");
		}
	}
	
	
	/* KMP */
	// 실패테이블
	static int[] getPi(String pattern) {
		// 접두사와 접미사가 일치하는 최대길이를 담을 배열 선언
		int[] pi = new int[pattern.length()];

		// idx
		int j = 0;
		// i,j가 가리키는 값이 일치하면 둘다 증가
		// 불일치하면 i만 증가시켜야 하므로 for문
		for (int i = 1; i < pattern.length(); i++) {

			// pattern 내에서 i와 j가 가리키는 값이 다를때
			//while문안에 넣는 이유는 중간단계를 건너뛰고 최대한으로 점프하려고
			while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
				//j의 값을 한칸 뺀곳의 값으로 j를 바꿈
				j = pi[j - 1];
			}
			// pattern 내에서 i와 j가 가리키는 값이 같으면
			if (pattern.charAt(i) == pattern.charAt(j)) {
				// i번째의 최대길이는 ++j한 값
				pi[i] = ++j;
			}
		}

		return pi;
	}
	
	
	static boolean KMP(String parent, String pattern) {
		if(pattern.length() == 0) return true;
		int[] table = getPi(pattern);
//		System.out.println("점프테이블");
//		System.out.println(Arrays.toString(table));   
		parent = parent.trim();
		int j = 0; 
		for(int i = 0 ; i< parent.length(); i++) {
			while(j >0 && parent.charAt(i) != pattern.charAt(j)) {
				j = table[j - 1];
			}
			//부모와 패턴이 일치한다면
			if(parent.charAt(i) == pattern.charAt(j)) {
				//j의 값이 패턴의 길이-1이라면 한번 다찾은거니까
				//찾아다고 처리 -> 찾으면 바로 끝내기
				//return true;
				
				if(j == pattern.length()-1) {
					return true;
					//System.out.println((i-pattern.length()+1) + "째 인덱스에서 찾음" );
					//패턴을 또 찾기 위해서
					//j = table[j];
				}else {
					//다찾은건아니라면 계속 진행해야하므로 j값 증가
					j++;
				}
			}
		}
		return false;
	}

	@Override
	public List<HouseDeal> searchDetail(String AptName) {
		try {
			List<HouseDeal> deals = dao.searchDetail2(AptName);
			if(deals == null || deals.isEmpty()) {
				throw new HappyHouseException(String.format("거래번호 %d번에 해당하는 주택 거래정보가 존재하지 않습니다.", AptName));
			}
			return deals;
		} catch (SQLException e) {
			throw new HappyHouseException("주택정보 조회 중 오류 발생");
		}
	}
	
	//////////////////////////////////////////////////////
	@Override
	public PageNavigation makePageNavigation(int currentPage, int sizePerPage,HousePageBean  bean) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();
		int naviSize = 10; // 페이지 갯수
		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		int totalCount = dao.searchHouseDealCnt(bean); // 총 게시글 수(?)
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1)/sizePerPage+1; // 총 페이지수(?)
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage<=naviSize;; // startRange true: 이전 x,  false: 이전 o
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount-1)/naviSize * naviSize < currentPage; // endRange true: 다음 x,  false: 다음 o
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}
	
	
	
}
