package edu.ssafy.spring.trip.controller;

import edu.ssafy.spring.trip.dto.AreaDto;
import edu.ssafy.spring.trip.dto.AttractionInfoDto;
import edu.ssafy.spring.trip.model.service.TripService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/trip")
@Slf4j
public class TripController {

    private final TripService service;

    @Autowired
    public TripController(TripService service) {
        this.service = service;
    }
    
    @GetMapping("/trip")
    public String trip() {
    	return "trip/trip";
    }

    @GetMapping("/option")
    @RequestBody
    public ResponseEntity<?> option(@RequestParam(name = "action", required = false) String action,
                                  @RequestParam(name = "regcode_pattern", required = false) String regcodePattern,
                                  @RequestParam(name = "regcode", required = false) String regcode,
                                  @RequestParam(name = "area", required = false) String area) throws JsonProcessingException {
        if ("detail".equals(regcodePattern)) {
            String[] search = regcode.split(" ");
            AttractionInfoDto attractionInfoDto = new AttractionInfoDto();
            attractionInfoDto.setSidoCode(Integer.parseInt(search[1]));
            attractionInfoDto.setContentTypeId(Integer.parseInt(search[search.length - 1]));
            List<AttractionInfoDto> list = service.attractionList(attractionInfoDto);
            return ResponseEntity.ok(list); // JSON으로 변환하여 반환합니다.
        } else {
            String set = service.searchArea(regcodePattern, regcode, area);
            AreaDto trip = new AreaDto();
            trip.setArea(set);
            System.out.println(ResponseEntity.ok(trip));
            return ResponseEntity.ok(trip); // JSON으로 변환하여 반환합니다.
        }
    }
}
