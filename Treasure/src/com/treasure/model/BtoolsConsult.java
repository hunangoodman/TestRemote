package com.treasure.model;

import java.util.Date;
import java.io.Serializable;
/**
 * @class 咨询
 * @author 吴健 E-mail:1582406829@qq.com
 * @description 实体基类
 */
public class BtoolsConsult implements Serializable{
	private static final long serialVersionUID = 1L; 

	
	private Long  conId;
			
	
	private String  conHeadline;
			
	
	private String  conImg;
			
	
	private String  conContent;
			
	
	private String  conAuthor;
			
	
	private Integer  status;
			
	
	private Date  conDate;
	
	private Date addDate;
			
	
	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public Long getConId() {
		return conId;
	}
   	
   	public void setConId(Long conId) {
		this.conId=conId;
	}
   	

	public String getConHeadline() {
		return conHeadline;
	}
   	
   	public void setConHeadline(String conHeadline) {
		this.conHeadline=conHeadline;
	}
   	

	public String getConImg() {
		return conImg;
	}
   	
   	public void setConImg(String conImg) {
		this.conImg=conImg;
	}
   	

	public String getConContent() {
		return conContent;
	}
   	
   	public void setConContent(String conContent) {
		this.conContent=conContent;
	}
   	

	public String getConAuthor() {
		return conAuthor;
	}
   	
   	public void setConAuthor(String conAuthor) {
		this.conAuthor=conAuthor;
	}
   	

	public Integer getStatus() {
		return status;
	}
   	
   	public void setStatus(Integer status) {
		this.status=status;
	}
   	

	public Date getConDate() {
		return conDate;
	}
   	
   	public void setConDate(Date conDate) {
		this.conDate=conDate;
	}
   	

	@Override
	public String toString() {
		return "{BtoolsConsult:{"
		+"conId:"+conId+","
		+"conHeadline:"+conHeadline+","
		+"conImg:"+conImg+","
		+"conContent:"+conContent+","
		+"conAuthor:"+conAuthor+","
		+"status:"+status+","
		+"conDate:"+conDate+","
		+"}}";
	}


  
}