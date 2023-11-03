package edu.ssafy.spring.board.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ssafy.spring.board.dto.BoardDto;
import edu.ssafy.spring.board.model.mapper.BoardMapper;
import edu.ssafy.spring.comment.dto.CommentDto;
import edu.ssafy.spring.util.PageNavigation;
import edu.ssafy.spring.util.SizeConstant;

@Service
public class BoardServiceImpl implements BoardService {
	
	private BoardMapper boardMapper;

	@Autowired
	public BoardServiceImpl(BoardMapper boardMapper) {
		super();
		this.boardMapper = boardMapper;
	}

	@Override
	@Transactional
	public void writeArticle(BoardDto boardDto) throws Exception {
		boardMapper.writeArticle(boardDto);
	}

	@Override
	public List<BoardDto> listArticle(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		String key = map.get("key");
		if("userid".equals(key))
			key = "b.user_id";
		param.put("key", key == null ? "" : key);
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int pgNo = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
		int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
		param.put("start", start);
		param.put("listsize", SizeConstant.LIST_SIZE);

		return boardMapper.listArticle(param);
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
		System.out.println(map);
//		param.put("key", key == null ? "" : key);
//		param.put("word", word == null ? "" : word);
		param.put("key", key);
		param.put("word", word);
		
		int totalCount = boardMapper.getTotalArticleCount(param); // 총 게시글 갯수
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
		return boardMapper.getArticle(articleNo);
	}

	@Override
	public void updateHit(int articleNo) throws Exception {
		boardMapper.updateHit(articleNo);
	}

	@Override
	public void modifyArticle(BoardDto boardDto) throws Exception {
		// TODO : BoardDaoImpl의 modifyArticle 호출
		boardMapper.modifyArticle(boardDto);
	}

	@Override
	@Transactional
	public void deleteArticle(int articleNo) throws Exception {
		boardMapper.deleteArticle(articleNo);
	}

	@Override
	public void updateCommentCnt(CommentDto commentDto) throws SQLException {
		boardMapper.updateCommentCnt(commentDto);
	}

	@Override
	public void deleteCommentCnt(int articleNo) throws SQLException {
		boardMapper.deleteCommentCnt(articleNo);
		
	}
}

