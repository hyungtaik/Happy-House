package com.ssafy.happyhouse.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//root로 시작하는 모든것들에 적용하겠다.
//보통은 web.xml에다 설정함
//@WebFilter("/*")
public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	//로그인 체그
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		req.setCharacterEncoding("utf-8");
		
		//상위 클래스로 형변환
		HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse) res;

        //uri를 가져옴(param빼고)
        String path = httpServletRequest.getRequestURI();
        String action = httpServletRequest.getParameter("action");

        System.out.println("doFilter path : " + path ); 

        HttpSession session = httpServletRequest.getSession();
        
        
        //redirect 횟수가 너무 많음
//        String user= (String) session.getAttribute("user");
//        if(user == null) {
//            httpServletResponse.sendRedirect("index.jsp");
//            return;
//        }
        // exclude
        // index.jsp
        // .gif
        // main.do?action=LOGIN
        if( ! path.contains("index.jsp") 
                && ! path.contains(".gif") 	//반드시 필요
                && !path.contains(".bmp")
                && !path.contains("/user")
                && !path.contains("/css")
                && !path.contains("/js")
                && !path.contains("/assets")
                && !path.contains("/FSelectBoxController")
                && ! ( path.contains("/user") && "LOGIN".equals(action) ) //반드시 필요 //main.do와 LOGIN 은 제외시켜야함
                ) {
            String user= (String) session.getAttribute("suser");
            if(user == null) {
                httpServletResponse.sendRedirect("index.jsp");
                return;
            }
        }

        
        //반드시 넣어야함. 뒤까지 filtering 하기 위해
        chain.doFilter(req, res);
		
	}

	@Override
	public void init(FilterConfig req) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
}
