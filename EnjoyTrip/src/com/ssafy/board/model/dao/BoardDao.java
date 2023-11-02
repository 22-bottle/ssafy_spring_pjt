package com.ssafy.board.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.board.dto.BoardDto;
import com.ssafy.comment.dto.CommentDto;

public interface BoardDao {

	void writeArticle(BoardDto boardDto) throws SQLException;
	List<BoardDto> listArticle(Map<String, Object> param) throws SQLException;
	int getTotalArticleCount(Map<String, Object> param) throws SQLException;
	BoardDto getArticle(int articleNo) throws SQLException;
	void updateHit(int articleNo) throws SQLException;
	
	void modifyArticle(BoardDto boardDto) throws SQLException;
	void deleteArticle(int articleNo) throws SQLException;	
	
	void updateCommentCnt(CommentDto commentDto) throws SQLException;
	void deleteCommentCnt(int articleNo) throws SQLException;
}
