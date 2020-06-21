package com.ssafy.happyhouse.dto;

/**
 * city, gu, dong, userId를 PK로
 */
public class FavDto {
	/**도,광역시*/
	private String city;

	/**시,구,군*/
	private String gu;

	/**법정동*/
	private String dong;

	/**해당 구역 사용자 ID*/
	private String userId;

	//메인화면 선택 여부는 미사용
	/**메인화면 선택여부*/
	private boolean isMain;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getGu() {
		return gu;
	}

	public void setGu(String gu) {
		this.gu = gu;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isMain() {
		return isMain;
	}

	public void setMain(boolean isMain) {
		this.isMain = isMain;
	}

	@Override
	public String toString() {
		return "FavoriteArea [city=" + city + ", gu=" + gu + ", dong=" + dong + ", userId=" + userId + ", isMain="
				+ isMain + "]";
	}
}
