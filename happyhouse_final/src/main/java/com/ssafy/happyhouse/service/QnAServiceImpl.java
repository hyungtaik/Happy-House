package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.QnADao;
import com.ssafy.happyhouse.dto.QnADto;


@Service
public class QnAServiceImpl implements QnAService {

	@Autowired
	private QnADao dao;
	
	@Override
	public List<QnADto> retrieveQnA(String userid) {
		return dao.selectQnA(userid);
	}

	@Override
	public QnADto detailQnA(int no) {
		return dao.detailQnA(no);
	}

	@Override
	public boolean writeQnA(QnADto dto) {
		return dao.writeQnA(dto) == 1;
	}

	@Override
	public boolean updateQnA(QnADto dto) {
		return dao.updateQnA(dto) == 1;
	}

	@Override
	public boolean deleteQnA(int no) {
		return dao.deleteQnA(no) == 1;
	}
	
	/* Reply */
	@Override
	public List<QnADto> retrieveAllQnA() {
		return dao.selectAllQnA();
	}
	
	@Override
	public boolean writeReply(QnADto dto) {
		return dao.writeReply(dto) == 1;
	}

	@Override
	public boolean updateReply(QnADto dto) {
		return dao.updateReply(dto) == 1;
	}

	@Override
	public boolean deleteReply(int no) {
		return dao.deleteReply(no) == 1;
	}
}
