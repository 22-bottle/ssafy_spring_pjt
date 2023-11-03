package edu.ssafy.spring.trip.model.service;

import java.util.List;

import edu.ssafy.spring.trip.dto.AttractionInfoDto;

public interface TripService {

	List<AttractionInfoDto> attractionList(AttractionInfoDto attractionInfoDto);

	List<AttractionInfoDto> searchByTitle(String title, int sidoCode);
	
	String searchArea(String scope, String regcode, String area);
	
}
