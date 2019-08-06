package kr.or.ksmart.ksmartshop.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ksmart.ksmartshop.service.MemberService;
import kr.or.ksmart.ksmartshop.vo.Category;
import kr.or.ksmart.ksmartshop.vo.Member;

@Controller
public class MainController {

	
	@GetMapping("/")
	public String index(Model model, HttpSession session) {
		model.addAttribute("ksmartShop", "ksmartShop");
		
		return "index";
	}
	
}
