package com.ssafy.happyhouse.dto;

import java.io.Serializable;

public class HouseInfo implements Serializable, Comparable<HouseInfo> {
	/**아파트를 식별할 번호*/
	private int    no;
	/**법정 동명 */
	private String dong;
	/**아파트 이름 */
	private String aptName;
	/**법정 동코드 */
	private String code;
	/**건축 연도*/
	private String buildYear;
	/**지번*/
	private String jibun;
	/** */
	private String lat;
	private String lng;
	
	/**이미지 경로*/
	protected String img;
	public HouseInfo() {	}
	public HouseInfo(int no) {
		super();
		this.no = no;
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
	public String getBuildYear() {
		return buildYear;
	}
	public void setBuildYear(String buildYear) {
		this.buildYear = buildYear;
	}
	public String getJibun() {
		return jibun;
	}
	public void setJibun(String jibun) {
		this.jibun = jibun;
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
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int compareTo(HouseInfo o) {
		if(o!=null && o.dong != null&&dong!=null) {
			return dong.compareTo(o.dong);
		}
		return 0;
	}
	@Override
	public String toString() {
		return "HouseInfo [no=" + no + ", dong=" + dong + ", aptName=" + aptName + ", code=" + code + ", buildYear="
				+ buildYear + ", jibun=" + jibun + ", img=" + img + "]";
	}
}




