package com.huatu.ic.search.util.command;

import com.alibaba.fastjson.JSON;
import com.huatu.common.ErrorResult;
import com.huatu.common.exception.BizException;
import com.huatu.ic.search.repository.JbzmRepository;
import com.huatu.ic.search.repository.User;
import com.huatu.ic.search.repository.UserRepository;
import com.huatu.ic.search.request.mq.BaseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jbzm
 * @date 20182:19 PM
 **/
@Service
public class UserCommand extends BaseCommand<User, Long> {
    private final UserRepository userRepository;

    @Autowired
    public UserCommand(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public JbzmRepository<User, Long> getJbzmRepository() {
        return userRepository;
    }

    @Override
    public void methodChoose(BaseRequest baseRequest) {
        User user = JSON.parseObject(baseRequest.getData(), User.class);
        switch (baseRequest.getOperation()) {
            case "save":
                userRepository.save(user);
                break;
            case "update":
                userRepository.updateOperation(user, user.getId());
                break;
            case "delete":
                userRepository.delete(user.getId());
                break;
            default:
                throw new BizException(ErrorResult.create(100025, "该功能尚未开放"));
        }
    }
}