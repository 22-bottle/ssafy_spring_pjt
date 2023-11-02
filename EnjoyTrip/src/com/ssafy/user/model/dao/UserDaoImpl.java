package com.ssafy.user.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssafy.board.dto.BoardDto;
import com.ssafy.user.dto.UserDto;
import com.ssafy.util.DBUtil;
import com.ssafy.util.Encrypt;

public class UserDaoImpl implements UserDao{
	
	private static UserDao userDao;
	private DBUtil dbUtil;
	private Encrypt encrypt;
	
	public UserDaoImpl() {
		dbUtil = DBUtil.getInstance();
		encrypt = Encrypt.getInstance();
	}
	
	public static UserDao getUserDao() {
		if(userDao == null) {
			userDao = new UserDaoImpl();
		}
		return userDao;
	}
	
	@Override
	public boolean loginUser(UserDto userDto) {
		UserDto u = new UserDto();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select user_password, salt from user where user_id = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userDto.getUserId());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				u = new UserDto();
				u.setUserPassword(rs.getString("user_password"));
				u.setSalt(rs.getString("salt"));
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}

//		System.out.println("-----------------login");
//		System.out.println("pwd " + userDto.getUserPassword());
//		System.out.println("pwd a" + encrypt.getEncrypt(userDto.getUserPassword(), u.getSalt()));
//		System.out.println("pwd a db " + u.getUserPassword());
		
		// 암호화로 확인
		if(u.getUserPassword().equals(encrypt.getEncrypt(userDto.getUserPassword(), u.getSalt()))) {
			return true;
		} 
		
		return false;
	}

	@Override
	public int registUser(UserDto userDto) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into user (user_id, user_name, user_password, email_id, email_domain, salt) \n");
			sql.append("values (?, ?, ?, ?, ?, ?)");
			pstmt = conn.prepareStatement(sql.toString());
			
			// 암호화
			String salt = encrypt.getSalt();
			
			pstmt.setString(1, userDto.getUserId());
			pstmt.setString(2, userDto.getUserName());
			pstmt.setString(3, encrypt.getEncrypt(userDto.getUserPassword(), salt));
			pstmt.setString(4, userDto.getEmailId());
			pstmt.setString(5, userDto.getEmailDomain());
			pstmt.setString(6, salt);
			cnt = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, conn);
		}
		
		return cnt;
	}

	@Override
	public boolean findUser(UserDto userDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from user where user_id = ? and user_name = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userDto.getUserId());
			pstmt.setString(2, userDto.getUserName());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		
		return false;
	}

	@Override
	public UserDto getUser(String userId) {
		UserDto userDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from user where user_id = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				userDto = new UserDto();
				userDto.setUserId(rs.getString("user_id"));
				userDto.setUserName(rs.getString("user_name"));
				userDto.setUserPassword(rs.getString("user_password"));
				userDto.setEmailId(rs.getString("email_id"));
				userDto.setEmailDomain(rs.getString("email_domain"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return userDto;
	}
	
	@Override
	public String getSalt(String userId) {
		String salt = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select salt from user where user_id = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				salt = rs.getString("salt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return salt;
	}

	@Override
	public void updateUser(UserDto userDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("update user set user_name = ?,user_password = ?, email_id = ?, email_domain = ? \n");
			sql.append("where user_id = ? \n");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userDto.getUserName());
//			pstmt.setString(2, userDto.getUserPassword());
			pstmt.setString(2, encrypt.getEncrypt(userDto.getUserPassword(), userDto.getSalt()));
			
//			System.out.println("-----------------update");
//			System.out.println("pw " + userDto.getUserPassword());
//			System.out.println("pw a " + encrypt.getEncrypt(userDto.getUserPassword(), userDto.getSalt()));
//			System.out.println("salt " + userDto.getSalt());
			
			pstmt.setString(3, userDto.getEmailId());
			pstmt.setString(4, userDto.getEmailDomain());
			pstmt.setString(5, userDto.getUserId());
			
			pstmt.executeUpdate();	
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public int deleteUser(String userId) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("delete from user where user_id = ? \n");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userId);
			
			cnt = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, conn);
		}
		
		return cnt;
	}
	
	
}
