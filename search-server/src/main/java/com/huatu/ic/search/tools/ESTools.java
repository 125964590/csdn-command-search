package com.huatu.ic.search.tools;


import java.io.IOException;
import java.net.InetAddress;

import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;


/**
 * @author zhouwei
 * @Description: TODO
 * @create 2018-06-21 下午9:34
 **/
public class ESTools {

    public final static Client client = build();


    /**
     * 创建一次
     *
     * @return
     */
    public static Client build() {
        if (null != client) {
            return client;
        }
        Client client = null;
        try {
            Settings settings = Settings
                    .settingsBuilder()
                    .put("cluster.name", "huatu-ztk-cluster")
                    .put("client.transport.sniff", true)
                    .build();
            client = TransportClient.builder().settings(settings).build()
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.100.21"), 9300));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return client;
    }

    public static XContentBuilder getMapping() {
        XContentBuilder mapping = null;
        try {
            mapping = jsonBuilder().startObject().startObject("properties").
                    startObject("id").field("type", "long").endObject().
                    startObject("username").field("type", "string").field("index", "not_analyzed").endObject().
                    startObject("nickname").field("type", "string").field("index", "not_analyzed").endObject().
                    startObject("realName").field("type", "string").field("index", "not_analyzed").endObject().
                    startObject("sex").field("type", "integer").endObject().
                    startObject("collectionFlag").field("type", "integer").endObject().
                    startObject("avatar").field("type", "string").endObject().
                    startObject("signature").field("type", "string").endObject()
                    .endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapping;
    }

    public static void createUserMapping() {
        PutMappingRequest mapping = Requests.putMappingRequest("cool-user-index").type("cool-user-table").source(getMapping());
        ESTools.client.admin().indices().putMapping(mapping).actionGet();

    }

    public static void main(String[] args) {
        createUserMapping();
    }

}
