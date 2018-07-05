package com.huatu.ic.search.mq.listeners;

import com.alibaba.fastjson.JSON;
import com.huatu.ic.search.repository.Question;
import com.huatu.ic.search.repository.QuestionRepository;
import com.huatu.ic.search.repository.User;
import com.huatu.ic.search.request.mq.BaseRequest;
import com.huatu.ic.search.service.UserSearchService;
import com.huatu.ic.search.util.command.BaseCommand;
import com.huatu.ic.search.util.command.ChooseCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author hanchao
 * @date 2018/1/18 15:51
 */
@Slf4j
@Component
public class MessageListener {

    private final MessageConverter messageConverter;
    private final UserSearchService userSearchService;
    private final QuestionRepository questionRepository;
    private final ChooseCommand chooseCommand;

    @Autowired
    public MessageListener(MessageConverter messageConverter, UserSearchService userSearchService, QuestionRepository questionRepository, ChooseCommand chooseCommand) {
        this.messageConverter = messageConverter;
        this.userSearchService = userSearchService;
        this.questionRepository = questionRepository;
        this.chooseCommand = chooseCommand;
    }


    @RabbitListener(queues = "cool_user_queue", containerFactory = "rabbitFactory")
    public void onMessageForUser(Message message) {
        try {
            log.info("cool_user_queue");
            BaseRequest baseRequest = (BaseRequest) messageConverter.fromMessage(message);
            chooseCommand.typeChoose(baseRequest);
            log.debug(baseRequest.getOperation()+"一条数据:user"+baseRequest.getData());
        } catch (Exception e) {
            log.error("deal message error，data={}", message, e);
        }

    }

    @RabbitListener(queues = "cool_question_queue", containerFactory = "rabbitFactory")
    public void onMessageForQuestion(Message message) {
        try {
            log.info("cool_question_queue");
            BaseRequest baseRequest = (BaseRequest) messageConverter.fromMessage(message);
            chooseCommand.typeChoose(baseRequest);
            log.debug(baseRequest.getOperation()+"一条数据:question"+baseRequest.getData());
        } catch (Exception e) {
            log.error("deal message error，data={}", message, e);
        }
    }
}
