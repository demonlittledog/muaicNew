package cn.music.entity;

/*分页工具类：计算总页数:总数量/每页显示几条*/
public class Page {
	//当前页码
	private int currentPageNo = 1;
	//总数量
	private int totalCount = 0;
	//页面容量
	private int pageSize = 0;
	//总页数
	private int totalPageCount = 1;
	
	
	public int getCurrentPageNo() {
		return currentPageNo;
	}
	public void setCurrentPageNo(int currentPageNo) {
		if(currentPageNo>0)
			this.currentPageNo = currentPageNo;
	}
	public int getTotalCount() {
		return totalCount;
	}
	//计算总页数
	public void setTotalPageCountByRS(){
		if(this.totalCount%this.pageSize==0){
			this.totalPageCount = this.totalCount/this.pageSize;
		}else if(this.totalCount%this.pageSize!=0){
			this.totalPageCount = this.totalCount/this.pageSize+1;
		}else{
			this.totalPageCount = 0;
		}
	}
	
	
	//总数量：设置总数量时，同步做一件事：计算出来总页数
	public void setTotalCount(int totalCount) {
		if(totalCount>=0){
			this.totalCount = totalCount;
			//设置总页数
			this.setTotalPageCountByRS();
		}
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		if(pageSize>0)
			this.pageSize = pageSize;
	}
	public int getTotalPageCount() {
		return totalPageCount;
	}
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	
	//流程：我们去数据库查询新闻总数量--》设置总新闻数量，计算总页数  setTotalCount(1000)--》getTotalPageCount()
}
