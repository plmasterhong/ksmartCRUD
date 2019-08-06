package kr.or.ksmart.ksmartshop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
public class MemberController {

	@Autowired MemberService memberService;
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("title", "ksmart 로그인");
		return "/login/login";
	}
	
	@PostMapping("/login")
	public String loginProcess( @RequestParam(value="memberId") String memberId
							  , @RequestParam(value="memberPw") String memberPw
							  , HttpSession session) {
		
		String urlPage = memberService.loginCheck(memberId, memberPw, session);
	
		return urlPage;
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	
	@GetMapping("/regMember")
	public String addMember(Model model) {
		model.addAttribute("title", "ksmart 회원가입");
		return "member/insert/addMember";
	}
	
	@PostMapping("/regMember")
	public String addMember(Member member, Model model) {
		String result = memberService.addMember(member);
		
		System.out.println(result);
		String urlPage;
		
		if(result.equals("회원등록성공")) {
			urlPage = "/login/login";
		}else {
			urlPage = "member/insert/addMember";
		}
		model.addAttribute("result", result);
		return urlPage;
	}
	
	@GetMapping("/memberList")
	public String getMemberList(Model model, HttpServletRequest request) {
		model.addAttribute("title", "회원전체목록");
		model.addAttribute("memberList", memberService.getMemberList(request));
		return "member/list/memberList";
	}
	
	@GetMapping("/modifyMember")
	public String modifyMember(@RequestParam(value="memberId", required = false) String memberId, Model model, HttpSession session) {
		String sessionLevel = (String) session.getAttribute("SLEVEL");
		String sessionId	= (String) session.getAttribute("SID");
		
		if(sessionLevel.equals("관리자")) {			
			model.addAttribute("title", "회원상세보기");
			model.addAttribute("member", memberService.getMemberById(memberId));
		}else {
			model.addAttribute("title", "나의정보보기");
			model.addAttribute("member", memberService.getMemberById(sessionId));
		}
		return "member/update/modifyMember";
	}
	
	@PostMapping("/modifyMember")
	public String modifyMemberProcess(Member member, HttpSession session) {
		
		memberService.modifyMember(member, session);
		
		String urlPage;
		String sessionLevel = (String) session.getAttribute("SLEVEL");
		if(sessionLevel.equals("관리자")) {
			urlPage = "redirect:/memberList";
		}else {
			urlPage = "redirect:/";
		}
		
		return urlPage;
	}
	
	@GetMapping("/deleteMember")
	public String deleteMember(@RequestParam(value="memberId") String memberId, Model model) {
		model.addAttribute("title", "회원삭제");
		model.addAttribute("memberId", memberId);
		return "member/delete/deleteMember";
	}
	
	@PostMapping("/deleteMember")
	public String deleteMemberProcess(Member member) {
		memberService.deleteMember(member); 		
		return "redirect:/memberList";
	}
}
