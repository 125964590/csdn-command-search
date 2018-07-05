package com.huatu.ic.search.util.command;

import com.huatu.ic.search.request.mq.BaseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author jbzm
 * @date 201811:12 PM
 **/
@Component
public class ChooseCommand {
    private final UserCommand userCommand;
    private final QuestionCommand questionCommand;

    @Autowired
    public ChooseCommand(UserCommand userCommand, QuestionCommand questionCommand) {
        this.userCommand = userCommand;
        this.questionCommand = questionCommand;
    }


    /**
     * 类型选择器
     */
    public void typeChoose(BaseRequest baseRequest) {
        switch (baseRequest.getOperateType()) {
            case "user":
                userCommand.methodChoose(baseRequest);
                break;
            case "question":
                questionCommand.methodChoose(baseRequest);
                break;
            default:
        }
    }
}