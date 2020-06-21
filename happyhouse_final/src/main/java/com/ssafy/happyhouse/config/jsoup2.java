package com.ssafy.happyhouse.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ssafy.happyhouse.dto.NewsDto;
import com.ssafy.happyhouse.dto.Rank;

public class jsoup2 {
	public static void main(String[] args) {
		// https://land.naver.com/news/headline.nhn?bss_ymd=20200615
		String url = "http://aptpr.co.kr/apt/include/graph/apt_rankingAll.jsp?main=true&area=";
		url += "11680";
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<Rank> list = new ArrayList<Rank>();
		Elements element = doc.select(".tbl_type02 a");
		for (Element el : element) {
			String[] temp = new String[7];
//			String temp = "<td>"+el.select("th").text()+"</td>"+el.select("td").toString();
//			list.add(new Rank(el.select("th").text(), temp));
//			list.add(new Rank(el.select("th").text(), el.select("td").toString()));
//			System.out.println(el.select("th").text());
			System.out.println(el.attr("j"));
//			temp[0] = el.select("th").text();
//				System.out.println(temp);
//			int index = 1;
				System.out.println("***************");
//			for (Element el2 : el.select("td")) {
//				temp[index++] = el2.text();
//				System.out.println(el2.text());
//			}
			list.add(new Rank(temp[0],temp[1],temp[2],temp[3],temp[4],temp[5],temp[6]));
//				System.out.println(el.select("td").toString());
				if(list.size()==10) break;
		}

		Elements element2 = doc.select(".photo a");
		for (Element el : element2) {
//			System.out.println(el.attr("href"));
		}

		Elements element3 = doc.select(".headline_list img");

		for (Element el : element3) {
//			System.out.println(el.attr("src"));
		}
	}
}
