package kr.or.ksmart.ksmartshop.controller;

import java.io.*;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ksmart.ksmartshop.service.GoodsService;
import kr.or.ksmart.ksmartshop.vo.Goods;
import kr.or.ksmart.ksmartshop.vo.Member;

@Controller
public class GoodsController {

	@Autowired GoodsService goodsService;
	
	
	@GetMapping("/addGoods")
	public String addGoods(Model model) {
		model.addAttribute("title", "ksmart 상품등록");
		model.addAttribute("cateList", goodsService.getCategory());
		
		return "/goods/insert/addGoods";
	}
	
	@PostMapping("/addGoods")
	public String addGoods(Goods goods, @RequestPart MultipartFile[] goodsImage, HttpSession session, Model model) throws IOException {
		System.out.println(goods +"<-- goods parameter");
		int fileSize = 10*1024*1024;
		
		for(int i=0; i<goodsImage.length; i++) {
			if(goodsImage[i].getBytes().length > fileSize){
				model.addAttribute("result", "파일 용량이 초과했습니다.");
				break;
			}else {
				String sourceFileName = goodsImage[i].getOriginalFilename();
				String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();
				
				File destinationFile;
				String destinationFileName;
				
				do {
					destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension;
	
					destinationFile = new File("C:/springboot-workspace/ksmartshop/src/main/resources/static/image/" + destinationFileName);
				} while (destinationFile.exists());
				
				goodsImage[i].transferTo(destinationFile);
			}	
		}
		
		/* goodsService.addGoods(goods, session); */
		return "/goods/insert/addGoods";
	}
	
	@GetMapping("/goodsList")
	public String goodsList(Model model, HttpServletRequest request) { 
		model.addAttribute("goodsList", goodsService.getGoodsList(request));
		return "goods/list/goodsList";
	}
	
	@GetMapping("/modifyGoods")
	public String modifyMember(@RequestParam(value="goodsCode", required = false) String memberId, Model model, HttpSession session) {
		String sessionLevel = (String) session.getAttribute("SLEVEL");
		String sessionId	= (String) session.getAttribute("SID");
		
		if(sessionLevel.equals("관리자")) {			
			model.addAttribute("title", "회원상세보기");
			//model.addAttribute("member", memberService.getMemberById(memberId));
		}else {
			model.addAttribute("title", "나의정보보기");
			//model.addAttribute("member", memberService.getMemberById(sessionId));
		}
		return "member/update/modifyMember";
	}
}
