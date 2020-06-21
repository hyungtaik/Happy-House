package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.happyhouse.dto.FavDto;
import com.ssafy.happyhouse.dto.HouseDeal;
import com.ssafy.happyhouse.dto.StoreDto;
import com.ssafy.happyhouse.service.FavService;

import io.swagger.annotations.ApiOperation;

@Controller
@CrossOrigin(origins = { "*" }, maxAge = 6000)
//@RestController
@RequestMapping("/fav")
public class FavController {

	@Autowired
	FavService favService;

	// 에러 핸들러 - ModelAndView 객체를 이용하여 에러페이지와 메세지를 담아 return
	@ExceptionHandler
	public ModelAndView handler(Exception ex) {
		ModelAndView mav = new ModelAndView("Error");
		mav.addObject("msg", ex.getMessage());
		ex.printStackTrace();
		return mav;
	}

//	@GetMapping(value = "/MYFAVAREA")
//	public String favarea() {
//		return "jsp/favorite/favoritearea";
//	}

//	@GetMapping(value = "/FAVAREA")
//	public String mvfavarea(HttpSession session, Model m) {
//		// session정보에서 userid 가져오기
//		String id = (String)session.getAttribute("suser");
//
//		// 선호지역 검색하기
//		m.addAttribute("a", favoriteService.searchFavArea(id));
//		return "/user/FavArea";
//	}

//	//관심지역
//	@GetMapping(value = "/")
//	public String mvFav() {
//		return "/fav/selectFav";
//	}

	// 관심지역 페이지
	@ApiOperation(value = "관심지역 목록결과를 반환한다.", response = String.class)
	@GetMapping(value = "/list")
	public String mvFav(HttpSession session, Model m) {
		// session정보에서 userid 가져오기
		String id = (String) session.getAttribute("suser");
		// 선호지역 검색하기
		m.addAttribute("a", favService.searchFavArea(id));
		return "/fav/selectFav";
	}

	// 관심지역 목록정보
	@ApiOperation(value = "관심지역 목록결과를 반환한다.", response = String.class)
	@GetMapping(value = "/house")
	public String mvFavHouse(HttpSession session, Model m) {
		// session정보에서 userid 가져오기
		String id = (String) session.getAttribute("suser");
		// 선호지역 검색하기
		m.addAttribute("a", favService.searchFavArea(id));
		return "/fav/favHouse";
	}
	
	@ApiOperation(value = "동 이름으로 거래내역 출력", response = List.class)
	@GetMapping(value = "/apt/{dong}")
	public ResponseEntity<List<HouseDeal>> searchDong(@PathVariable String dong, HttpServletRequest req) {
		System.out.println("/apt/dong");
		
		int limit = Integer.parseInt(req.getParameter("limit"));
		int offset = Integer.parseInt(req.getParameter("offset"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dong", dong);
		map.put("limit", limit);
		map.put("offset", offset);
		
		return new ResponseEntity<List<HouseDeal>>(favService.selectApt(map), HttpStatus.OK);
	}
	
	// 관심지역 목록정보
	@ApiOperation(value = "관심지역 목록결과를 반환한다.", response = String.class)
	@GetMapping(value = "/store")
	public String mvFavStore(HttpSession session, Model m) {
		// session정보에서 userid 가져오기
		String id = (String) session.getAttribute("suser");
		// 선호지역 검색하기
		m.addAttribute("a", favService.searchFavArea(id));
		return "/fav/favStore";
	}
	
	// 관심지역 상권정보
	@ApiOperation(value = "관심지역 상권결과를 반환한다.", response = String.class)
	@GetMapping(value = "/store/{dong}")
	public ResponseEntity<List<StoreDto>> searchStore(@PathVariable String dong, HttpServletRequest req) {
		int limit = Integer.parseInt(req.getParameter("limit"));
		int offset = Integer.parseInt(req.getParameter("offset"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dong", dong);
		map.put("limit", limit);
		map.put("offset", offset);
		
		return new ResponseEntity<List<StoreDto>>(favService.searchFavStore(map), HttpStatus.OK);
	}
	
//	@GetMapping(value = "/house/myfav")
//	public @ResponseBody List<FavDto> myFavArea(HttpSession session) {
//		String userid = (String)session.getAttribute("suser");
//		
//		//해당 id이용해서 favoritearea table에 등록된 관심 정보 지역 정보 가져오기
//		return favService.searchFavoriteAreaUser(userid);
//	}

	// 나의 관심지역 저장
	@ApiOperation(value = "관심지역 저장결과를 반환한다.", response = String.class)
	@PostMapping(value = "/save")
	public String update(HttpSession session, Model m, HttpServletRequest request) {
		int result = -1;

		// session정보에서 userid 가져오기
		String id = (String) session.getAttribute("suser");

		// 삭제
		result = favService.deleteFavArea(id);

		String[] sidoList = request.getParameterValues("sido");
		String[] gugunList = request.getParameterValues("gugun");
		String[] dongList = request.getParameterValues("dong");

		if (sidoList != null && gugunList != null && dongList != null) {
			for (int i = 0; i < gugunList.length; i++) {
				FavDto area = new FavDto();
				area.setUserId(id);
				area.setCity(sidoList[i]);
				area.setGu(gugunList[i]);
				area.setDong(dongList[i]);
				result = favService.insertFavArea(area);
				System.out.println(area.toString());
			}
		}

		// 선호지역 검색하기
		m.addAttribute("a", favService.searchFavArea(id));
		return "/fav/selectFav";
	}

	
	@ApiOperation(value = "동 이름으로 검색시 주택의 총갯수를 반환", response = List.class)
	@GetMapping(value = "/houseListTotalCnt")
	public ResponseEntity<Integer> searchDongCnt(HttpServletRequest req) {

		String dong = (String)req.getParameter("dong");
		
		return new ResponseEntity<Integer>(favService.selectAptCnt(dong), HttpStatus.OK);
	}
	
	@ApiOperation(value = "동 이름으로 검색시 상점의 총갯수를 반환", response = List.class)
	@GetMapping(value = "/storeListTotalCnt")
	public ResponseEntity<Integer> searchStoreCnt(HttpServletRequest req) {

		String dong = (String)req.getParameter("dong");
		
		return new ResponseEntity<Integer>(favService.selectStoreCnt(dong), HttpStatus.OK);
	}
}
