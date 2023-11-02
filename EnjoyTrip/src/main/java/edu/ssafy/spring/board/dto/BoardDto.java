package edu.ssafy.spring.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardDto {

	private int articleno;
	private String userId;
	private String subject;
	private String content;
	private int hit;
	private int commentCnt;

}
