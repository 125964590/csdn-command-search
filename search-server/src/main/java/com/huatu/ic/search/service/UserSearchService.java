package com.huatu.ic.search.service;


import com.huatu.ic.search.repository.User;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;


/**
 * @author zhouwei
 * @Description: TODO
 * @create 2018-06-21 下午2:25
 **/
public interface UserSearchService {
    /**
     * 插入
     * @param message
     */
    void saveUser(User message);

    /**
     * 搜索
     * @param keyword
     * @return
     */
    Map<String,Object> getUser(String keyword, int from, int size);
}
