package com.hoon.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hoon.demo.dao.MemberRepository;
import com.hoon.demo.dto.MemberESDTO;

public class MemberESService {
	
	@Autowired
	private MemberRepository rep;
	
	public void saveMemberList(List<MemberESDTO> memberList) {
		rep.saveAll(memberList);
	}
	
	public Iterable<MemberESDTO> findAllEmployees() {
        return rep.findAll();
    }
 
    public List<MemberESDTO> findByCustNm(String custNm) {
        return rep.findByCustNm(custNm);
    }

}
