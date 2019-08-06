package kr.or.ksmart.ksmartshop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ksmart.ksmartshop.mapper.GoodsMapper;
import kr.or.ksmart.ksmartshop.vo.Category;
import kr.or.ksmart.ksmartshop.vo.Goods;

@Service
@Transactional
public class GoodsService {
	
	@Autowired private GoodsMapper goodsMapper;
		
	public List<Category> getCategory(){
		return goodsMapper.getCateList();
	}
	
	public void addGoods(Goods goods, HttpSession session){
		int maxCount=0;
		String goodsCode = "goods_";
		String memberId = (String) session.getAttribute("SID");
		
		String count = goodsMapper.getMaxGoodsCode();	 
		
		if(count == null) {
			count = "0";
		}
		
		maxCount += Integer.parseInt(count) + 1;
		
		goodsCode = goodsCode + maxCount;
		
		goods.setGoodsCode(goodsCode);
		goods.setMemberId(memberId);
		
		int result = goodsMapper.addGoods(goods);
		
		if(result>0) {
			System.out.println("상품등록성공");
		}else {
			System.out.println("상품등록실패");
		}
	}
	
	public List<Goods> getGoodsList(HttpServletRequest request){
		HttpSession session  = request.getSession();
		String sessionId 	= (String) session.getAttribute("SID");
		String sessionLevel = (String) session.getAttribute("SLEVEL");
		
		String sk 		= request.getParameter("sk");
		String sv 		= request.getParameter("sv");
		String firstDay = request.getParameter("firstDay");
		String lastDay 	= request.getParameter("lastDay");
		
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("sk"			, sk);
		searchMap.put("sv"			, sv);
		searchMap.put("firstDay"	, firstDay);
		searchMap.put("lastDay"		, lastDay);
		searchMap.put("memberId"	, sessionId);
		searchMap.put("memberLevel"	, sessionLevel);
		
		return goodsMapper.getGoodsList(searchMap);
	}
}
