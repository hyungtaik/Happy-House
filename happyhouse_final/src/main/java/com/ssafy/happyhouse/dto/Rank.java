package com.ssafy.happyhouse.dto;

/**
 * @author TAEK
 *
 */
public class Rank {
	private String aptName;
	private String rank;
	private String dong;
	private String pay;
	private String dealDay;
	private String area;
	private String floor;
	private String img;
	
	
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getAptName() {
		return aptName;
	}
	public void setAptName(String aptName) {
		this.aptName = aptName;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
	public String getPay() {
		return pay;
	}
	public void setPay(String pay) {
		this.pay = pay;
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
	public Rank(String aptName, String rank, String dong, String pay, String dealDay, String area, String floor) {
		super();
		this.aptName = aptName;
		this.rank = rank;
		this.dong = dong;
		this.pay = pay;
		this.dealDay = dealDay;
		this.area = area;
		this.floor = floor;
	}
	@Override
	public String toString() {
		return "Rank [aptName=" + aptName + ", rank=" + rank + ", dong=" + dong + ", pay=" + pay + ", dealDay="
				+ dealDay + ", area=" + area + ", floor=" + floor + "]";
	}
	
}
