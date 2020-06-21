package com.ssafy.happyhouse.dto;

import java.util.Date;

public class NoticeDto {
	private int no;
	private String userId;
	private String title;
	private String content;
	private Date date;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "NoticeDto [no=" + no + ", userId=" + userId + ", title=" + title + ", content=" + content + ", date="
				+ date + "]";
	}
}