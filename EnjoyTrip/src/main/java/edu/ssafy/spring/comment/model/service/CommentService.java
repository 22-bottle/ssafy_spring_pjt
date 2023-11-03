package edu.ssafy.spring.comment.model.service;

import java.util.List;

import edu.ssafy.spring.comment.dto.CommentDto;

public interface CommentService {
	
	void addComment(CommentDto commentDto) throws Exception;
	List<CommentDto> listComment(int articleNo) throws Exception;
	void deleteComment(int commentNo) throws Exception;
	void deleteComments(int articleNo) throws Exception;
	
}
