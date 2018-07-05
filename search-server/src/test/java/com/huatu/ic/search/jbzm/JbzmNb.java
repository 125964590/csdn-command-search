package com.huatu.ic.search.jbzm;

import com.huatu.ic.search.SearchApplicationTests;
import com.huatu.ic.search.repository.Question;
import com.huatu.ic.search.repository.QuestionRepository;
import com.huatu.ic.search.repository.User;
import com.huatu.ic.search.service.UserSearchService;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Random;

/**
 * @author jbzm
 * @date 20181:05 AM
 **/
public class JbzmNb extends SearchApplicationTests {
    @Autowired
    UserSearchService userSearchService;
    @Autowired
    TransportClient transportClient;
    @Autowired
    QuestionRepository questionRepository;

    @Test
    public void test01() {
        int i = 0;
            while (i<100) {
                User user = new User();
                user.setId(new Random().nextLong());
                user.setSex(new Random().nextInt(100));
                user.setUsername("zhouwei" + Math.random() * 1000);
                user.setAvatar("hello" + Math.random() * 1000);
                user.setPhoneNum("135" + Math.random() * 1000);
                user.setNickname("java" + Math.random() * 1000);
                user.setSignature("I am jack" + Math.random() * 1000);
                user.setCollectionFlag((int) (Math.random() * 4));
                userSearchService.saveUser(user);
                i++;
            }
        }

    @Test
    public void test02() throws IOException {
        XContentBuilder xContentBuilder = XContentFactory.jsonBuilder().startObject();
        xContentBuilder.field("name", "jbzm");
        xContentBuilder.endObject();
        transportClient.prepareIndex("jbzm", "book").setSource(xContentBuilder.string(), XContentType.JSON);
    }

    @Test
    public void test04(){
        int i=0;
        while (i<100) {
            Question question = new Question();
            question.setCollectionFlag((int) (Math.random() * 3));
            question.setId(new Random().nextLong());
            question.setContent("jbzm" + Math.random() * 100);
            question.setSource("source ~~~ huatu" + Math.random() * 100);
            questionRepository.save(question);
            i++;
        }
    }
}