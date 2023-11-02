package com.ssafy.comment.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.comment.dao.CommentDao;
import com.ssafy.comment.dao.CommentDaoImpl;
import com.ssafy.comment.dto.CommentDto;

public class CommentServiceImpl implements CommentService {

	private static CommentService commentService = new CommentServiceImpl();
	private CommentDao commentDao;
	
	private CommentServiceImpl(){
		commentDao = CommentDaoImpl.getCommentDao();
	}
	
	public static CommentService getCommentService() {
		return commentService;
	}
	
	@Override
	public void addComment(CommentDto commentDto) throws Exception {
		commentDao.addComment(commentDto);

	}

	@Override
	public List<CommentDto> listComment(int articleNo) throws Exception {
		return commentDao.listComment(articleNo);
	}

	@Override
	public void deleteComment(int commentNo) throws Exception {
		commentDao.deleteComment(commentNo);		
	}

	@Override
	public void deleteComments(int articleNo) throws Exception {
		commentDao.deleteComments(articleNo);
	}


}
