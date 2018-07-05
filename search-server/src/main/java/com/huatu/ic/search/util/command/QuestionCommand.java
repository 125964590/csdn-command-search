package com.huatu.ic.search.util.command;

import com.alibaba.fastjson.JSON;
import com.huatu.common.ErrorResult;
import com.huatu.common.exception.BizException;
import com.huatu.ic.search.repository.JbzmRepository;
import com.huatu.ic.search.repository.Question;
import com.huatu.ic.search.repository.QuestionRepository;
import com.huatu.ic.search.request.mq.BaseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jbzm
 * @date 201810:40 PM
 **/
@Service
public class QuestionCommand extends BaseCommand<Question, Long> {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionCommand(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public JbzmRepository<Question, Long> getJbzmRepository() {
        return questionRepository;
    }

    @Override
    public void methodChoose(BaseRequest baseRequest) {
        Question question = JSON.parseObject(baseRequest.getData(), Question.class);
        switch (baseRequest.getOperation()) {
            case "save":
                questionRepository.save(question);
                break;
            case "update":
                questionRepository.updateOperation(question, question.getId());
                break;
            case "delete":
                questionRepository.delete(question.getId());
                break;
            default:
                throw new BizException(ErrorResult.create(100025,"该功能尚未开放"));
        }
    }
}