/**
 * 
 */
package com.cmc.common.utils;

import java.util.List;

/**
 * 分页类
 * @author Count Monte Cristo 
 */
public class PaginationResult<T> {

	private List<T> list;

	private Long pageCount;

	private Long totalCount;

	private Long pageNo;

	private Integer code;

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Long getPageCount() {
		return pageCount;
	}

	public void setPageCount(Long pageCount) {
		this.pageCount = pageCount;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Long getPageNo() {
		return pageNo;
	}

	public void setPageNo(Long pageNo) {
		this.pageNo = pageNo;
	}
}
