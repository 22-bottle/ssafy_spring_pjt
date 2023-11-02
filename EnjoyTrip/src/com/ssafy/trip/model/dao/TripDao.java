package com.ssafy.trip.model.dao;

import java.util.List;

import com.ssafy.trip.dto.AttractionInfoDto;

public interface TripDao {
	
	List<AttractionInfoDto> attractionList(AttractionInfoDto attractionInfoDto);

	List<AttractionInfoDto> searchByTitle(String title, int sidoCode);
	
	String searchArea(String scope, String regcode, String area);
}
