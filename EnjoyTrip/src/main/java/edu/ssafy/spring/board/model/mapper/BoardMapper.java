package edu.ssafy.spring.board.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import edu.ssafy.spring.board.dto.BoardDto;
import edu.ssafy.spring.comment.dto.CommentDto;

@Mapper
public interface BoardMapper {

	void writeArticle(BoardDto boardDto) throws SQLException;

	void registerFile(BoardDto boardDto) throws Exception;

	List<BoardDto> listArticle(Map<String, Object> param) throws SQLException;

	int getTotalArticleCount(Map<String, Object> param) throws SQLException;

	BoardDto getArticle(int articleNo) throws SQLException;

	void updateHit(int articleNo) throws SQLException;

	void modifyArticle(BoardDto boardDto) throws SQLException;

	void deleteArticle(int articleNo) throws SQLException;

	void updateCommentCnt(CommentDto commentDto) throws SQLException;
	
	void deleteCommentCnt(int articleNo) throws SQLException;
	
}