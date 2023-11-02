package edu.ssafy.spring.board.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import edu.ssafy.spring.board.dto.BoardDto;
import edu.ssafy.spring.comment.dto.CommentDto;
import edu.ssafy.spring.util.PageNavigation;

public interface BoardService {

	void writeArticle(BoardDto boardDto) throws Exception;
	List<BoardDto> listArticle(Map<String, String> map) throws Exception;
	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	BoardDto getArticle(int articleNo) throws Exception;
	void updateHit(int articleNo) throws Exception;
	
	void modifyArticle(BoardDto boardDto) throws Exception;
	void deleteArticle(int articleNo) throws Exception;
	
	void updateCommentCnt(CommentDto commentDto) throws SQLException;
	void deleteCommentCnt(int articleNo) throws SQLException;
}
