package com.ssafy.happyhouse.dto;

import java.io.Serializable;

public class HouseDeal implements Serializable, Comparable<HouseDeal> {
	public static String  APT_DEAL="아파트 매매";
	public static String  APT_RENT="아파트 전월세";
	public static String  HOUSE_DEAL="연립,주택 매매";
	public static String  HOUSE_RENT="연립,주택 전월세";
	/**거래 정보를 식별할 번호*/
	private int    no;
	/**법정 동명 */
	private String dong;
	/**아파트 이름 */
	private String aptName;
	/**법정 동코드 */
	private String code;
	/**거래 금액 */
	private String dealAmount;
	/**건축 연도*/
	private String buildYear;
	/**거래 연도*/
	private String dealYear;
	/**거래 월 */
	private String dealMonth;
	/**거래 일 */
	private String dealDay;
	/**전용면적 */
	private String area;
	/**층*/
	private String floor;
	/**지번*/
	/** */
	private String lat;
	private String lng;
	private String jibun;
	/**type
	 * 1 : 아파트 매매
	 * 2 : 연립 다세세 매매
	 * 3 : 아파트 전월세
	 * 4 : 연립 다세세 전월세
	 * */
	private String type;
	private String rentMoney;
	
	private String img;
	public HouseDeal() {	}
	public HouseDeal(int no) {
		super();
		this.no = no;
	}
	
	
	
	public HouseDeal(int no, String dong, String aptName, String code, String dealAmount, String buildYear,
			String dealYear, String dealMonth, String dealDay, String area, String floor, String lat, String lng,
			String jibun, String type, String rentMoney, String img) {
		super();
		this.no = no;
		this.dong = dong;
		this.aptName = aptName;
		this.code = code;
		this.dealAmount = dealAmount;
		this.buildYear = buildYear;
		this.dealYear = dealYear;
		this.dealMonth = dealMonth;
		this.dealDay = dealDay;
		this.area = area;
		this.floor = floor;
		this.lat = lat;
		this.lng = lng;
		this.jibun = jibun;
		this.type = type;
		this.rentMoney = rentMoney;
		this.img = img;
	}
	public HouseDeal(HouseDeal hd) {
		super();
		this.dong = hd.dong;
		this.aptName = hd.aptName;
		this.code = hd.code;
		this.dealAmount = hd.dealAmount;
		this.buildYear = hd.buildYear;
		this.dealYear = hd.dealYear;
		this.dealMonth = hd.dealMonth;
		this.dealDay = hd.dealDay;
		this.area = hd.area;
		this.floor = hd.floor;
		this.jibun = hd.jibun;
	}

	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
	public String getAptName() {
		return aptName;
	}
	public void setAptName(String aptName) {
		this.aptName = aptName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDealAmount() {
		return dealAmount;
	}
	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}
	public String getBuildYear() {
		return buildYear;
	}
	public void setBuildYear(String buildYear) {
		this.buildYear = buildYear;
	}
	public String getDealYear() {
		return dealYear;
	}
	public void setDealYear(String dealYear) {
		this.dealYear = dealYear;
	}
	public String getDealMonth() {
		return dealMonth;
	}
	public void setDealMonth(String dealMonth) {
		this.dealMonth = dealMonth;
	}
	public String getDealDay() {
		return dealDay;
	}
	public void setDealDay(String dealDay) {
		this.dealDay = dealDay;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getJibun() {
		return jibun;
	}
	public void setJibun(String jibun) {
		this.jibun = jibun;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRentMoney() {
		return rentMoney;
	}
	public void setRentMoney(String rentMoney) {
		this.rentMoney = rentMoney;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int compareTo(HouseDeal o) {
		if(o!=null && o.dong != null&&dong!=null) {
			return dong.compareTo(o.dong);
		}
		return 0;
	}
	@Override
	public String toString() {
		return "HouseDeal [no=" + no + ", dong=" + dong + ", aptName=" + aptName + ", code=" + code + ", dealAmount="
				+ dealAmount + ", buildYear=" + buildYear + ", dealYear=" + dealYear + ", dealMonth=" + dealMonth
				+ ", dealDay=" + dealDay + ", area=" + area + ", floor=" + floor + ", lat=" + lat + ", lng=" + lng
				+ ", jibun=" + jibun + ", type=" + type + ", rentMoney=" + rentMoney + ", img=" + img + "]";
	}
	
	
}






