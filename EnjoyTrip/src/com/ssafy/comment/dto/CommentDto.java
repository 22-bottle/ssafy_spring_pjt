package com.ssafy.comment.dto;

public class CommentDto {
	
	private int commentNo;
	private int articleNo;
	private String userId;
	private String commentContent;
	private String registerTime;
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public int getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	
	@Override
	public String toString() {
		return "CommentDto [commentNo=" + commentNo + ", articleNo=" + articleNo + ", userId=" + userId
				+ ", commentContent=" + commentContent + ", registerTime=" + registerTime + "]";
	}


}
