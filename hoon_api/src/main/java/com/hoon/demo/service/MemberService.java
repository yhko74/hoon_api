package com.hoon.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hoon.demo.dao.MemberDAO;
import com.hoon.demo.dto.MemberDTO;

@Service
public class MemberService {

	@Autowired
	private MemberDAO memberDAO;
	
	
	public MemberDTO getMember(String custId, String custNm) {
		return memberDAO.getMember(custId, custNm);
	}

	public List<MemberDTO> getMemberList(MemberDTO memberDTO) {
		
		List<MemberDTO> memberList = memberDAO.getMemberList(memberDTO);
		
		return memberList;
	}
	
	public List<MemberDTO> getMemberAllList() {
		int threadMax = 3;
		List<MemberDTO> memberList = memberDAO.getMemberAllList(threadMax);
		
		return memberList;
	}
	
	public void addMember(MemberDTO member) {
		memberDAO.addMember(member);
	}
	
	public void delMember(MemberDTO member) {
		memberDAO.delMember(member);
	}
}
