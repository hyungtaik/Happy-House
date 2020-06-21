package com.ssafy.happyhouse.controller;

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

import com.ssafy.happyhouse.dto.NoticeDto;
import com.ssafy.happyhouse.service.NoticeService;

import io.swagger.annotations.ApiOperation;

//http://localhost:9999/happyhouse/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/notice")
public class NoticeController {

	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private NoticeService noticeService; 

    @ApiOperation(value = "모든 공지사항의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<NoticeDto>> retrieveNotice(HttpSession session) throws Exception {
		logger.debug("retrieveNotice - 호출");
		String userid = (String)session.getAttribute("suser");
		
		return new ResponseEntity<List<NoticeDto>>(noticeService.retrieveNotice(userid), HttpStatus.OK);
		
	}

    @ApiOperation(value = "글번호에 해당하는 공지사항의 정보를 반환한다.", response = NoticeDto.class)    
	@GetMapping("{no}")
	public ResponseEntity<NoticeDto> detailNotice(@PathVariable int no) {
		logger.debug("detailNotice - 호출");
		System.out.println(noticeService.detailNotice(no));
		return new ResponseEntity<NoticeDto>(noticeService.detailNotice(no), HttpStatus.OK);
	}

    @ApiOperation(value = "새로운 공지사항을 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeNotice(@RequestBody NoticeDto notice,HttpSession session) {
    	System.out.println(notice);
		logger.debug("writeNotice - 호출");
		String userid = (String)session.getAttribute("suser");
		if(userid.equals("admin") && userid.equals(notice.getUserId())) {
			if (noticeService.writeNotice(notice)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		//Notice.setNoticeUserid(Notice.getNoticeUserid());
	}

    @ApiOperation(value = "글번호에 해당하는 공지사항을 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("{no}")
	public ResponseEntity<String> updateNotice(@RequestBody NoticeDto notice,HttpSession session) {
		logger.debug("updateNotice - 호출");
		logger.debug("" + notice);
		String userid = (String)session.getAttribute("suser");
		//Notice.setNoticeUserid(userid);
		if(userid.equals("admin") && userid.equals(notice.getUserId())) {
			if (noticeService.updateNotice(notice)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "글번호에 해당하는 공지사항의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{no}")
	public ResponseEntity<String> deleteNotice(@PathVariable int no,HttpSession session) {
		logger.debug("deleteNotice - 호출");
		
		if (noticeService.deleteNotice(no)) {
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
}