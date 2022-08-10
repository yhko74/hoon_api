package com.hoon.demo.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.hoon.demo.dto.MemberDTO;
import com.hoon.demo.service.MemberService;

@RestController
@RequestMapping("/sample")
public class TestController {
	@Autowired
	RedisTemplate redisTemplate;
	
	@Autowired
	private MemberService memService;

	@GetMapping("test")
	public String test() {
		return "Start";
	}
	
	@GetMapping("redistest")
	public String redisTest() {
		
		redisTemplate.opsForValue().set("testkey","Hellow World!");
		return redisTemplate.opsForValue().get("testkey").toString();
	}
	
	@GetMapping("member")
	public String getMember(@ModelAttribute MemberDTO memDTO) {
		System.out.println("custId : " + memDTO.getCustId() + ", custNm : " + memDTO.getCustNm() );
		
		MemberDTO memInfo = memService.getMember(memDTO.getCustId(), memDTO.getCustNm() );
		//Gson gson =new GsonBuilder().setPrettyPrinting().create(); //  new Gson();
		Gson gson =new Gson();
		System.out.println( gson.toJson(memInfo));
		return gson.toJson(memInfo);
	}
	
	@GetMapping("memberlist")
	public String getMemberList(@ModelAttribute MemberDTO member) {
		
		List<MemberDTO> memList = memService.getMemberList(member);
		//Gson gson =new GsonBuilder().setPrettyPrinting().create(); //  new Gson();
		Gson gson =new Gson();
		System.out.println( gson.toJson(memList));
		return gson.toJson(memList);
	}
	
	@GetMapping("memberalllist")
	public String getMemberAllList() {
		
		List<MemberDTO> memList = memService.getMemberAllList();
		//Gson gson =new GsonBuilder().setPrettyPrinting().create(); //  new Gson();
		Gson gson =new Gson();
		System.out.println( gson.toJson(memList));
		return gson.toJson(memList);
	}
	
	@GetMapping("delmember")
	public String delMember(@ModelAttribute MemberDTO delMemDTO) {
		
		Gson gson =new Gson();
		
		//MemberDTO delMemDTO = memService.getMember(memDTO.getCustId(), memDTO.getCustNm());
		//System.out.println("search member : " + gson.toJson(memDTO));
		
		memService.delMember(delMemDTO);
		List<MemberDTO> memList = memService.getMemberAllList();
		//Gson gson =new GsonBuilder().setPrettyPrinting().create(); //  new Gson();
		
		System.out.println( gson.toJson(memList));
		return gson.toJson(memList);
	}
	
	@PostMapping("addmember")
	public String addMemberList(@ModelAttribute MemberDTO member) {

		memService.addMember(member);
		List<MemberDTO> memList = memService.getMemberAllList();
		//Gson gson =new GsonBuilder().setPrettyPrinting().create(); //  new Gson();
		Gson gson =new Gson();
		System.out.println( gson.toJson(memList));
		return gson.toJson(memList);
	}
	
	@GetMapping("addtestmember")
	public String addMemberList() {
		
		Set<String> familyNames = new HashSet<String>(Arrays.asList("son 1", "daughter 2"));
		MemberDTO mem = new MemberDTO();
		mem.setCustId("tester");
		mem.setCustNm("tester");
		mem.setCelPhone("1112223333");
		mem.setSavePoint(new BigDecimal(50000));
		mem.setUsedPoint(1500);
		mem.setFamilyNames(familyNames);
		memService.addMember(mem);
		
		List<MemberDTO> memList = memService.getMemberAllList();
		//Gson gson =new GsonBuilder().setPrettyPrinting().create(); //  new Gson();
		Gson gson =new Gson();
		System.out.println( gson.toJson(memList));
		return gson.toJson(memList);
	}
}
