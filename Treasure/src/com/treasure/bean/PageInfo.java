package com.treasure.bean;

import com.treasure.utils.ToLowerCase;

/**
 * PageInfo.java
 * House-Intro-Common
 * zhujiagui email:zhujiagui@zkingsoft.com
 * 2015年10月25日 下午3:33:28
 * @description 分页信息类
 */
public class PageInfo {
	private int pageIndex = 0;// 当前索引值
	private int pageSize = 10;// 获取当前页现在最大记录数
	private String oldSortField; // 排序列
	private String sortField; // 排序列
	private String sortOrder; // 排序方向
	private int totalRecord; // 总记录数
	private int totalPage;// 总页数
	private int currentPage = 1;// 当前页面
	public PageInfo(){
		
	}
	
	public PageInfo(int pageIndex, int pageSize, String sortField, String sortOrder) {
		super();
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex() {
		this.pageIndex = (this.currentPage-1) * this.pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		this.setTotalPage();
		this.setPageIndex();
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = ToLowerCase.toLowerCase(sortField);
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		this.setTotalPage();
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage() {
		this.totalPage = calculateTotalPage(this.pageSize, this.totalRecord);
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		this.setPageIndex();
	}

	public int calculateTotalPage(int pageSize, int totalRecord) {
		int page = 0;
		if (pageSize >= 0 && totalRecord >= 0) {
			if (totalRecord > 0) {
				if (totalRecord % pageSize == 0) {
					page = totalRecord / pageSize;
				} else {
					page = totalRecord / pageSize + 1;
				}
			}
		}
		return page;
	}

	public String getOldSortField() {
		return oldSortField;
	}

	public void setOldSortField(String oldSortField) {
		this.oldSortField = oldSortField;
	}

	@Override
	public String toString() {
		return "PageInfo [pageIndex=" + pageIndex + ", pageSize=" + pageSize
				+ ", oldSortField=" + oldSortField + ", sortField=" + sortField
				+ ", sortOrder=" + sortOrder + ", totalRecord=" + totalRecord
				+ ", totalPage=" + totalPage + ", currentPage=" + currentPage
				+ "]";
	}
	
	
	
}
