package com.ssafy.happyhouse.controller;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.happyhouse.dto.HouseDeal;
import com.ssafy.happyhouse.dto.HouseInfo;
import com.ssafy.happyhouse.dto.HousePageBean;
import com.ssafy.happyhouse.dto.UtilDto;
import com.ssafy.happyhouse.service.HouseDealService;
import com.ssafy.happyhouse.service.HouseInfoService;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/house")
public class HouseController {

	@Autowired
	private HouseInfoService his;
	@Autowired
	private HouseDealService hds;
	
	@ResponseBody
	@GetMapping(value = "/list/{dong}/{page}")
    public ResponseEntity<HousePageBean> searchDongPage(@PathVariable String dong,@PathVariable String page) {
    	HousePageBean bean = new HousePageBean();
    	bean.setDong(dong);
    	int searchCnt = hds.searchHouseDealCnt(bean);
    	int curPage;
    	System.out.println(page);
    	if(page.equals("undefined")) {
    		curPage = 1;
    	}else {
    		curPage = Integer.parseInt(page);
    	}
    	bean.setPage(searchCnt,curPage);
    	System.out.println("*****************");
    	System.out.println(bean);
    	return new ResponseEntity<HousePageBean>(bean, HttpStatus.OK);
	}
	
	@GetMapping(value = "/list2")
	public String houseDealList(HttpServletRequest req, Model m, HttpSession session) {
		HousePageBean hpb = new HousePageBean();
		String searchField = (String) req.getParameter("searchField");
		if (searchField == null)
			searchField = "";
		searchField = searchField.trim();
		String searchText = (String) req.getParameter("aptName");
		searchText = searchText.trim();

		if (searchText == "") {
			searchText = (String) session.getAttribute("searchAPT");
		} else {
			session.setAttribute("searchAPT", searchText);
		}
		searchText = searchText.trim();
		String pageStr = (String) req.getParameter("page");
		System.out.println(pageStr);
		int curPage;
		if (pageStr == null)
			curPage = 1;
		else
			curPage = Integer.parseInt(pageStr);
		int searchCnt;

		List<HouseDeal> list = null;
		switch (searchField) {
		case "DONG":
			hpb.setDong(searchText);
			searchCnt = hds.searchHouseDealCnt(hpb);
			hpb.setPage(searchCnt, curPage);
			list = hds.searchDong(hpb);
			break;
		case "NAME":
			hpb.setaptName(searchText);
			searchCnt = hds.searchHouseDealCnt(hpb);
			hpb.setPage(searchCnt, curPage);
			list = hds.searchHouseName(hpb);
			break;
		case "LIST":
		default:
			hpb.setDong(searchText);
			hpb.setaptName(searchText);
			searchCnt = hds.searchHouseDealCnt(hpb);
			System.out.println("*******************"+searchCnt);
			hpb.setPage(searchCnt, curPage);
			list = hds.searchDong(hpb);
			break;
		}
		m.addAttribute("housedeals", list);
		m.addAttribute("searchField", searchField);
		m.addAttribute("searchText", searchText);
		m.addAttribute("housePagination", hpb);

		return "housedeallist";
	}

	// /houseDeal.do/houseDealCnt
	@ResponseBody
	@GetMapping(value = "/houseDeal/houseDealCnt")
	public HousePageBean houseDealCnt(HousePageBean bean) {
		int cnt = hds.searchHouseDealCnt(bean);
		bean.setPage(cnt, bean.getPage());
		return bean;
	}

	// /houseDeal.do/houseDealFavList
	@ResponseBody
	@PostMapping(value = "/houseDeal/houseDealFavList")
	public List<HouseDeal> houseDealCnt(HousePageBean bean, Model m) {
		int cnt = hds.searchHouseDealCnt(bean);
		bean.setPage(cnt, bean.getPage());
//		List<HouseDeal> list = hds.searchMulti(bean);
		List<HouseDeal> list = hds.searchDong(bean.getDong());
		return list;
	}

	@GetMapping(value = "/img/{dong}/{name}")
	@ResponseBody
	public ResponseEntity<HouseInfo> houseDealDetail2(@PathVariable String dong, @PathVariable String name,
			HttpServletRequest req) {
		String url = req.getRealPath("/");
		dong = dong.trim();
		name = name.trim();
		HouseDeal temp = new HouseDeal();
		temp.setDong(dong);
		temp.setAptName(name);
		HouseInfo dto = his.search(temp);
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
		return new ResponseEntity<HouseInfo>(dto, HttpStatus.OK);
	}

	// /houseDeal.do/houseDealDetail2
	@GetMapping(value = "/Cdetail/{aptName}")
	public String houseDealDetail2(@PathVariable String aptName, Model m, HttpServletRequest req) {
		List<HouseDeal> houseDeals = null;
		// 거래 리스트를 받아야 한다.
		houseDeals = hds.searchDetail(aptName);
		int len = houseDeals.size() - 1;
		for (int j = len; j >= 0; j--) {
			HouseDeal dto = houseDeals.get(j);
			String url = req.getRealPath("/");

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
			houseDeals.add(dto);
			houseDeals.remove(j);
		}
		m.addAttribute("housedeals", houseDeals);

		return "housedealdetail";
	}
	

}
