package kr.or.ksmart.ksmartshop.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ksmart.ksmartshop.vo.Category;
import kr.or.ksmart.ksmartshop.vo.Goods;

@Mapper
public interface GoodsMapper {
	public List<Category> getCateList();
	public String getMaxGoodsCode();
	public int addGoods(Goods goods);
	public List<Goods> getGoodsList(Map<String, Object> searchMap);
	
}
