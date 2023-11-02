package com.ssafy.comment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.board.dto.BoardDto;
import com.ssafy.comment.dto.CommentDto;
import com.ssafy.util.DBUtil;

public class CommentDaoImpl implements CommentDao {
	
	private static CommentDao commentDao;
	private DBUtil dbUtil;
	
	private CommentDaoImpl() {
		dbUtil = DBUtil.getInstance();
	}
	
	public static CommentDao getCommentDao() {
		if(commentDao == null)
			commentDao = new CommentDaoImpl();
		return commentDao;
	}

	@Override
	public void addComment(CommentDto commentDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("insert into comment (article_no, user_id, comment_content) values (?, ?, ?)");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, commentDto.getArticleNo());
			pstmt.setString(2, commentDto.getUserId());
			pstmt.setString(3, commentDto.getCommentContent());
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}
	
	@Override
	public List<CommentDto> listComment(int articleNo) throws Exception {
		List<CommentDto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select comment_no, article_no, user_id, comment_content, register_time from comment where article_no = ? \n");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, articleNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CommentDto commentDto = new CommentDto();
				commentDto.setCommentNo(rs.getInt("comment_no"));
				commentDto.setArticleNo(rs.getInt("article_no"));
				commentDto.setUserId(rs.getString("user_id"));
				commentDto.setCommentContent(rs.getString("comment_content"));
				commentDto.setRegisterTime(rs.getString("register_time"));
				commentDto.toString();
				list.add(commentDto);
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	@Override
	public void deleteComment(int commentNo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("delete from comment where comment_no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, commentNo);
			
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public void deleteComments(int articleNo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("delete from comment where article_no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, articleNo);
			
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}



}
