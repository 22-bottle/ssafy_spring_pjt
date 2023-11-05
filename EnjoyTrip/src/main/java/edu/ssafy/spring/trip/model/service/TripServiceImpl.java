package edu.ssafy.spring.trip.model.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.ssafy.spring.trip.dto.AreaInfoDto;
import edu.ssafy.spring.trip.dto.AttractionInfoDto;
import edu.ssafy.spring.trip.model.mapper.TripMapper;

@Service
public class TripServiceImpl implements TripService{

	private TripMapper tripMapper;
    private final ObjectMapper objectMapper; // Jackson's ObjectMapper

	@Autowired
	public TripServiceImpl(TripMapper tripMapper, ObjectMapper objectMapper) {
		this.tripMapper = tripMapper;
		this.objectMapper = objectMapper;
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
	public String searchArea(String scope, String regcode, String area) throws JsonProcessingException {
        // Validate 'scope' to prevent SQL injection
        if (!isValidScope(scope)) {
        		scope = "sido";
        }
        List<AreaInfoDto> areaList = tripMapper.searchArea(scope, regcode, area);
        Map<String, List<AreaInfoDto>> resultMap = new HashMap<>();
        resultMap.put("regcodes", areaList);
        return objectMapper.writeValueAsString(resultMap);
	}
	
    private boolean isValidScope(String scope) {
        // List of allowed scopes
        List<String> validScopes = Arrays.asList("sido", "gugun");
        return validScopes.contains(scope);
    }

	
}
