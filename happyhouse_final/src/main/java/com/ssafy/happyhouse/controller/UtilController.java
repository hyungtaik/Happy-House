package com.ssafy.happyhouse.controller;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.UtilDto;
import com.ssafy.happyhouse.service.HouseDealService;
import com.ssafy.happyhouse.service.HouseInfoService;
import com.ssafy.happyhouse.dto.HouseDeal;
import com.ssafy.happyhouse.dto.HouseInfo;
import com.ssafy.happyhouse.dto.HousePageBean;
import com.ssafy.happyhouse.service.UtilService;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/map")
public class UtilController {

	@Autowired
	private UtilService service;
	
	@Autowired
	private HouseInfoService infoService;
	
	@Autowired
	private HouseDealService dealService;

    @ApiOperation(value = "시 목록을 반환한다.", response = List.class)
	@GetMapping(value = "/city")
	public ResponseEntity<List<UtilDto>> getcity() {
		return new ResponseEntity<List<UtilDto>>(service.getcity(), HttpStatus.OK);
	}
	
    @ApiOperation(value = "구/군 목록을 반환한다.", response = List.class)
	@GetMapping(value = "/gu/{city}")
	public ResponseEntity<List<UtilDto>> getgu(@PathVariable String city) {
    	return new ResponseEntity<List<UtilDto>>(service.getgu(city), HttpStatus.OK);
	}
	
    @ApiOperation(value = "동 목록을 반환한다.", response = List.class)
	@GetMapping(value = "/dong/{gu}")
	public ResponseEntity<List<UtilDto>> getdong(@PathVariable String gu) {
    	return new ResponseEntity<List<UtilDto>>(service.getdong(gu), HttpStatus.OK);
	}
    
    
    @ApiOperation(value = "동 이름으로 거래내역 출력", response = List.class)
    @GetMapping(value = "/apt/{dong}")
    public ResponseEntity<List<HouseDeal>> searchDong(@PathVariable String dong) {
    	System.out.println("/apt/dong");
    	return new ResponseEntity<List<HouseDeal>>(service.selectApt(dong), HttpStatus.OK);
	}
    
    @ApiOperation(value = "동 이름으로 거래내역 출력", response = List.class)
    @GetMapping(value = "/aptPage/{dong}/{page}")
    public ResponseEntity<List<HouseDeal>> searchDongPage(@PathVariable String dong,@PathVariable String page,HttpSession session) {
    	System.out.println("/apt/dong");
    	HousePageBean bean = new HousePageBean();
    	bean.setDong(dong);
    	int searchCnt = dealService.searchHouseDealCnt(bean);
    	int curPage;
    	if(page.equals("undefined")) {
    		curPage = 1;
    	}else {
    		curPage = Integer.parseInt(page);
    	}
    	bean.setPage(searchCnt,curPage);
    	System.out.println("*****************");
    	System.out.println(bean);
//    	session.setAttribute("housePagination2", bean);
    	System.out.println(dealService.searchDong(bean));
    	return new ResponseEntity<List<HouseDeal>>(dealService.searchDong(bean), HttpStatus.OK);
	}
    
    
    @ApiOperation(value = "동 이름으로 house정보 목록 출력", response = List.class)
    @GetMapping(value = "/info/{dong}")
    public ResponseEntity<List<HouseInfo>> searchInfo(@PathVariable String dong,HttpServletRequest req) {
    	List<HouseInfo> list = infoService.searchAllHouseInfo(dong);
    	String url = req.getRealPath("/");
		dong = dong.trim();
		url = url + "\\WEB-INF\\views\\img";
		File path2 = new File(url);
		int len = list.size();
		for(int j=0;j<len;j++) {
			HouseInfo dto = list.get(0);
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
			list.remove(0);
			list.add(dto);
		}
    	return new ResponseEntity<List<HouseInfo>>(list, HttpStatus.OK);
	}
   
}
