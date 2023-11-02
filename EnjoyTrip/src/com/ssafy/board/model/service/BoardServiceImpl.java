package com.ssafy.board.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.board.dto.BoardDto;
import com.ssafy.board.model.dao.BoardDao;
import com.ssafy.board.model.dao.BoardDaoImpl;
import com.ssafy.comment.dto.CommentDto;
import com.ssafy.util.PageNavigation;
import com.ssafy.util.SizeConstant;

public class BoardServiceImpl implements BoardService {

	private static BoardService boardService = new BoardServiceImpl();
	private BoardDao boardDao;
	
	private BoardServiceImpl() {
		boardDao = BoardDaoImpl.getBoardDao();
	}

	public static BoardService getBoardService() {
		return boardService;
	}

	@Override
	public void writeArticle(BoardDto boardDto) throws Exception {
		boardDao.writeArticle(boardDto);
	}

	@Override
	public List<BoardDto> listArticle(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		String key = map.get("key");
		String word = map.get("word");
		
		// 검색 조건, 검색어는 검색 하는 경우만 있고, 나머지는 빈 문자열로 넘어옴
		param.put("key", key.isEmpty() ? "" : key); // 검색 조건
		param.put("word", word.isEmpty() ? "" : word); // 검색어
		
		// 페이지 처리
		int pgno = Integer.parseInt(map.get("pgno")); // 시작 페이지
		int start = pgno * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE; // 시작 게시글 번호
		
		param.put("start", start); // 시작 게시글 번호
		param.put("listsize", SizeConstant.LIST_SIZE); // 몇 개 가지고 올건지
		
		return boardDao.listArticle(param);
	}
	
	@Override
	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();

		int naviSize = SizeConstant.NAVIGATION_SIZE; // 이전 | 1 | ... | naviSize | 다음 -> 이 떄의 naviSize
		int sizePerPage = SizeConstant.LIST_SIZE; // 페이지 당 글 수
		int currentPage = Integer.parseInt(map.get("pgno")); // 현재 페이지

		Map<String, Object> param = new HashMap<String, Object>();
		String key = map.get("key");
		String word = map.get("word");
		param.put("key", key.isEmpty() ? "" : key);
		param.put("word", word.isEmpty() ? "" : word);
		
		int totalCount = boardDao.getTotalArticleCount(param); // 총 게시글 갯수
		int totalPageCount = (totalCount - 1) / sizePerPage + 1; // 총 페이지 개수
		boolean startRange = currentPage <= naviSize; // 현재 페이지가 네비게이션 사이즈보다 작으면 이전 버튼 누를 수 없음
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage; // 현재 페이지가 네비게이션 하나당 가능한 페이지보다 작으면 다음 버튼 누를 수 없음
		
		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		pageNavigation.setTotalCount(totalCount);
		pageNavigation.setTotalPageCount(totalPageCount);
		pageNavigation.setStartRange(startRange);
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();

		return pageNavigation;
	}

	@Override
	public BoardDto getArticle(int articleNo) throws Exception {
		return boardDao.getArticle(articleNo);
	}

	@Override
	public void updateHit(int articleNo) throws Exception {
		boardDao.updateHit(articleNo);
	}

	@Override
	public void modifyArticle(BoardDto boardDto) throws Exception {
		boardDao.modifyArticle(boardDto);
	}

	@Override
	public void deleteArticle(int articleNo) throws Exception {
		boardDao.deleteArticle(articleNo);
	}

	@Override
	public void updateCommentCnt(CommentDto commentDto) throws SQLException {
		boardDao.updateCommentCnt(commentDto);
	}

	@Override
	public void deleteCommentCnt(int articleNo) throws SQLException {
		boardDao.deleteCommentCnt(articleNo);
		
	}
}
