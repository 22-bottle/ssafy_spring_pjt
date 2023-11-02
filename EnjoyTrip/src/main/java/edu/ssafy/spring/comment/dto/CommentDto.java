package edu.ssafy.spring.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentDto {
	
	private int commentNo;
	private int articleNo;
	private String userId;
	private String commentContent;
	private String registerTime;
	
}
