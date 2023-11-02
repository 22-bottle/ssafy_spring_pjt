package com.ssafy.user.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.user.dto.UserDto;
import com.ssafy.user.model.service.UserService;
import com.ssafy.user.model.service.UserServiceImpl;
import com.ssafy.util.Encrypt;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UserService service;
	
	@Override
	public void init() throws ServletException {
		super.init();
		service = new UserServiceImpl();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String path = "";
		try {
			if(action.equals("login")) {
				path = login(request, response);
			}else if(action.equals("logout")) {
				request.getSession().invalidate();
				path = "redirect:/index.jsp";
			}else if(action.equals("regist")) {
				path = regist(request, response);
			}else if("mvlogin".equals(action)) {
				path = "redirect:/user/login.jsp";
			}else if("find".equals(action)) {
				path = find(request, response);
			}else if("update".equals(action)) {
				path = update(request, response);
			}else if("delete".equals(action)) {
				path = delete(request, response);
			}
		} catch (Exception e) {
			path = "/error/error.jsp";
			response.sendRedirect(path);
		}
		
		if(path.startsWith("redirect")) {
			path = path.substring(path.indexOf(":") + 1);
			path = request.getContextPath() + path;
			response.sendRedirect(path);
		} else {
			request.getRequestDispatcher(path).forward(request, response);
		}
	}

	private String update(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		String emailId = request.getParameter("emailId");
		String emailDomain = request.getParameter("emailDomain");
		String salt = service.getSalt(userId);
		
		UserDto userDto = new UserDto();
		userDto.setUserId(userId);
		userDto.setUserName(userName);
		userDto.setUserPassword(userPassword);
		userDto.setEmailId(emailId);
		userDto.setEmailDomain(emailDomain);
		userDto.setSalt(salt);
		
		System.out.println("userDto");
		System.out.println(userDto.toString());
		
		service.updateUser(userDto);
		UserDto userInfo = service.getUser(userDto.getUserId());
		request.getSession().setAttribute("userInfo", userInfo);
		
		return "/include/updatesuccess.jsp";
	}
	
	private String delete(HttpServletRequest request, HttpServletResponse response) {
//		String userId = request.getParameter("userId");
		System.out.println("삭제중");
		String userId = request.getParameter("userId");
		System.out.println(userId);
		
		int cnt = service.deleteUser(userId);
		
		if(cnt > 0) {
			return "/include/deletesuccess.jsp";
		} else {
			return "/include/deletefail.jsp";
		}
	}

	private String regist(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		String emailId = request.getParameter("emailId");
		String emailDomain = request.getParameter("emailDomain");
		
		UserDto userDto = new UserDto();
		userDto.setUserId(userId);
		userDto.setUserName(userName);
		userDto.setUserPassword(userPassword);
		userDto.setEmailId(emailId);
		userDto.setEmailDomain(emailDomain);
		
		int cnt = service.registUser(userDto);
		
		return "/include/joinsuccess.jsp";
	}

	private String login(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("id");
		String userPassword = request.getParameter("pw");
		String remember = request.getParameter("remember");
		
		UserDto userDto = new UserDto();
		userDto.setUserId(userId);
		userDto.setUserPassword(userPassword);
		boolean login = service.loginUser(userDto);
		
		if(login) {
			UserDto userInfo = service.getUser(userDto.getUserId());
			request.getSession().setAttribute("userInfo", userInfo);
			if(remember != null) {
				Cookie c = new Cookie("id", userId);
				c.setMaxAge(60*60*24);
				response.addCookie(c);
			} else {
				Cookie[] cookies = request.getCookies();
				for(Cookie c:cookies) {
					if(c.getName().equals("id")) {
						c.setMaxAge(0);
						response.addCookie(c);
					}
				}
			}
			return "/include/loginsuccess.jsp";
		} else {
			return "/include/loginfail.jsp";
		}
		
	}
	
	private String find(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String userId = request.getParameter("id");
		String userName = request.getParameter("name");
		
		UserDto userDto = new UserDto();
		userDto.setUserId(userId);
		userDto.setUserName(userName);
		
		boolean find = service.findUser(userDto);
		if(find) {
			return "/include/findsuccess.jsp";
		} else {
			return "/include/findfail.jsp";
		}
	}

}
