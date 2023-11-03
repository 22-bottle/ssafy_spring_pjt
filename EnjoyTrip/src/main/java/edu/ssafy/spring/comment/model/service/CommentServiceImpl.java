package edu.ssafy.spring.comment.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ssafy.spring.comment.dto.CommentDto;
import edu.ssafy.spring.comment.model.mapper.CommentMapper;

@Service
public class CommentServiceImpl implements CommentService {

	private CommentMapper commentMapper;
	
	@Autowired
	public CommentServiceImpl(CommentMapper commentMapper){
		this.commentMapper = commentMapper;
	}
	
	@Override
	public void addComment(CommentDto commentDto) throws Exception {
		commentMapper.addComment(commentDto);
	}

	@Override
	public List<CommentDto> listComment(int articleNo) throws Exception {
		return commentMapper.listComment(articleNo);
	}

	@Override
	public void deleteComment(int commentNo) throws Exception {
		commentMapper.deleteComment(commentNo);
	}

	@Override
	public void deleteComments(int articleNo) throws Exception {
		commentMapper.deleteComments(articleNo);
	}


}
