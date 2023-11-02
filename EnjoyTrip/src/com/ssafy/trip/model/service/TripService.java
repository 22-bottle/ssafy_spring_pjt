package com.ssafy.trip.model.service;

import java.util.List;
import com.ssafy.trip.dto.AttractionInfoDto;

public interface TripService {

	List<AttractionInfoDto> attractionList(AttractionInfoDto attractionInfoDto);

	List<AttractionInfoDto> searchByTitle(String title, int sidoCode);
	
	String searchArea(String scope, String regcode, String area);
}
