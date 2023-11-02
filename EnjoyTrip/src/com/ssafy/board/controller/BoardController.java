package com.ssafy.board.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.board.dto.BoardDto;
import com.ssafy.board.model.service.BoardService;
import com.ssafy.board.model.service.BoardServiceImpl;
import com.ssafy.comment.dto.CommentDto;
import com.ssafy.comment.service.CommentService;
import com.ssafy.comment.service.CommentServiceImpl;
import com.ssafy.user.dto.UserDto;
import com.ssafy.util.PageNavigation;
import com.ssafy.util.ParameterCheck;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private int pgno;
	private String key;
	private String word;
	private String queryStrig;
	
	private BoardService boardService;
	private CommentService commentService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		boardService = BoardServiceImpl.getBoardService();
		commentService = CommentServiceImpl.getCommentService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		
		pgno = ParameterCheck.notNumberToOne(request.getParameter("pgno"));
		key = ParameterCheck.nullToBlank(request.getParameter("key"));
		word = ParameterCheck.nullToBlank(request.getParameter("word"));
		queryStrig = "pgno=" + pgno + "&key=" + key + "&word=" + URLEncoder.encode(word, "utf-8");
		
		String path = "";
		if ("list".equals(action)) {
			path = list(request, response);
			forward(request, response, path);
		} else if ("view".equals(action)) {
			path = view(request, response);
			forward(request, response, path);
		} else if ("mvwrite".equals(action)) {
			path = "/board/write.jsp";
			redirect(request, response, path);
		} else if ("write".equals(action)) {
			path = write(request, response);
			redirect(request, response, path);
		} else if ("mvmodify".equals(action)) {
			path = mvModify(request, response);
			forward(request, response, path);
		} else if ("modify".equals(action)) {
			path = modify(request, response);
			forward(request, response, path);
		} else if ("delete".equals(action)) {
			path = delete(request, response);
			redirect(request, response, path);
		} else if("commentAdd".equals(action)){
			path = commentAdd(request, response);
			redirect(request, response, path);
		} else if("commentDelete".equals(action)){
			path = commentDelete(request, response);
			redirect(request, response, path);
		} else {
			redirect(request, response, path);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

	private void forward(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

	private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
		response.sendRedirect(request.getContextPath() + path);
	}

	private String list(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("pgno", pgno + ""); // 페이지 번호
			map.put("key", key); // 검색 조건
			map.put("word", word); // 검색어
			
			// 추가 -> session에 pgno 붙여서 보냄(글목록)
			request.getSession().setAttribute("pgno", pgno);
			
			// step 1. 글 리스트
			List<BoardDto> list = boardService.listArticle(map);
			request.setAttribute("articles", list);
			
			// step 2. 페이지 번호 만들어서 request에 붙여 보냄
			PageNavigation pageNavigation = boardService.makePageNavigation(map);
			request.setAttribute("navigation", pageNavigation);
			
			return "/board/list.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "글목록 출력 중 문제가 발생했어요!😥");
			return "/error/error.jsp";
		}

	}

	private String view(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		UserDto userDto = (UserDto) session.getAttribute("userInfo");
		int articleNo = Integer.parseInt(request.getParameter("articleno"));
		try {
			BoardDto boardDto = boardService.getArticle(articleNo);
			boardService.updateHit(articleNo);
			request.setAttribute("article", boardDto);

			// 추가 -> 댓글 request에 붙여 보냄
			List<CommentDto> list = commentService.listComment(articleNo);
			request.setAttribute("comments", list);
			
			return "/board/view.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "글내용 출력 중 문제가 발생했어요!😥");
			return "/error/error.jsp";
		}
	}

	private String write(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		UserDto userDto = (UserDto) session.getAttribute("userInfo");
		if (userDto.getUserId() != null) {
			System.out.println(userDto.getUserId());
			BoardDto boardDto = new BoardDto();
			boardDto.setUserId(userDto.getUserId());
			boardDto.setSubject(request.getParameter("subject"));
			boardDto.setContent(request.getParameter("content"));
			try {
//				// test 생성
//				String subject = boardDto.getSubject();
//				String content = boardDto.getContent();
//				for(int i=1;i<=100;i++) {
//					boardDto.setSubject(subject+" test subject no."+i);
//					boardDto.setContent(content+" test content no."+i);
//					boardService.writeArticle(boardDto);
//					Thread.sleep(1000);
//				}
				boardService.writeArticle(boardDto);
//				System.out.println("write 완료");
//				return "/article?action=list" + queryStrig;
				return "/board?action=list";
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "글작성 중 문제가 발생했어요!😥");
				return "/error/error.jsp";
			}
		} else
			return "/user/login.jsp";
	}

	private String mvModify(HttpServletRequest request, HttpServletResponse response) {
		// TODO : 수정하고자하는 글의 글번호를 얻는다.
		// TODO : 글번호에 해당하는 글정보를 얻고 글정보를 가지고 modify.jsp로 이동.
		try {
			HttpSession session = request.getSession();
			UserDto userDto = (UserDto) session.getAttribute("userInfo");
			
			if(userDto.getUserId() != null) {
				int articleNo = Integer.parseInt(request.getParameter("articleno"));
				BoardDto boardDto = boardService.getArticle(articleNo);
				request.setAttribute("article", boardDto);
				
				return "/board/modify.jsp";
			} else
				return "/user/login.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "글을 가져오다가 문제가 발생했어요!😥");
			return "/error/error.jsp";
		}
	}

	private String modify(HttpServletRequest request, HttpServletResponse response) {
		// TODO : 수정 할 글정보를 얻고 BoardDto에 set.
		// TODO : boardDto를 파라미터로 service의 modifyArticle() 호출.
		// TODO : 글수정 완료 후 view.jsp로 이동.(이후의 프로세스를 생각해 보세요.)
		HttpSession session = request.getSession();
		UserDto userDto = (UserDto) session.getAttribute("userInfo");
		
		if(userDto.getUserId() != null) {
			BoardDto boardDto = new BoardDto();
			boardDto.setArticleNo(Integer.parseInt(request.getParameter("articleno")));
			boardDto.setSubject(request.getParameter("subject"));
			boardDto.setContent(request.getParameter("content"));
			
			try {
				boardService.modifyArticle(boardDto);
//				return "/article?action=list&pgno=N&key=&word=";
				return "/board?action=list";
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "글수정 중 문제가 발생했어요!😥");
				return "/error/error.jsp";
			}
			
		} else
			return "/user/login.jsp";
	}

	private String delete(HttpServletRequest request, HttpServletResponse response) {
		// TODO : 삭제할 글 번호를 얻는다.
		// TODO : 글번호를 파라미터로 service의 deleteArticle()을 호출.
		// TODO : 글삭제 완료 후 list.jsp로 이동.(이후의 프로세스를 생각해 보세요.)
		HttpSession session = request.getSession();
		UserDto userDto = (UserDto) session.getAttribute("userInfo");
		if(userDto != null) {
			int articleNo = Integer.parseInt(request.getParameter("articleno"));
			
			try {
				boardService.deleteArticle(articleNo);
				commentService.deleteComments(articleNo);
				
//				return "/board?action=list";
				int pgno = (int) request.getSession().getAttribute("pgno");
				return "/board?action=list&pgno="+pgno+"&key=&word=#";				
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "글삭제 중 문제가 발생했어요!😥");
				return "/error/error.jsp";
			}
			
		} else
			return "/user/login.jsp";
	}

	private String commentAdd(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		UserDto userDto = (UserDto) session.getAttribute("userInfo");
		int articleNo = Integer.parseInt(request.getParameter("articleNo"));
		
		if (userDto.getUserId() != null) {
			CommentDto commentDto = new CommentDto();
			commentDto.setArticleNo(articleNo);
			commentDto.setUserId(userDto.getUserId());
			commentDto.setCommentContent(request.getParameter("comment"));
			
			try {
				commentService.addComment(commentDto);
				boardService.updateCommentCnt(commentDto);
				return "/board?action=view&articleno=" + articleNo; 
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "댓글 작성 중 문제가 발생했어요!😥");
				return "/error/error.jsp";
			}
		} else
			return "/user/login.jsp";
	}
	
	private String commentDelete(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		UserDto userDto = (UserDto) session.getAttribute("userInfo");
		int articleNo = Integer.parseInt(request.getParameter("articleno"));
		int commentNo = Integer.parseInt(request.getParameter("commentno"));
		
		if (userDto.getUserId() != null) {
			try {
				commentService.deleteComment(commentNo);
				boardService.deleteCommentCnt(articleNo);
				return "/board?action=view&articleno=" + articleNo; 
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "댓글 작성 중 문제가 발생했어요!😥");
				return "/error/error.jsp";
			}
		} else
			return "/user/login.jsp";
	}

}
