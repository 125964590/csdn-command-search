package com.huatu.ic.search.base.config;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author hanchao
 * @date 2018/1/23 16:11
 */
@Configuration
@EnableApolloConfig("tiku.elastic-2.3.2")
@EnableElasticsearchRepositories(basePackages = "com.huatu.ic.search")
public class ElasticSearchConfig {
}
