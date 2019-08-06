package kr.or.ksmart.ksmartshop.vo;



public class Category {
	private String cateCode;
	private String cateName;
	private String cateRegDate;
	
	public String getCateCode() {
		return cateCode;
	}
	public void setCateCode(String cateCode) {
		this.cateCode = cateCode;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	public String getCateRegDate() {
		return cateRegDate;
	}
	public void setCateRegDate(String cateRegDate) {
		this.cateRegDate = cateRegDate;
	}
	@Override
	public String toString() {
		return "Categori [cateCode=" + cateCode + ", cateName=" + cateName + ", cateRegDate=" + cateRegDate + "]";
	}	
}
