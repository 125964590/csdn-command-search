package com.huatu.ic.search.repository;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;

/**
 * @author jbzm
 * @date 201812:53 AM
 **/
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@Document(indexName = "cool-question-index", type = "cool-question-table")
@Mapping
public class Question {
    private Long id;
    /**
     * 实体来源
     */
    private String source;
    /**
     * 题干
     */
    private String content;
    /**
     * 是否收藏 0没有收藏 1已收藏
     */
    private Integer collectionFlag;
}