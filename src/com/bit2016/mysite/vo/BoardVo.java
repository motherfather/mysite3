package com.bit2016.mysite.vo;

public class BoardVo {
	private Long no;
	private String title;
	private String content;
	private String reg_date;
	private Long hit;
	private Long group_no;
	private Long order_no;
	private Long depth;
	private Long users_no;
	private String users_name;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public Long getHit() {
		return hit;
	}
	public void setHit(Long hit) {
		this.hit = hit;
	}
	public Long getGroup_no() {
		return group_no;
	}
	public void setGroup_no(Long group_no) {
		this.group_no = group_no;
	}
	public Long getOrder_no() {
		return order_no;
	}
	public void setOrder_no(Long order_no) {
		this.order_no = order_no;
	}
	public Long getDepth() {
		return depth;
	}
	public void setDepth(Long depth) {
		this.depth = depth;
	}
	public Long getUsers_no() {
		return users_no;
	}
	public void setUsers_no(Long users_no) {
		this.users_no = users_no;
	}
	public String getUsers_name() {
		return users_name;
	}
	public void setUsers_name(String users_name) {
		this.users_name = users_name;
	}
	
	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", content=" + content + ", reg_date=" + reg_date + ", hit="
				+ hit + ", group_no=" + group_no + ", order_no=" + order_no + ", depth=" + depth + ", users_no="
				+ users_no + ", users_name=" + users_name + "]";
	}
	
}
