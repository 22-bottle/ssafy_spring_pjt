package edu.ssafy.spring.comment.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.ssafy.spring.comment.dto.CommentDto;

@Mapper
public interface CommentMapper {
	
	void addComment(CommentDto commentDto) throws SQLException;
	List<CommentDto> listComment(int articleNo) throws Exception;
	void deleteComment(int commentNo) throws Exception;
	void deleteComments(int articleNo) throws Exception;
	
}
