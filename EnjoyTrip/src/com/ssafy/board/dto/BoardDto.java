package com.ssafy.board.dto;

public class BoardDto {
	
	private int articleno;
	private String userId;
	private String subject;
	private String content;
	private int hit;
	private int commentCnt;
	public int getCommentCnt() {
		return commentCnt;
	}

	public void setCommentCnt(int commentCnt) {
		this.commentCnt = commentCnt;
	}

	private String registerTime;

	public int getArticleNo() {
		return articleno;
	}

	public void setArticleNo(int articleno) {
		this.articleno = articleno;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	@Override
	public String toString() {
		return "BoardDto [articleNo=" + articleno + ", userId=" + userId + ", subject=" + subject + ", content="
				+ content + ", hit=" + hit + ", registerTime=" + registerTime + "]";
	}

}
