package kr.or.ksmart.ksmartshop.vo;

public class Goods extends Category{
	public String goodsCode;
	public String memberId;
	public String goodsName;
	public String goodsCate;
	public String goodsPrice;
	public String goodsColor;
	public String goodsSize;
	public String goodsDate;
	public String goodsDesc;
	public String goodsImagePath;
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsCate() {
		return goodsCate;
	}
	public void setGoodsCate(String goodsCate) {
		this.goodsCate = goodsCate;
	}
	public String getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public String getGoodsColor() {
		return goodsColor;
	}
	public void setGoodsColor(String goodsColor) {
		this.goodsColor = goodsColor;
	}
	public String getGoodsSize() {
		return goodsSize;
	}
	public void setGoodsSize(String goodsSize) {
		this.goodsSize = goodsSize;
	}
	public String getGoodsDate() {
		return goodsDate;
	}
	public void setGoodsDate(String goodsDate) {
		this.goodsDate = goodsDate;
	}
	public String getGoodsDesc() {
		return goodsDesc;
	}
	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}
	
	public String getGoodsImagePath() {
		return goodsImagePath;
	}
	public void setGoodsImagePath(String goodsImagePath) {
		this.goodsImagePath = goodsImagePath;
	}
	@Override
	public String toString() {
		return "Goods [goodsCode=" + goodsCode + ", memberId=" + memberId + ", goodsName=" + goodsName + ", goodsCate="
				+ goodsCate + ", goodsPrice=" + goodsPrice + ", goodsColor=" + goodsColor + ", goodsSize=" + goodsSize
				+ ", goodsDate=" + goodsDate + ", goodsDesc=" + goodsDesc + ", goodsImagePath=" + goodsImagePath + "]";
	}
	
}
