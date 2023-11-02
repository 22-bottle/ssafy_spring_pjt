package com.ssafy.trip.model.service;

import java.util.List;

import com.ssafy.trip.dto.AttractionInfoDto;
import com.ssafy.trip.model.dao.TripDaoImpl;

public class TripServiceImpl implements TripService{

	private static TripService instance = new TripServiceImpl();
	public TripServiceImpl() {};
	public static TripService getAttractionDao() {
		if(instance == null) {
			instance = new TripServiceImpl();
		}
		return instance;
	}
	
	@Override
	public List<AttractionInfoDto> attractionList(AttractionInfoDto attractionInfoDto) {
		return TripDaoImpl.getAttractionDao().attractionList(attractionInfoDto);
	}

	@Override
	public List<AttractionInfoDto> searchByTitle(String title, int sidoCode) {
		// TODO Auto-generated method stub
		return TripDaoImpl.getAttractionDao().searchByTitle(title, sidoCode);
	}
	@Override
	public String searchArea(String scope, String regcode, String area) {
		return TripDaoImpl.getAttractionDao().searchArea(scope, regcode, area);
	}


	
}
