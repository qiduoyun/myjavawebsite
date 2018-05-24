package com.xiuxiuyu.util;

import java.io.Serializable;
import java.util.List;
/**
 * 分页对象
 * @author 桂都
 *
 * @param <M>
 */
public class PageInfo<M extends Serializable> {
    private Integer page;//当前页
    private Integer pageSize=10;//页大小
    private Integer rows;//总记录数
    private List<M> resultList;//返回结果集合
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public List<M> getResultList() {
		return resultList;
	}
	public void setResultList(List<M> resultList) {
		this.resultList = resultList;
	}

}
