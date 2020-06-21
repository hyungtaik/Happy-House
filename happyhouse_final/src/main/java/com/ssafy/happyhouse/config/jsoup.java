package com.ssafy.happyhouse.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ssafy.happyhouse.dto.NewsDto;

public class jsoup {
	public static void main(String[] args) {
		// https://land.naver.com/news/headline.nhn?bss_ymd=20200615
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
//				imgUrl = el.select("img").attr("src");
				continue;
			}
//			System.out.println(el.text());
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
		
		for(NewsDto dto:list) {
			System.out.println(dto);
		}
		
		index = 0;
		System.out.println("============================================================");
		Elements element2 = doc.select(".photo a");
		for(Element el:element2) {
//			System.out.println(el.attr("href"));
		}
		
		index = 0;
		System.out.println("============================================================");
		Elements element3 = doc.select(".headline_list img");
		
		for(Element el:element3) {
//			System.out.println(el.attr("src"));
		}
	}
}
