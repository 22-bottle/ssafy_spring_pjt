package com.ssafy.trip.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.trip.dto.AttractionInfoDto;
import com.ssafy.util.DBUtil;

public class TripDaoImpl implements TripDao{

	private static TripDao instance = new TripDaoImpl();
	private TripDaoImpl() {};
	public static TripDao getAttractionDao() {
		if(instance == null) {
			instance = new TripDaoImpl();
		}
		return instance;
	}

	//시도코드와 컨텐츠 타입을 기준으로 db값들을 모두  리스트에 넣음
	//0일땐 전부
	@Override
	public List<AttractionInfoDto> attractionList(AttractionInfoDto attractionInfoDto) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<AttractionInfoDto> list = new ArrayList<AttractionInfoDto>();
		
		try {
			conn = DBUtil.getInstance().getConnection();
			StringBuilder sql = new StringBuilder("select * from attraction_info");
			if(attractionInfoDto.getSidoCode()!=0) { //시도 전체 아니면
				sql.append(" where sido_code = "+attractionInfoDto.getSidoCode());
				if(attractionInfoDto.getContentTypeId()!=0) { //타입 전체 아니면					
					sql.append(" and content_type_id = "+attractionInfoDto.getContentTypeId()+"");
				}
			}
			//전체면 그대로
			else {
				if(attractionInfoDto.getContentTypeId()!=0) { //타입 전체 아니면					
					sql.append(" where content_type_id = "+attractionInfoDto.getContentTypeId()+"");
				}
			}
			pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				AttractionInfoDto attractdto = new AttractionInfoDto();
				attractdto.setContentId(rs.getInt("content_id"));
				attractdto.setContentTypeId(rs.getInt("content_type_id"));
				attractdto.setTitle(rs.getString("title"));
				attractdto.setAddr1(rs.getString("addr1"));
				attractdto.setAddr2(rs.getString("addr2"));
				attractdto.setZipcode(rs.getString("zipcode"));
				attractdto.setTel(rs.getString("tel"));
				attractdto.setFirstImage(rs.getString("first_image"));
				attractdto.setFirstImage2(rs.getString("first_image2"));
				attractdto.setReadcount(rs.getInt("readcount"));
				attractdto.setSidoCode(rs.getInt("sido_code"));
				attractdto.setGugunCode(rs.getInt("gugun_code"));
				attractdto.setLatitude(rs.getDouble("latitude"));
				attractdto.setLongitude(rs.getDouble("longitude"));
				attractdto.setMlevel(rs.getString("mlevel"));
				list.add(attractdto);
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.getInstance().close(pstmt);
			DBUtil.getInstance().close(conn);
		}
		
		return list;
	}

	//title에서 파라미터 title값을 포함하는것과 sidocode가 같은것 찾기
	@Override
	public List<AttractionInfoDto> searchByTitle(String title, int sidoCode) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<AttractionInfoDto> list = new ArrayList<AttractionInfoDto>();
		try {
			conn = DBUtil.getInstance().getConnection();
			StringBuilder sql = new StringBuilder("select * from attraction_info where title like '%"+title+"%'");
			if(sidoCode!=0) {				
				sql.append(" and sido_code = "+sidoCode);
			}
			pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				AttractionInfoDto attractdto = new AttractionInfoDto();
				attractdto.setContentId(rs.getInt("content_id"));
				attractdto.setContentTypeId(rs.getInt("content_type_id"));
				attractdto.setTitle(rs.getString("title"));
				attractdto.setAddr1(rs.getString("addr1"));
				attractdto.setAddr2(rs.getString("addr2"));
				attractdto.setZipcode(rs.getString("zipcode"));
				attractdto.setTel(rs.getString("tel"));
				attractdto.setFirstImage(rs.getString("first_image"));
				attractdto.setFirstImage2(rs.getString("first_image2"));
				attractdto.setReadcount(rs.getInt("readcount"));
				attractdto.setSidoCode(rs.getInt("sido_code"));
				attractdto.setGugunCode(rs.getInt("gugun_code"));
				attractdto.setLatitude(rs.getDouble("latitude"));
				attractdto.setLongitude(rs.getDouble("longitude"));
				attractdto.setMlevel(rs.getString("mlevel"));
				list.add(attractdto);
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.getInstance().close(pstmt);
			DBUtil.getInstance().close(conn);
		}
		return list;
	}
	@Override
	public String searchArea(String scope, String regcode, String area) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		StringBuilder set = new StringBuilder("{\"regcodes\": [");
		try {
			conn = DBUtil.getInstance().getConnection();
			StringBuilder sql = new StringBuilder("select * from ");
			sql.append(scope);
			if (scope.equals("gugun")) {
				sql.append(" where sido_code="+regcode);
			}
			pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			boolean check = rs.next();
			while(check) {
				check = false;
				
				set.append("{"+"\""+"code"+"\""+":"
						   +"\""+rs.getInt(scope+"_code")+"\","
						   +"\""+"name"+"\""+":"
						   +"\""+area+" "+rs.getString(scope+"_name")+"\"}");
				if (rs.next()) {
					check = true;
					set.append(",");
				}
			}
			set.append("]}");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.getInstance().close(pstmt);
			DBUtil.getInstance().close(conn);
		}
		System.out.println(set.toString());
		return set.toString();
	}
	 
}
