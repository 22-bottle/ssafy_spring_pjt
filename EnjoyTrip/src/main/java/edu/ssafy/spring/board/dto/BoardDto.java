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

	private int article_no;
	private String user_id;
	private String subject;
	private String content;
	private int hit;
	private int comment_cnt;
	private String register_time;

}
