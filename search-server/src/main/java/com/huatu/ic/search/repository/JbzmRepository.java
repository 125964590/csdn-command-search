package com.huatu.ic.search.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @author jbzm
 * @date 20181:30 AM
 **/
@NoRepositoryBean
public interface JbzmRepository<T, ID extends Serializable> extends ElasticsearchRepository<T, ID> {

    /**
     * update data
     *
     * @param t  data
     * @param id id
     */
    default void updateOperation(T t, ID id) {
        delete(id);
        save(t);
    }
}
