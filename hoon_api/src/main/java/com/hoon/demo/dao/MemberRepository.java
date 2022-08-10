package com.hoon.demo.dao;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.hoon.demo.dto.MemberESDTO;

public interface MemberRepository extends ElasticsearchRepository<MemberESDTO, String> {
     public List<MemberESDTO> findByCustNm(String custNm);
}
