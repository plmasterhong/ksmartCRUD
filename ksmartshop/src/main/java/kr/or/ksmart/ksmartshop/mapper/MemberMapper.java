package kr.or.ksmart.ksmartshop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ksmart.ksmartshop.vo.Member;

@Mapper
public interface MemberMapper {
	public Member loginCheck(String memberId, String memberPw);
	public int addMember(Member member);
	public List<Member> getMemberList (String sk, String sv);
	public Member getMemberById (String memberId);
	public int modifyMember(Member member);
	public int deleteMember(Member member);
}
