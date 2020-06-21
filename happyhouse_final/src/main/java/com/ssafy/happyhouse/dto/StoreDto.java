package com.ssafy.happyhouse.dto;

import java.io.Serializable;

public class StoreDto implements Serializable {
	/** 식별할 번호 */
	private int no;

	/** 해당 상가업소의 상호명 */
	private String shopname;

	/** 지점명 */
	private String localname;

	/** 상권업종대분류코드 */
	private String code1;

	/** 상권업종대분류명 */
	private String codename1;

	/** 상권업종중분류코드 */
	private String code2;

	/** 상권업종중분류명 */
	private String codename2;

	/** 상권업종소분류코드 */
	private String code3;

	/** 상권업종소분류명 */
	private String codename3;

	/** 표준산업분류코드 */
	private String code4;

	/** 표준산업분류명 */
	private String codename4;

	/** 시도코드 */
	private String citycode;

	/** 시도명 */
	private String city;

	/** 시군구코드 */
	private String gucode;

	/** 시군구명 */
	private String gu;

	/** 법정동코드 */
	private String dongcode;

	/** 법정동명 */
	private String dong;

	/** 지번주소 */
	private String jibuaddress;

	/** 도로명주소 */
	private String address;

	/** 구우편번호 */
	private String oldpostcode;

	/** 신우편번호 */
	private String postcode;

	/** 위도 */
	private String lng;

	/** 경도 */
	private String lat;

	public StoreDto() {
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public String getLocalname() {
		return localname;
	}

	public void setLocalname(String localname) {
		this.localname = localname;
	}

	public String getCode1() {
		return code1;
	}

	public void setCode1(String code1) {
		this.code1 = code1;
	}

	public String getCodename1() {
		return codename1;
	}

	public void setCodename1(String codename1) {
		this.codename1 = codename1;
	}

	public String getCode2() {
		return code2;
	}

	public void setCode2(String code2) {
		this.code2 = code2;
	}

	public String getCodename2() {
		return codename2;
	}

	public void setCodename2(String codename2) {
		this.codename2 = codename2;
	}

	public String getCode3() {
		return code3;
	}

	public void setCode3(String code3) {
		this.code3 = code3;
	}

	public String getCodename3() {
		return codename3;
	}

	public void setCodename3(String codename3) {
		this.codename3 = codename3;
	}

	public String getCode4() {
		return code4;
	}

	public void setCode4(String code4) {
		this.code4 = code4;
	}

	public String getCodename4() {
		return codename4;
	}

	public void setCodename4(String codename4) {
		this.codename4 = codename4;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getGucode() {
		return gucode;
	}

	public void setGucode(String gucode) {
		this.gucode = gucode;
	}

	public String getGu() {
		return gu;
	}

	public void setGu(String gu) {
		this.gu = gu;
	}

	public String getDongcode() {
		return dongcode;
	}

	public void setDongcode(String dongcode) {
		this.dongcode = dongcode;
	}


	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getJibuaddress() {
		return jibuaddress;
	}

	public void setJibuaddress(String jibuaddress) {
		this.jibuaddress = jibuaddress;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOldpostcode() {
		return oldpostcode;
	}

	public void setOldpostcode(String oldpostcode) {
		this.oldpostcode = oldpostcode;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	@Override
	public String toString() {
		return "StoreInfo [no=" + no + ", shopname=" + shopname + ", localname=" + localname + ", code1=" + code1
				+ ", codename1=" + codename1 + ", code2=" + code2 + ", codename2=" + codename2 + ", code3=" + code3
				+ ", codename3=" + codename3 + ", code4=" + code4 + ", codename4=" + codename4 + ", citycode="
				+ citycode + ", city=" + city + ", gucode=" + gucode + ", gu=" + gu + ", dongcode=" + dongcode
				+ ", dong=" + dong + ", jibuaddress=" + jibuaddress + ", address=" + address + ", oldpostcode="
				+ oldpostcode + ", postcode=" + postcode + ", lng=" + lng + ", lat=" + lat + "]";
	}

}