package com.huatu.ic.search.util.command;

import com.huatu.ic.search.repository.JbzmRepository;
import com.huatu.ic.search.request.mq.BaseRequest;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author jbzm
 * @date 20186:25 PM
 **/
@NoArgsConstructor
public abstract class BaseCommand<T, ID extends Serializable> {

    /**
     * @return es for jpa
     */
    public abstract JbzmRepository<T, ID> getJbzmRepository();

    /**
     * 方法选择器
     *
     */
    public abstract void methodChoose(BaseRequest baseRequest);

    /**
     * 保存操作
     */
    public void saveOperation(T t) {
        getJbzmRepository().save(t);
    }

    /**
     * 修改操作
     */
    public void updateOperation(T t, ID id) {
        deleteOperation(id);
        saveOperation(t);
    }

    /**
     * 删除操作
     */
    public void deleteOperation(ID id) {
        getJbzmRepository().delete(id);
    }
}