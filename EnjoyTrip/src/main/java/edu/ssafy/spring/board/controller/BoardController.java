package edu.ssafy.spring.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.ssafy.spring.board.dto.BoardDto;
import edu.ssafy.spring.board.model.service.BoardService;
import edu.ssafy.spring.comment.dto.CommentDto;
import edu.ssafy.spring.comment.model.service.CommentService;
import edu.ssafy.spring.user.dto.UserDto;
import edu.ssafy.spring.util.PageNavigation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

	private BoardService boardService;

	private CommentService commentService;

	@Autowired
	public BoardController(BoardService boardService, CommentService commentService) {
		super();
		this.boardService = boardService;
		this.commentService = commentService;
	}

	@GetMapping("/list")
	public ModelAndView list(@RequestParam Map<String, String> map, ModelAndView mav) {
		log.debug("list parameter pgno : {}", map.get("pgno"));
		try {
			List<BoardDto> list = boardService.listArticle(map);
			PageNavigation pageNavigation = boardService.makePageNavigation(map);
			mav.addObject("articles", list);
			mav.addObject("navigation", pageNavigation);
			mav.addObject("key", map.get("key"));
			mav.addObject("word", map.get("word"));
			mav.setViewName("/board/list");
		} catch (Exception e) {
			e.printStackTrace();
			mav.setViewName("/error/error");
			return mav;
		}
		return mav;
	}

	@GetMapping("/write")
	public ModelAndView write(@RequestParam Map<String, String> map, ModelAndView mav) {
		mav.addObject("pgno", map.get("pgno"));
		mav.addObject("key", map.get("key"));
		mav.addObject("word", map.get("word"));
		mav.setViewName("/board/write");
		return mav;
	}

	@PostMapping("/write")
	public String write(BoardDto boardDto, RedirectAttributes redirectAttributes, HttpSession session) {
		UserDto userDto = (UserDto) session.getAttribute("userInfo");
		if (userDto != null) {
			try {
				boardDto.setUser_id(userDto.getUserId());
				boardService.writeArticle(boardDto);
				redirectAttributes.addAttribute("pgno", "1");
				redirectAttributes.addAttribute("key", "");
				redirectAttributes.addAttribute("word", "");
				return "redirect:/board/list";
			} catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addAttribute("msg", "글작성 중 문제가 발생했어요!😥");
				return "/error/error";
			}
		} else {
			return "/user/login";
		}
	}

	@GetMapping("/view")
	public String view(@RequestParam("articleno") int articleNo, @RequestParam Map<String, String> map, Model model) throws Exception {
		try {
			BoardDto boardDto = boardService.getArticle(articleNo);
			List<CommentDto> list = commentService.listComment(articleNo);
			boardService.updateHit(articleNo);
			model.addAttribute("article", boardDto);
			model.addAttribute("pgno", map.get("pgno"));
			model.addAttribute("key", map.get("key"));
			model.addAttribute("word", map.get("word"));
			model.addAttribute("comments", list);
			return "board/view";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "글내용 출력 중 문제가 발생했어요!😥");
			return "/error/error";
		}
	}
	
	@GetMapping("/modify")
	public String modify(@RequestParam("articleno") int articleNo, @RequestParam Map<String, String> map, Model model, HttpSession session) throws Exception {
		try {
			UserDto userDto = (UserDto) session.getAttribute("userInfo");
			if (userDto.getUserId() != null) {
				BoardDto boardDto = boardService.getArticle(articleNo);
				model.addAttribute("article", boardDto);
				model.addAttribute("pgno", map.get("pgno"));
				model.addAttribute("key", map.get("key"));
				model.addAttribute("word", map.get("word"));
				return "board/modify";
			} else {
				return "/user/login";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "글을 가져오다가 문제가 발생했어요!😥");
			return "/error/error";
		}
	}

	@PostMapping("/modify")
	public String modify(BoardDto boardDto, @RequestParam Map<String, String> map, RedirectAttributes redirectAttributes, HttpSession session) throws Exception {
		UserDto userDto = (UserDto) session.getAttribute("userInfo");
		if(userDto.getUserId() != null) {
			try {
				boardService.modifyArticle(boardDto);
				redirectAttributes.addAttribute("pgno", map.get("pgno"));
				redirectAttributes.addAttribute("key", map.get("key"));
				redirectAttributes.addAttribute("word", map.get("word"));
				return "redirect:/article/list";
			} catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addAttribute("msg", "글수정 중 문제가 발생했어요!😥");
				return "/error/error";
			}
		} else {
			return "/user/login";
		}
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("articleno") int articleNo, 
						 @RequestParam Map<String, String> map, 
						RedirectAttributes redirectAttributes, 
						HttpSession session) throws Exception {
		UserDto userDto = (UserDto) session.getAttribute("userInfo");
		if(userDto != null) {
			try {
				boardService.deleteArticle(articleNo);
				redirectAttributes.addAttribute("pgno", map.get("pgno"));
				redirectAttributes.addAttribute("key", map.get("key"));
				redirectAttributes.addAttribute("word", map.get("word"));
				return "redirect:/article/list";
			} catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addAttribute("msg", "글삭제 중 문제가 발생했어요!😥");
				return "/error/error";
			}
		} else {
			return "/user/login";
		}
	}
	
	 @PostMapping("/commentAdd")
	    public String commentAdd( @RequestParam("articleNo") int articleNo,
	                              @RequestParam("comment") String commentContent,
	                             Model model, 
	                             HttpSession session) {
		 	UserDto userDto = (UserDto) session.getAttribute("userInfo");
	        if (userDto != null && userDto.getUserId() != null) {
	            CommentDto commentDto = new CommentDto();
	            commentDto.setArticleNo(articleNo);
	            commentDto.setUserId(userDto.getUserId());
	            commentDto.setCommentContent(commentContent);

	            try {
	                commentService.addComment(commentDto);
	                boardService.updateCommentCnt(commentDto);
	                return "redirect:/view?articleno=" + articleNo;
	            } catch (Exception e) {
	                e.printStackTrace();
	                model.addAttribute("msg", "댓글 작성 중 문제가 발생했어요!😥");
	                return "/error/error";
	            }
	        } else {
	            return "redirect:/user/login";
	        }
	    }

	    @PostMapping("/commenDelete")
	    public String commentDelete(@RequestParam("articleno") int articleNo,
	                                @RequestParam("commentno") int commentNo,
	                                Model model, 
	                                HttpSession session) {
	    	UserDto userDto = (UserDto) session.getAttribute("userInfo");
	        if (userDto != null && userDto.getUserId() != null) {
	            try {
	                commentService.deleteComment(commentNo);
	                boardService.deleteCommentCnt(articleNo);
	                return "redirect:/board?action=view&articleno=" + articleNo;
	            } catch (Exception e) {
	                e.printStackTrace();
	                model.addAttribute("msg", "댓글 삭제 중 문제가 발생했어요!😥");
	                return "/error/error";
	            }
	        } else {
	            return "redirect:/user/login";
	        }
	    }
	
}
