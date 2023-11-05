package edu.ssafy.spring.trip.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AttractionDescriptionDto {

	private int contentId;
	private String homepage;
	private String overview;
	private String telname;

}
