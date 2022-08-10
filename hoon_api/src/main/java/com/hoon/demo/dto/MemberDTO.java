package com.hoon.demo.dto;

import java.math.BigDecimal;
import java.util.Set;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBVersionAttribute;

import lombok.Data;

@DynamoDBTable(tableName="T_MEMBER")
@Data
public class MemberDTO {

	@DynamoDBHashKey(attributeName = "CUST_ID")
	private String custId;
	@DynamoDBRangeKey(attributeName = "CUST_NM") 
	private String custNm;
	@DynamoDBAttribute(attributeName = "ADDRESS")
	private String address;
	@DynamoDBAttribute(attributeName = "CEL_PHONE")
	private String celPhone;
	@DynamoDBAttribute(attributeName = "SAVE_POINT")
	private BigDecimal savePoint;
	@DynamoDBAttribute(attributeName = "USED_POINT")
	private Integer usedPoint;
	@DynamoDBAttribute(attributeName="FAMILY_NAMES")
	private Set<String> familyNames;
	@DynamoDBVersionAttribute
	private Long version;
}
