package edu.ssafy.spring.user.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "redirect:/include/updatesuccess";
    }
	
    @PostMapping("/delete")
    public String delete(@RequestParam String userId) {
        int cnt = service.deleteUser(userId);
        return cnt > 0 ? "redirect:/include/deletesuccess.jsp" : "redirect:/include/deletefail.jsp";
    }

    @PostMapping("/regist")
    public String regist(@ModelAttribute UserDto userDto) {
        service.registUser(userDto);
        return "redirect:/include/joinsuccess.jsp";
    }

    @PostMapping("/login")
	private String login(@ModelAttribute UserDto userDto, 
						 @RequestParam String remember,
						 HttpServletRequest request,
						 HttpServletResponse response,
						 HttpSession session) {
		boolean login = service.loginUser(userDto);
		
		if(login) {
			String userId = userDto.getUserId();
			UserDto userInfo = service.getUser(userId);
			session.setAttribute("userInfo", userInfo);
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
			return "redirect:/include/loginsuccess";
		} else {
			return "redirect:/include/loginfail";
		}
		
	}
	
    @PostMapping("/find")
    public String find(@ModelAttribute UserDto userDto) {
        boolean find = service.findUser(userDto);
        return find ? "redirect:/include/findsuccess" : "redirect:/include/findfail";
    }

}
