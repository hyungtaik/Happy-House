package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.NoticeDao;
import com.ssafy.happyhouse.dto.NoticeDto;


@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeDao dao;
	
	@Override
	public List<NoticeDto> retrieveNotice(String userid) {
		return dao.selectNotice(userid);
	}

	@Override
	public NoticeDto detailNotice(int no) {
		return dao.detailNotice(no);
	}

	@Override
	public boolean writeNotice(NoticeDto dto) {
		return dao.writeNotice(dto) == 1;
	}

	@Override
	public boolean updateNotice(NoticeDto dto) {
		return dao.updateNotice(dto) == 1;
	}

	@Override
	public boolean deleteNotice(int no) {
		return dao.deleteNotice(no) == 1;
	}
}
