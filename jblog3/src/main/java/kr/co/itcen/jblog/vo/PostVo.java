package kr.co.itcen.jblog.vo;

public class PostVo {
	private Long no;
	private String title;
	private String contents;
	private String updateDate;
	private Long categoryNo;
	
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
	
	public String getContents() {
		return contents;
	}
	
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public String getUpdateDate() {
		return updateDate;
	}
	
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	
	public Long getCategoryNo() {
		return categoryNo;
	}
	
	public void setCategoryNo(Long categoryNo) {
		this.categoryNo = categoryNo;
	}

	@Override
	public String toString() {
		return "PostVo [no=" + no + ", title=" + title + ", contents=" + contents + ", updateDate=" + updateDate
				+ ", categoryNo=" + categoryNo + "]";
	}
}
