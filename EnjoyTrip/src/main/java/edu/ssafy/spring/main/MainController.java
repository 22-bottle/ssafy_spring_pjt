package edu.ssafy.spring.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/main")
@Slf4j
public class MainController {
	
	@GetMapping("/index")
	public String index() {
		return "/index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "/user/login";
	}
	
	@GetMapping("/join")
	public String join() {
		return "/user/join";
	}
	
	@GetMapping("/mypage")
	public String mypage() {
		return "/user/mypage";
	}
	
	@GetMapping("/joinsuccess")
	public String joinsuccess() {
		return "/include/joinsuccess";
	}
	
	@GetMapping("/loginsuccess")
	public String loginsuccess() {
		return "/include/loginsuccess";
	}
	
	@GetMapping("/loginfail")
	public String loginfail() {
		return "/include/loginfail";
	}
	
	@GetMapping("/find")
	public String find() {
		return "/user/find";
	}
	
}
