package com.ssafy.happyhouse.dto;

public class NewsDto {
	private String url;
	private String title;
	private String imgUrl;
	private String contents;
	
	public NewsDto() {}
	
	public NewsDto(String url, String title, String imgUrl) {
		super();
		this.url = url;
		this.title = title;
		this.imgUrl = imgUrl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	@Override
	public String toString() {
		return "NewsDto [url=" + url + ", title=" + title + ", imgUrl=" + imgUrl + ", contents=" + contents + "]";
	}
	
}
