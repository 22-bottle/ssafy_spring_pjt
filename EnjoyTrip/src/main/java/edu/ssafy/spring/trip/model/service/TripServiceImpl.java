package edu.ssafy.spring.trip.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ssafy.spring.trip.dto.AttractionInfoDto;
import edu.ssafy.spring.trip.model.mapper.TripMapper;

@Service
public class TripServiceImpl implements TripService{

	private TripMapper tripMapper;
	
	@Autowired
	public TripServiceImpl(TripMapper tripMapper) {
		this.tripMapper = tripMapper;
	}
	
	@Override
	public List<AttractionInfoDto> attractionList(AttractionInfoDto attractionInfoDto) {
		return tripMapper.attractionList(attractionInfoDto);
	}

	@Override
	public List<AttractionInfoDto> searchByTitle(String title, int sidoCode) {
		// TODO Auto-generated method stub
		return tripMapper.searchByTitle(title, sidoCode);
	}
	@Override
	public String searchArea(String scope, String regcode, String area) {
		return tripMapper.searchArea(scope, regcode, area);
	}


	
}
