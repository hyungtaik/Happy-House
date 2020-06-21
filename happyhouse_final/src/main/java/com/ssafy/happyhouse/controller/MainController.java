package com.ssafy.happyhouse.controller;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.happyhouse.dto.NewsDto;
import com.ssafy.happyhouse.dto.Rank;
import com.ssafy.happyhouse.service.UserService;
import com.ssafy.happyhouse.service.UtilService;



@Controller
public class MainController {
	
	@Autowired
	private UserService user;
	
	@Autowired
	private UtilService util;

	// 에러 핸들러 - ModelAndView 객체를 이용하여 에러페이지와 메세지를 담아 return
	@ExceptionHandler
	public ModelAndView handler(Exception ex) {
		ModelAndView mav = new ModelAndView("error/errorHandler");
		mav.addObject("msg", ex.getMessage());
		ex.printStackTrace();
		return mav;
	}

	@GetMapping(value = "/")
	public String mvIndex() {
		return "index";
	}
	@GetMapping(value = "rank")
	public String test() {
		return "rank";
	}
	/*
	 * @GetMapping(value = "/user/login") public String mvLogin() { return "login";
	 * }
	 */
	
	// 회원가입
//	@PostMapping(value = "/user/join")
//	public String mvJoin(@RequestBody UserDto dto) {
//		user.register(dto);
//		return "index";
//	}
	
	// 로그아웃
	@GetMapping(value = "/user/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "index";
	}
	
	// 테스트
	@GetMapping(value = "/map")
	public String map() {
		return "gmap";
	}
	
	// 마이페이지
	@GetMapping(value = "/mypage")
	public String mypage(Model m, HttpSession session) {
		String id = (String) session.getAttribute("suser");
		m.addAttribute("u", user.search(id));
		return "UserView";
	}
	
	// 뉴스 크롤링
	@ResponseBody
	@GetMapping(value="/news")
	public List<NewsDto> news() {
		System.out.println("크롤링 시작");
		String url = "https://land.naver.com/news/headline.nhn?bss_ymd=20200615";
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		List<NewsDto> list = new ArrayList<NewsDto>();
		
		int index = 0;
		String uurl = "https://land.naver.com";
		String imgUrl = "";
		Elements element = doc.select(".headline_list");
		for(Element el:element.select("dt")) {
			if(index>=4) break;
			index++;
			if(el.className().contentEquals("photo")) { 
				uurl+= el.select("a").attr("href");
				continue;
			}
			String title = el.text();
			if(index%2==0) {
				list.add(new NewsDto(uurl,title,imgUrl));
				uurl = "https://land.naver.com";
				imgUrl = "";
			}
		}
		for(Element el:element.select("dd")) {
			NewsDto dto = list.remove(0);
			dto.setContents(el.text());
			list.add( dto);
		}
		
		for(int i=0;i<2;i++) {
			url = list.get(0).getUrl();
			doc = null;
			try {
				doc = Jsoup.connect(url).get();
			} catch (IOException e) {
				e.printStackTrace();
			} 
			Elements el2 = doc.select(".end_photo_org");
			imgUrl = el2.select("img").attr("src");
			NewsDto dto = list.remove(0);
			dto.setImgUrl(imgUrl);
			list.add(dto);
		}
		return list;
	}
	// 매물 순위 크롤링
		@ResponseBody
		@GetMapping(value="/rank/{dong}")
		public List<Rank> rank(@PathVariable String dong,HttpServletRequest req) {
			String dongcode = util.getdongcode(dong);
			System.out.println("매물 순위 크롤링 시작");
			String url = "http://aptpr.co.kr/apt/include/graph/apt_rankingAll.jsp?main=true&area=";
			System.out.println(dong);
			System.out.println(dongcode);
			url+=dongcode;
			Document doc = null;
			try {
				doc = Jsoup.connect(url).get();
			} catch (IOException e) {
				e.printStackTrace();
			} 
			List<Rank> list = new ArrayList<Rank>();
			Elements element = doc.select(".tbl_type02 tbody tr");
			for (Element el : element) {
				String[] temp = new String[7];
				System.out.println(el.select("th").text());
				temp[0] = el.select("th").text();
				int index = 1;
				for (Element el2 : el.select("td")) {
					temp[index++] = el2.text();
				}
				list.add(new Rank(temp[0],temp[1],temp[2],temp[3],temp[4],temp[5],temp[6]));
					if(list.size()==12) break;
			}
			// 사진 입력
			int len = list.size();
			for (int j = 0; j <len; j++) {
				Rank dto = list.get(0);
				url = req.getRealPath("/");

				url = url + "\\WEB-INF\\views\\img";
				File path2 = new File(url);
				String fileList[] = path2.list(new FilenameFilter() {
					@Override
					public boolean accept(File dir, String name) {
						String temp = dto.getAptName().trim();
						if (temp.length() <= 5) {
							return name.contains(temp);
						} else {
							return name.contains(temp.substring(0, 5)); // pattern 형식으로 시작하는(여기서는 2019로 시작하는 이름)
						}
					}
				});
				boolean check = true;
				if (fileList.length > 0) {
					for (int i = 0; i < fileList.length; i++) {
						dto.setImg(fileList[i].trim());
						check = false;
						break;
					}
				}
				if (check)
					dto.setImg("다세대주택.jpg");
				System.out.println(dto.getImg());
				list.add(dto);
				list.remove(0);
			}
			
			return list;
		}
	
	//주택정보
	//상권정보
	
	//QnA
	
	//공지사항
	//MyPage
	//Logout
	
}
