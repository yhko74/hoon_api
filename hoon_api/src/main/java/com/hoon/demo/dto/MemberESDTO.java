package com.hoon.demo.dto;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(indexName = "hoon_db", type = "member")
public class MemberESDTO {

	@Id
	private String id;
	private String custId;
	private String custNm;
	private String address;
	private String celPhone;
	private Integer savePoint;
	private Integer usedPoint;
	private Set<String> familyNames;
	private Long version;
}
