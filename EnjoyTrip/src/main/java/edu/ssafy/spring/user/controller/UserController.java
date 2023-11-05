package edu.ssafy.spring.user.controller;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.ssafy.spring.user.dto.UserDto;
import edu.ssafy.spring.user.model.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UserService service;
	
	@Autowired
	public UserController(UserService service) {
		super();
		this.service = service;
	}
	
    @PostMapping("/update")
    public String update(@ModelAttribute UserDto userDto, HttpSession session) {
        String salt = service.getSalt(userDto.getUserId());
        userDto.setSalt(salt);
        service.updateUser(userDto);
        UserDto userInfo = service.getUser(userDto.getUserId());
        session.setAttribute("userInfo", userInfo);
        return "include/updatesuccess";
    }
	
    @PostMapping("/delete")
    public String delete(@RequestParam String userId) {
        int cnt = service.deleteUser(userId);
        return cnt > 0 ? "include/deletesuccess" : "include/deletefail";
    }

    @PostMapping("/regist")
    public String regist(@ModelAttribute UserDto userDto) throws IOException {
    	try {
    		service.registUser(userDto);
    		return "include/joinsuccess";
    	} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
        
    }
    
    @GetMapping("/join")
	private String join() {
    	return "user/join";
	}
    
    @GetMapping("/login")
	private String login() {
    	return "user/login";
	}
    
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "/index";
	}
	
	@GetMapping("/mypage")
	public String mypage() {
		return "user/mypage";
	}
    
    @PostMapping("/login")
	private String login(@ModelAttribute UserDto userDto, 
						 @RequestParam(required = false) String remember,
						 HttpServletRequest request,
						 HttpServletResponse response,
						 HttpSession session) throws IOException {
		boolean login = service.loginUser(userDto);
		
		if(login) {
			String userId = userDto.getUserId();
			UserDto userInfo = service.getUser(userId);
			session.setAttribute("userInfo", userInfo);
			if(remember != null) {
				Cookie c = new Cookie("id", userId);
				c.setPath("/");
				c.setMaxAge(60*60*24);
				response.addCookie(c);
			} else {
				Cookie c = new Cookie("id", userId);
				c.setMaxAge(0);
				response.addCookie(c);
			}
			return "include/loginsuccess";
		} else {
			return "include/loginfail";
		}
		
	}
	
    @PostMapping("/find")
    public String find(@ModelAttribute UserDto userDto) {
        boolean find = service.findUser(userDto);
        return find ? "redirect:/include/findsuccess" : "redirect:/include/findfail";
    }

}
