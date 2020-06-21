package com.ssafy.happyhouse.dto;

import java.io.Serializable;
import java.util.Arrays;

public class HousePageBean implements Serializable {
	private String city;
	private String dong;
	private String dongcode;
	private String aptName;
	private String dealtype = "all";
	/**
	 * 0 : 아파트 매매 1 : 아파트 전월세 2 : 다세대 매매 3 : 다세대 전월세
	 */
	private boolean[] searchType;
	String typeStr;
	String searchField;
	String searchText;
	int page;

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}



	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getSearchField() {
		return searchField;
	}

	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}

	public String getTypeStr() {
		return typeStr;
	}

	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}

	/** 웹 페이지 링크 */
	private String pagelink;

	/** 페이지네이션 위한 변수들 */

	/** 한 페이지당 게시글 수 */
	private int pageSize = 10;

	/** 보여질 페이지 범위 */
	private int rangeSize = 10;

	/** 현재 선택된 페이지 */
	private int curPage = 1;

	// 아래는 curPage와 곂치는 것 아닐까?
	/** 현재 선택된 range */
	private int curRange = 1;

	/** 검색된 거래 수 */
	private int searchCnt;

	/** 총 페이지 수 */
	private int pageCnt;

	/** 총 range 수?? */
	private int rangeCnt;

	/** 시작 페이지 */
	private int startPage = 1;

	/** 끝 페이지 */
	private int endPage = 1;

	/** 검색시 시작 index */
	private int startIndex;

	/** 이전 페이지 */
	private int prevPage;

	/** 다음 페이지 */
	private int nextPage;

	public void setPage(int searchCnt, int curPage) {
		/**
		 * 페이징 처리 순서 1. 총 페이지수 2. 총 블럭(range)수 3. range setting
		 */

		// 총 검색 수와 현재 페이지를 받아서 페이지관련 변수들을 초기화 시킬 예정이다.
		// 현재 페이지
		setCurPage(curPage);
		// 총 검색 수
		setSearchCnt(searchCnt);

		// 총 페이지 수
		setPageCnt(searchCnt / pageSize + (searchCnt % pageSize > 0 ? 1 : 0));
		// 총 range 수
		setRangeCnt(pageCnt / rangeSize + (pageCnt % rangeSize > 0 ? 1 : 0));
		// 블럭 setting
		rangeSetting(curPage);

		// DB offset 설정 위한 startIndex 설정
		setStartIndex((curPage - 1) * pageSize);
	}

	private void rangeSetting(int curPage) {
		//
		setCurRange((int) ((curPage - 1) / rangeSize) + 1);
		// 아래 보여지는 범위(range)
		this.startPage = (curRange - 1) * rangeSize + 1;
		this.endPage = startPage + rangeSize - 1;

		// 끝 점은 총 페이지 수 보다 커질 수 없다.
		if (endPage > pageCnt) {
			this.endPage = pageCnt;
		}

		// 이전, 다음 버튼이 가지고 있을 curPage이다.
		// 첫페이지, 끝 페이지일때는 '이전', '다음' 버튼을 보여주지 않을 것이다.
		this.prevPage = curPage - 1;
		this.nextPage = curPage + 1;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRangeSize() {
		return rangeSize;
	}

	public void setRangeSize(int rangeSize) {
		this.rangeSize = rangeSize;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getCurRange() {
		return curRange;
	}

	public void setCurRange(int curRange) {
		this.curRange = curRange;
	}

	public int getSearchCnt() {
		return searchCnt;
	}

	public void setSearchCnt(int searchCnt) {
		this.searchCnt = searchCnt;
	}

	public int getPageCnt() {
		return pageCnt;
	}

	public void setPageCnt(int pageCnt) {
		this.pageCnt = pageCnt;
	}

	public int getRangeCnt() {
		return rangeCnt;
	}

	public void setRangeCnt(int rangeCnt) {
		this.rangeCnt = rangeCnt;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public HousePageBean() {
		super();
		searchType = new boolean[4];
		Arrays.fill(searchType, true);
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getDongcode() {
		return dongcode;
	}

	public void setDongcode(String dongcode) {
		this.dongcode = dongcode;
	}

	public String getaptName() {
		return aptName;
	}

	public void setaptName(String aptName) {
		this.aptName = aptName;
	}

	public String getDealtype() {
		return dealtype;
	}

	public void setDealtype(String dealtype) {
		this.dealtype = dealtype;
	}

	public boolean[] getSearchType() {
		return searchType;
	}

	public void setSearchType(boolean[] searchType) {
		this.searchType = searchType;
	}

	public String getPagelink() {
		return pagelink;
	}

	public void setPagelink(String pagelink) {
		this.pagelink = pagelink;
	}

	@Override
	public String toString() {
		return "HousePageBean [city=" + city + ", dong=" + dong + ", dongcode=" + dongcode + ", aptName=" + aptName
				+ ", dealtype=" + dealtype + ", searchType=" + Arrays.toString(searchType) + ", pagelink=" + pagelink
				+ ", pageSize=" + pageSize + ", rangeSize=" + rangeSize + ", curPage=" + curPage + ", curRange="
				+ curRange + ", searchCnt=" + searchCnt + ", pageCnt=" + pageCnt + ", rangeCnt=" + rangeCnt
				+ ", startPage=" + startPage + ", endPage=" + endPage + ", startIndex=" + startIndex + ", prevPage="
				+ prevPage + ", nextPage=" + nextPage + "]";
	}

}
