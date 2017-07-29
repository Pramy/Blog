package com.pramy.util;



public class PageUtil {

	//页数大小
	private Integer pageSize;
	//当前页数
	private Integer pageNo;
	//查询总数目
	private Integer total;
	//总数目
	private Integer totalPage;
    //数据库index
    private Integer index;

	public PageUtil() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PageUtil(String pageSize, String pageNo, Integer total) {
		super();
		if(StringUtil.isEmpty(pageSize)){
			this.pageSize = 10;
		}else{
			this.pageSize=Integer.parseInt(pageSize);
		}
		if(StringUtil.isEmpty(pageNo)){
			this.pageNo=1;
		}else{
			this.pageNo=Integer.parseInt(pageNo);
		}
		this.total = total;
		if (this.total%this.pageSize==0) {
			this.totalPage=this.total/this.pageSize;
		}else{
			this.totalPage=this.total/this.pageSize+1;
		}
		this.index=this.pageNo-1;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	


}
