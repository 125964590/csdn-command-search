package com.huatu.ic.search.tools;

import com.alibaba.fastjson.JSONObject;
import com.huatu.ic.search.repository.User;

import java.util.Random;

/**
 * @author jbzm
 * @date 201810:38 PM
 **/
public class SearchTest {

    public static void main(String args[]) {
        User user = new User();
        user.setId(new Random().nextLong());
        user.setSex(new Random().nextInt(100));
        user.setUsername("zhouwei" + Math.random() * 1000);
        user.setAvatar("hello" + Math.random() * 1000);
        user.setPhoneNum("135" + Math.random() * 1000);
        user.setNickname("java" + Math.random() * 1000);
        user.setSignature("I am jack" + Math.random() * 1000);
        user.setCollectionFlag((int) (Math.random() * 4));
        String userToJson = JSONObject.toJSONString(user);

        System.out.println("lol");
    }
}