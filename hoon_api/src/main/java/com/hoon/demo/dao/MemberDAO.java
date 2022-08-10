package com.hoon.demo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.hoon.demo.dto.MemberDTO;

@Repository
public class MemberDAO {

	@Autowired
	private DynamoDBMapper dynamoDBMapper;
	
	public MemberDTO getMember(String custId, String custNm) {
		
		return dynamoDBMapper.load(MemberDTO.class, custId, custNm);
	}
	
	public List<MemberDTO> getMemberList(MemberDTO memberDTO) {
		
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":val1", new AttributeValue().withS(memberDTO.getCustId()));
        eav.put(":val2", new AttributeValue().withS(memberDTO.getCustNm()));

        DynamoDBQueryExpression<MemberDTO> queryExpression = new DynamoDBQueryExpression<MemberDTO>()
            .withKeyConditionExpression("CUST_Id = :val1 and CUST_NM = :val2").withExpressionAttributeValues(eav);
        
		List<MemberDTO> memberList = dynamoDBMapper.query(MemberDTO.class, queryExpression);
		
		return memberList;
	}
	
	public List<MemberDTO> getMemberAllList(int threadmax) {
		
		List<MemberDTO> memberList = dynamoDBMapper.parallelScan(MemberDTO.class, new DynamoDBScanExpression(), threadmax );
		
		return memberList;
	}
	
	public void addMember(MemberDTO member) {
		dynamoDBMapper.save(member);
	}
	
	public void delMember(MemberDTO member) {
		dynamoDBMapper.delete(member);
	}
	
	public void batchSaveMember(List<MemberDTO> memberList) {
		dynamoDBMapper.batchSave(memberList);
	}
	
	public void batchDeleteMember(List<MemberDTO> memberList) {
		dynamoDBMapper.batchDelete(memberList);
	}

	public void batchWriteMember(List<Object> saveList, List<Object > deleteList) {
		dynamoDBMapper.batchDelete(saveList, deleteList);
	}
}
