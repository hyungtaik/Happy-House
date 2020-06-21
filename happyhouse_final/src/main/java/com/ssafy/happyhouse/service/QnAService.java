package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.QnADto;

public interface QnAService {
	List<QnADto> retrieveQnA(String userid);
	public QnADto detailQnA(int no);
	public boolean writeQnA(QnADto dto);
	public boolean updateQnA(QnADto dto);
	public boolean deleteQnA(int no);
	
	/* Reply */
	List<QnADto> retrieveAllQnA();
	public boolean writeReply(QnADto dto);
	public boolean updateReply(QnADto dto);
	public boolean deleteReply(int no);	//게시물의 번호
}
