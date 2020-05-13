package util;

import java.util.List;

/**
 * 分页工具
 * 
 * @author Daniel Duan
 *
 */
public class PageBean<T> {

	private int pageNo = 1; // 当前页
	private int pageSize = 10; // 每页个数
	private int totalCount; // 总记录数
	private int totalPages; // 总页数--只读
	private List<T> pageList; // 每页对应的集合泛型

	public PageBean() {
	}

	public int getPageNo() {
		return pageNo;
	}

	// 当前页码不能小于1不能大于总页数
	public void setPageNo(int pageNo) {
		if (pageNo < 1)
			this.pageNo = 1;
		else if (pageNo > totalPages)
			this.pageNo = totalPages;
		else
			this.pageNo = pageNo;
	}

	// 总记录数决定总页数
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		this.totalPages = (this.totalCount % this.pageSize == 0) ? this.totalCount / this.pageSize
				: this.totalCount / this.pageSize + 1;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public List<T> getPageList() {
		return pageList;
	}

	public void setPageList(List<T> pageList) {
		this.pageList = pageList;
	}

}