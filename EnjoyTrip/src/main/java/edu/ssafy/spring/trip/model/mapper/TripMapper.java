package edu.ssafy.spring.trip.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import edu.ssafy.spring.trip.dto.AreaInfoDto;
import edu.ssafy.spring.trip.dto.AttractionInfoDto;

@Mapper
public interface TripMapper {
	
	List<AttractionInfoDto> attractionList(AttractionInfoDto attractionInfoDto);

	List<AttractionInfoDto> searchByTitle(String title, int sidoCode);
	
	List<AreaInfoDto> searchArea(@Param("scope") String scope, @Param("regcode") String regcode, @Param("area") String area);
	
}
