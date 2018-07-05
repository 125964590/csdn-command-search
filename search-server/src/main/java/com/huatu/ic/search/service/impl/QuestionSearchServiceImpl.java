package com.huatu.ic.search.service.impl;

import com.google.common.collect.Maps;
import com.huatu.ic.search.repository.Question;
import com.huatu.ic.search.repository.QuestionRepository;
import com.huatu.ic.search.repository.User;
import com.huatu.ic.search.service.QuestionSearchService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author jbzm
 * @date 201812:50 AM
 **/
@Service
@Slf4j
public class QuestionSearchServiceImpl implements QuestionSearchService {
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionSearchServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    /**
     * 搜索
     * TODO 简单写下，待优化
     */
    @Override
    public Object getQuestion(String keyword, int pageSize, int size) {
        WildcardQueryBuilder queryBuilder = QueryBuilders.wildcardQuery("content", "*" + keyword + "*");
        Pageable pageable = new PageRequest(pageSize, size);
        Page<Question> page = questionRepository.search(queryBuilder, pageable);
        page.forEach((u) -> log.info(u.getContent()));
        Map<String, Object> map = Maps.newHashMapWithExpectedSize(4);
        map.put("total", page.getSize());
        map.put("questions", page.getContent());
        return map;
    }
}