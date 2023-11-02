package com.ssafy.trip.dto;

public class TripDto {
	private String area;
	
	public TripDto(String set) {
		super();
		this.setSido(set);
	}

	public void setSido(String sido) {
		this.area = sido;
	} public String getSido() {return area;}
}
