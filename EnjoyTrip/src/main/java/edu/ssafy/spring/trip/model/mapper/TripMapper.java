package edu.ssafy.spring.trip.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.ssafy.spring.trip.dto.AttractionInfoDto;

@Mapper
public interface TripMapper {
	
	List<AttractionInfoDto> attractionList(AttractionInfoDto attractionInfoDto);

	List<AttractionInfoDto> searchByTitle(String title, int sidoCode);
	
	String searchArea(String scope, String regcode, String area);
	
}
