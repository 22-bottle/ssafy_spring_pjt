package com.ssafy.trip.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.trip.dto.AreaDto;
import com.ssafy.trip.dto.AttractionInfoDto;
import com.ssafy.trip.model.service.TripServiceImpl;

@WebServlet("/trip")
public class TripController extends HttpServlet {
	TripServiceImpl service;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void init() throws ServletException {
		super.init();
		service = new TripServiceImpl();
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
	    String regcodePattern = request.getParameter("regcode_pattern");
	    String regcode = request.getParameter("regcode");
	    String area = request.getParameter("area");
	    
        if ("trip".equals(action)) {
            String tripPage = "/trip/trip.jsp";
            forward(request, response, tripPage);
        } else if ("detail".equals(regcodePattern)) {
	    	String[] search = regcode.split(" ");
	    	AttractionInfoDto attractionInfoDto = new AttractionInfoDto();
			attractionInfoDto.setSidoCode(Integer.parseInt(search[1]));
			attractionInfoDto.setContentTypeId(Integer.parseInt(search[search.length-1]));
			List<AttractionInfoDto> list = TripServiceImpl.getAttractionDao().attractionList(attractionInfoDto);
			StringBuilder json = new StringBuilder("{\"regcodes\": [");
			
			for(int i = 0; i < list.size(); i++) {
				AttractionInfoDto article = list.get(i);
				json.append("{"+"\""+"title"+"\""+":"
						   +"\""+article.getTitle()+"\","
						   +"\""+"addr"+"\""+":"
						   +"\""+article.getAddr1()+"\","
						   +"\""+"img"+"\""+":"
						   +"\""+article.getFirstImage()+"\"}");
				if (i != list.size() - 1) {
					json.append(",");
				}
			}
			json.append("]}");
            // JSON 데이터를 클라이언트로 전송
            PrintWriter out = response.getWriter();
            out.print(json);
            out.flush();
        } else {
            // JSON 데이터 생성
            AreaDto trip = new AreaDto();
            String set = service.searchArea(regcodePattern, regcode, area);
            trip.setArea(set);
            String json = trip.getArea();
           
    	    System.out.println(json);
            // JSON 데이터를 클라이언트로 전송
            PrintWriter out = response.getWriter();
            out.print(json);
            out.flush();
        }
    }
	
	public void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request,response);
	}
	
}
