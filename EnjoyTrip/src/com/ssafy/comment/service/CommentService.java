package com.ssafy.comment.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.comment.dto.CommentDto;

public interface CommentService {
	void addComment(CommentDto commentDto) throws Exception;
	List<CommentDto> listComment(int articleNo) throws Exception;
	void deleteComment(int commentNo) throws Exception;
	void deleteComments(int articleNo) throws Exception;
}
