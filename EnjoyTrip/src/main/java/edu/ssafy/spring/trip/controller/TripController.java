package edu.ssafy.spring.trip.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.ssafy.spring.trip.dto.AreaDto;
import edu.ssafy.spring.trip.dto.AttractionInfoDto;
import edu.ssafy.spring.trip.model.service.TripService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/trip")
@Slf4j
public class TripController extends HttpServlet {
	
	private TripService service;
	
	@Autowired
	public TripController(TripService service) {
		super();
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<?> trip(@RequestParam(name = "action") String action,
            		 @RequestParam(name = "regcode_pattern", required = false) String regcodePattern,
        			 @RequestParam(name = "regcode", required = false) String regcode,
    				 @RequestParam(name =  "area", required = false) String area) {
        if ("trip".equals(action)) {
        	return ResponseEntity.ok("Redirect to /trip/trip.jsp");
        } else if ("detail".equals(regcodePattern)) {
            String[] search = regcode.split(" ");
            AttractionInfoDto attractionInfoDto = new AttractionInfoDto();
            attractionInfoDto.setSidoCode(Integer.parseInt(search[1]));
            attractionInfoDto.setContentTypeId(Integer.parseInt(search[search.length - 1]));
            List<AttractionInfoDto> list = service.attractionList(attractionInfoDto);
            return ResponseEntity.ok(list);
        } else {
            AreaDto trip = new AreaDto();
            String set = service.searchArea(regcodePattern, regcode, area);
            trip.setArea(set);
            return ResponseEntity.ok(trip.getArea());
        }
    }
	
	
}
