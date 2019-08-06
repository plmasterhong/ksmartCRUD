package kr.or.ksmart.ksmartshop.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ksmart.ksmartshop.mapper.MemberMapper;
import kr.or.ksmart.ksmartshop.vo.Member;

@Service
@Transactional
public class MemberService {

	@Autowired private MemberMapper memberMapper;
	
	public String loginCheck(String memberId, String memberPw, HttpSession session) {
		
		Member member = memberMapper.loginCheck(memberId, memberPw);
		
		String urlPage;
		
		if(member != null) {			
			session.setAttribute("SID", member.getMemberId());
			session.setAttribute("SNAME", member.getMemberName());
			session.setAttribute("SLEVEL", member.getMemberLevel());
			urlPage = "index";
		}else {
			urlPage = "redirect:/login";
		}
		
		return urlPage;
	}
	
	public String addMember(Member member) {
		
		Member memberRead = memberMapper.getMemberById(member.getMemberId());
		
		int result = 0;
		
		if(memberRead == null) {
			result = memberMapper.addMember(member);
		}
		
		
		String idCheck;
		
		if(result > 0) {
			idCheck = "회원등록성공";
		}else {
			idCheck = "이미 가입된 아이디입니다.";
		}
		
		return idCheck;
	}
	
	public List<Member> getMemberList(HttpServletRequest request){

		String sk = request.getParameter("sk");
		String sv = request.getParameter("sv");
		
		return memberMapper.getMemberList(sk,sv);
	}
	
	public Member getMemberById (String memberId) {
		return memberMapper.getMemberById(memberId);
	}
	
	public String modifyMember(Member member, HttpSession session) {
		String sessionId 	= (String) session.getAttribute("SID");
		
		int result = memberMapper.modifyMember(member);
		
		if(result > 0) {
			if(sessionId.equals(member.getMemberId())) {
				session.setAttribute("SID", member.getMemberId());
				session.setAttribute("SLEVEL", member.getMemberLevel());
				session.setAttribute("SNAME", member.getMemberName());
			}
			return "수정성공";
		}else {
			return "수정실패";
		}
	}
	
	public String deleteMember(Member member) {
		int result = memberMapper.deleteMember(member);
		
		if(result > 0) {
			return "삭제성공";
		}else {
			return "삭제실패";
		}
	}
}
