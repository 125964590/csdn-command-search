package com.huatu.ic.search.jbzm.rabbit;

import com.alibaba.fastjson.JSONObject;
import com.huatu.ic.search.SearchApplicationTests;
import com.huatu.ic.search.repository.Question;
import com.huatu.ic.search.repository.User;
import com.huatu.ic.search.request.mq.BaseRequest;
import com.huatu.ic.search.util.command.BaseCommand;
import com.huatu.ic.search.util.command.ChooseCommand;
import com.huatu.ic.search.util.command.QuestionCommand;
import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

/**
 * @author jbzm
 * @date 20181:32 PM
 **/
public class RabbitTest extends SearchApplicationTests {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    QuestionCommand questionCommand;
    @Autowired
    ChooseCommand chooseCommand;

    @Test
    public void test01() {
        User user = new User();
        user.setId(new Random().nextLong());
        user.setSex(new Random().nextInt(100));
        user.setUsername("zhouwei" + Math.random() * 1000);
        user.setAvatar("hello" + Math.random() * 1000);
        user.setPhoneNum("135" + Math.random() * 1000);
        user.setNickname("java" + Math.random() * 1000);
        user.setSignature("I am jack" + Math.random() * 1000);
        rabbitTemplate.convertAndSend("cool_user_queue", user);
    }

    @Test
    public void test02() {

        Question question = new Question();
        question.setCollectionFlag((int) (Math.random() * 3));
        question.setId(new Random().nextLong());
        question.setContent("jbzm" + Math.random() * 100);
        question.setSource("source ~~~ huatu" + Math.random() * 100);
        rabbitTemplate.convertAndSend("cool_question_queue", question);
    }

    @Test
    public void test03() {
        Question question = new Question();
        question.setCollectionFlag((int) (Math.random() * 3));
        question.setId(new Random().nextLong());
        question.setContent("jbzm" + Math.random() * 100);
        question.setSource("source ~~~ huatu" + Math.random() * 100);
        questionCommand.saveOperation(question);
        questionCommand.updateOperation(question, question.getId());
    }

    @Test
    public void test04() {
        Question question = new Question();
        question.setCollectionFlag((int) (Math.random() * 3));
        question.setId(5415258235777307000L);
        question.setContent("jbzm" + Math.random() * 100);
        question.setSource("source ~~~ huatu" + Math.random() * 100);
        String toJSONString = JSONObject.toJSONString(question);
        BaseRequest baseRequest=new BaseRequest();
        baseRequest.setData(toJSONString);
        baseRequest.setOperateType("question");
        baseRequest.setOperation("save");
        rabbitTemplate.convertAndSend("cool_question_queue", baseRequest);
        chooseCommand.typeChoose(baseRequest);
        System.out.println("l");
    }
}