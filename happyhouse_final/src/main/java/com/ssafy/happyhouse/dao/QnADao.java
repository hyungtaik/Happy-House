package com.ssafy.happyhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.QnADto;

public interface QnADao {
	public List<QnADto> selectQnA(String userid);
	public QnADto detailQnA(int no);
	public int writeQnA(QnADto dto);
	public int updateQnA(QnADto dto);
	public int deleteQnA(int no);
	
	/* Reply */
	public List<QnADto> selectAllQnA();
	public int writeReply(QnADto dto);
	public int updateReply(QnADto dto);
	public int deleteReply(int no);
}
