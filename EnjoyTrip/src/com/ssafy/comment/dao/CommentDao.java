package com.ssafy.comment.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.comment.dto.CommentDto;

public interface CommentDao {
	void addComment(CommentDto commentDto) throws SQLException;
	List<CommentDto> listComment(int articleNo) throws Exception;
	void deleteComment(int commentNo) throws Exception;
	void deleteComments(int articleNo) throws Exception;
}
