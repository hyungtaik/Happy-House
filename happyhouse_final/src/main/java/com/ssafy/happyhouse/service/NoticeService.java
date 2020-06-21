package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.NoticeDto;

public interface NoticeService {
	List<NoticeDto> retrieveNotice(String userid);
	public NoticeDto detailNotice(int no);
	public boolean writeNotice(NoticeDto dto);
	public boolean updateNotice(NoticeDto dto);
	public boolean deleteNotice(int no);
}
