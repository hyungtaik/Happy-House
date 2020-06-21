package com.ssafy.happyhouse.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ssafy.happyhouse.dto.QnADto;
import com.ssafy.happyhouse.service.QnAService;

import io.swagger.annotations.ApiOperation;

//http://localhost:9999/happyhouse/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/qna")
public class QnAController {

	private static final Logger logger = LoggerFactory.getLogger(QnAController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private QnAService QnAService; 

    @ApiOperation(value = "현재 사용자와 글 작성자가 일치하거나 유저가 관리자인 경우 모든 질문 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<QnADto>> retrieveQnA(HttpSession session) throws Exception {
		logger.debug("retrieveQnA - 호출");
		String userid = (String)session.getAttribute("suser");
		
//		if(userid.equals("admin")) {
//			//모든 QnA글 반환
//			return new ResponseEntity<List<QnADto>>(QnAService.retrieveAllQnA(), HttpStatus.OK);
//		}else {
//			return new ResponseEntity<List<QnADto>>(QnAService.retrieveQnA(userid), HttpStatus.OK);
//		}
		
			return new ResponseEntity<List<QnADto>>(QnAService.retrieveAllQnA(), HttpStatus.OK);
	}

    @ApiOperation(value = "글번호에 해당하는 게시글의 정보를 반환한다.", response = QnADto.class)    
	@GetMapping("{no}")
	public ResponseEntity<QnADto> detailQnA(@PathVariable int no) {
		logger.debug("detailQnA - 호출");
		return new ResponseEntity<QnADto>(QnAService.detailQnA(no), HttpStatus.OK);
	}

    @ApiOperation(value = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeQnA(@RequestBody QnADto QnA,HttpSession session) {
    	System.out.println(QnA.getQnaUserid());
    	System.out.println(QnA);
		logger.debug("writeQnA - 호출");
		String userid = (String)session.getAttribute("suser");
		QnA.setQnaUserid(userid);
		if(userid.equals(QnA.getQnaUserid())) {
			if (QnAService.writeQnA(QnA)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "글번호에 해당하는 게시글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("{no}")
	public ResponseEntity<String> updateQnA(@RequestBody QnADto QnA,HttpSession session) {
		logger.debug("updateQnA - 호출");
		logger.debug("" + QnA);
		//String userid = (String)session.getAttribute("suser");
		//QnA.setQnaUserid(userid);
		QnA.setQnaUserid(QnA.getQnaUserid());
		if (QnAService.updateQnA(QnA)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{no}")
	public ResponseEntity<String> deleteQnA(@PathVariable int no,HttpSession session) {
		logger.debug("deleteQnA - 호출");
		
		if (QnAService.deleteQnA(no)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    
    @ApiOperation(value = "현재 사용자 세션 정보를 제공한다.", response = String.class)
   	@GetMapping("/id")
   	public ResponseEntity<String> pushID(HttpSession session) {
    	String userid = (String)session.getAttribute("suser");
   		logger.debug("pushID - 호출");
   		System.out.println("userid : "+userid );
   		if (userid != null) {
   			return new ResponseEntity<String>(userid, HttpStatus.OK);
   		}
   		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
   	}
    
    /* Reply */
    @ApiOperation(value = "새로운 댓글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/reply/{no}")
	public ResponseEntity<String> writeReply(@RequestBody QnADto QnA,HttpSession session) {
    	System.out.println(QnA.getReplyUserid());
    	System.out.println(QnA);
		logger.debug("writeReply - 호출");
		String userid = (String)session.getAttribute("suser");	//관리자 (admin)
		if(userid.equals("admin")) {  
			if (QnAService.writeReply(QnA)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		//QnA.setQnaUserid(QnA.getQnaUserid());
	}
    
    @ApiOperation(value = "글번호에 해당하는 게시글의 댓글을 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/reply/{no}")
	public ResponseEntity<String> updateReply(@RequestBody QnADto QnA,HttpSession session) {
		logger.debug("updateReply - 호출");
		logger.debug("" + QnA);
		System.out.println(QnA.toString());
		//String userid = (String)session.getAttribute("suser");
		//QnA.setQnaUserid(QnA.getQnaUserid());
		if (QnAService.updateReply(QnA)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    
    @ApiOperation(value = "글번호에 해당하는 게시글의 댓글을 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/reply/{no}")
	public ResponseEntity<String> deleteReply(@PathVariable int no,HttpSession session) {
		logger.debug("deleteReply - 호출");
		
		if (QnAService.deleteReply(no)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}