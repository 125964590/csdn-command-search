package com.huatu.ic.search.service.impl;

import com.google.common.collect.Maps;
import com.huatu.ic.search.repository.User;
import com.huatu.ic.search.repository.UserRepository;
import com.huatu.ic.search.service.UserSearchService;
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
 * @date 201812:47 AM
 **/
@Service
@Slf4j
public class UserSearchServiceImpl implements UserSearchService {
    private final UserRepository userRepository;

    @Autowired
    public UserSearchServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    /**
     * 搜索
     * TODO 简单写下，待优化
     */
    @Override
    public Map<String, Object> getUser(String keyword, int from, int size) {
        WildcardQueryBuilder queryBuilder = QueryBuilders.wildcardQuery("nickname", "*" + keyword + "*");
        Pageable pageable = new PageRequest(from, size);
        Page<User> page = userRepository.search(queryBuilder, pageable);
        page.forEach((u) -> log.info(u.getNickname()));
        Map<String, Object> map = Maps.newHashMapWithExpectedSize(4);
        map.put("total", page.getSize());
        map.put("users", page.getContent());
        return map;
    }
}