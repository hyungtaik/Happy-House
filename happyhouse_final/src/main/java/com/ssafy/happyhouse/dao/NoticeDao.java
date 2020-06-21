package com.ssafy.happyhouse.dao;

import java.util.List;

import com.ssafy.happyhouse.dto.NoticeDto;

public interface NoticeDao {
	public List<NoticeDto> selectNotice(String userid);
	public NoticeDto detailNotice(int no);
	public int writeNotice(NoticeDto dto);
	public int updateNotice(NoticeDto dto);
	public int deleteNotice(int no);
}
