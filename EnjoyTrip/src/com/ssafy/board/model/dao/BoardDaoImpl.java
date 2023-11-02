package com.ssafy.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ssafy.board.dto.BoardDto;
import com.ssafy.comment.dto.CommentDto;
import com.ssafy.util.DBUtil;

public class BoardDaoImpl implements BoardDao {
	
	private static BoardDao boardDao;
	private DBUtil dbUtil;
	
	private BoardDaoImpl() {
		dbUtil = DBUtil.getInstance();
	}

	public static BoardDao getBoardDao() {
		if(boardDao == null)
			boardDao = new BoardDaoImpl();
		return boardDao;
	}

	@Override
	public void writeArticle(BoardDto boardDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("insert into board (user_id, subject, content) values (?, ?, ?)");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, boardDto.getUserId());
			pstmt.setString(2, boardDto.getSubject());
			pstmt.setString(3, boardDto.getContent());
			
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public List<BoardDto> listArticle(Map<String, Object> param) throws SQLException {
		List<BoardDto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select article_no, user_id, subject, content, hit, comment_cnt, register_time from board \n");
			
			String key = (String) param.get("key"); // 검색조건
			String word = (String) param.get("word"); // 검색어
			
			// 검색하는 경우 -> where절 (검색어)
			if(!key.isEmpty() && !word.isEmpty()) { 
				if("subject".equals(key)) {  // 제목으로 검색하는 경우 -> 포함만 되면 됨
					sql.append("where subject like concat('%', ?, '%') \n");
				} else { // 글번호, 작성자로 검색하는 경우 -> 완전히 같아야 함
					sql.append("where ").append(key).append(" = ? \n");
				}
			}
			
			sql.append("order by article_no desc \n"); // article_no 기준으로 내림차순 정렬
			
			// listsize(10개)만큼만 가져오기 위해서 -> limit절 (시작 위치, 반환 개수)
			sql.append("limit ?, ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			int idx = 0;
			// 검색하는 경우 -> where절 검색어 추가
			if(!key.isEmpty() && !word.isEmpty()) 
				pstmt.setString(++idx, word);
			
			// 검색 하는 경우 start, listsize 지정
			pstmt.setInt(++idx, (Integer) param.get("start"));
			pstmt.setInt(++idx, (Integer) param.get("listsize"));
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDto boardDto = new BoardDto();
				boardDto.setArticleNo(rs.getInt("article_no"));
				boardDto.setUserId(rs.getString("user_id"));
				boardDto.setSubject(rs.getString("subject"));
				boardDto.setContent(rs.getString("content"));
				boardDto.setHit(rs.getInt("hit"));
				boardDto.setCommentCnt(rs.getInt("comment_cnt"));
				boardDto.setRegisterTime(rs.getString("register_time"));
				list.add(boardDto);
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return list;
	}
	
	@Override
	public int getTotalArticleCount(Map<String, Object> param) throws SQLException {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select count(article_no) from board \n");
			String key = (String) param.get("key");
			String word = (String) param.get("word");
			
			// 검색어 있는 경우 검색어에 해당하는 글의 개수를 반환하는 sql문
			if(!key.isEmpty() && !word.isEmpty()) {
				if("subject".equals(key)) {
					sql.append("where subject like concat('%', ?, '%') \n");
				} else {
					sql.append("where ").append(key).append(" = ? \n");
				}
			}
			pstmt = conn.prepareStatement(sql.toString());
			if(!key.isEmpty() && !word.isEmpty())
				pstmt.setString(1, word);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return cnt;
	}

	@Override
	public BoardDto getArticle(int articleNo) throws SQLException {
		BoardDto boardDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select article_no, user_id, subject, content, hit, register_time from board where article_no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, articleNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				boardDto = new BoardDto();
				boardDto.setArticleNo(rs.getInt("article_no"));
				boardDto.setUserId(rs.getString("user_id"));
				boardDto.setSubject(rs.getString("subject"));
				boardDto.setContent(rs.getString("content"));
				boardDto.setHit(rs.getInt("hit"));
				boardDto.setRegisterTime(rs.getString("register_time"));
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return boardDto;
	}

	@Override
	public void updateHit(int articleNo) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("update board set hit = hit + 1 where article_no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, articleNo);
			
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}	
	}

	@Override
	public void modifyArticle(BoardDto boardDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("update board set subject = ?, content = ? where article_no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, boardDto.getSubject());
			pstmt.setString(2, boardDto.getContent());
			pstmt.setInt(3, boardDto.getArticleNo());
			
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public void deleteArticle(int articleNo) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("delete from board where article_no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, articleNo);
			
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}
	
	@Override
	public void updateCommentCnt(CommentDto commentDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("update board set comment_cnt = comment_cnt + 1 where article_no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, commentDto.getArticleNo());
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
		
	}

	@Override
	public void deleteCommentCnt(int articleNo) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("update board set comment_cnt = comment_cnt - 1 where article_no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, articleNo);
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
		
	}

}
