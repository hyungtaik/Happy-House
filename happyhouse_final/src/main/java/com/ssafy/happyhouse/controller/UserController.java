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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.happyhouse.dto.UserDto;
import com.ssafy.happyhouse.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(QnAController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private UserService userService; 
	
	// 에러 핸들러 - ModelAndView 객체를 이용하여 에러페이지와 메세지를 담아 return
	@ExceptionHandler
	public ModelAndView handler(Exception ex) {
		ModelAndView mav = new ModelAndView("error/errorHandler");
		mav.addObject("msg", ex.getMessage());
		ex.printStackTrace();
		return mav;
	}
	
    @ApiOperation(value = "로그인의 결과를 반환한다.", response = UserDto.class)
	@PostMapping(value = "/login")
	public ModelAndView login(UserDto dto, HttpSession session) {
		UserDto login = userService.login(dto);
		ModelAndView mav = new ModelAndView("index");
		if (login == null) {
			mav.addObject("msg", "아이디 또는 비밀번호를 확인해주세요");
		} else {
			session.setAttribute("suser", login.getId());
			session.setAttribute("auth", login.getAuth());
			String msg = "정상적으로 로그인 되었습니다. 어서오세요 " + login.getName() + "님";
			System.out.println(msg);
			mav.addObject("msg", msg);
		}
		return mav;
	}
    
    @ApiOperation(value = "회원가입을 한다.", response = UserDto.class)
	@PostMapping(value="/join")
	public ResponseEntity join(@RequestBody UserDto user) {
		logger.debug("Join - 호출");
		if(userService.emailcheck(user.getEmail())!=null) {
			return new ResponseEntity<UserDto>(new UserDto(), HttpStatus.OK);
		}
		boolean check = userService.register(user);
		if(check) {
			return new ResponseEntity<UserDto>(user, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    
    @ApiOperation(value = "id pw를 찾는다.", response = UserDto.class)
	@PostMapping(value="/find")
	public ResponseEntity find(@RequestBody UserDto user) {
		logger.debug("find - 호출");
		UserDto dto = userService.findPwd(user);
		System.out.println(dto);
		if(dto.getId() != null) {
			return new ResponseEntity<UserDto>(dto, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    
    @ApiOperation(value = "회원정보 수정을 한다.", response = UserDto.class)
   	@PutMapping(value="/modify")
   	public ResponseEntity modify(@RequestBody UserDto user,HttpSession session) {
   		logger.debug("Modify - 호출");
   		String id = (String) session.getAttribute("suser");
   		user.setId(id);
   		System.out.println(user);
   	    UserDto temp = userService.emailcheck(user.getEmail());
   		if(temp == null) {
   			boolean check = userService.modify(user);
   			System.out.println(check);
   			if(check) {
   				return new ResponseEntity<UserDto>(user, HttpStatus.OK);
   			}else {
   				return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
   			}
   		}else {
   			if(temp.getId() == user.getId()) {
   				return new ResponseEntity<UserDto>(user, HttpStatus.OK);
   			}
   			return new ResponseEntity<UserDto>(new UserDto(), HttpStatus.OK);
   		}
   	}
    
 
 	 @ApiOperation(value = "회원탈퇴", response = UserDto.class)
 	@DeleteMapping(value = "/delete")
 	public ResponseEntity delete(HttpSession session) {
 		logger.debug("delete - 호출");
 		String id = (String) session.getAttribute("suser");
 		
 		session.invalidate();
 		
 		if(userService.delete(id)) {
 			return new ResponseEntity<UserDto>(new UserDto(), HttpStatus.OK);
 		}
 		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
 	}
}
