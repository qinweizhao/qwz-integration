package com.qinweizhao.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.Result;
import co.elastic.clients.elasticsearch._types.ShardStatistics;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.elasticsearch.indices.DeleteIndexResponse;
import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import co.elastic.clients.elasticsearch.indices.IndexState;
import co.elastic.clients.json.JsonData;
import com.alibaba.fastjson.JSON;
import com.qinweizhao.elasticsearch.entity.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

@SpringBootTest
class ElasticsearchApplicationTests {

    @Test
    void contextLoads() {
    }


    @Autowired
    ElasticsearchClient client;

    @Test
    void test() throws Exception {

        SearchResponse<Account> bank = client.search(s ->
                s.index("bank")
                        .query(q ->
                                q.matchAll(
                                        m -> m
                                )), Account.class);
        assert bank.hits().total() != null;
        System.out.println(bank.hits().total().value());


        String qs = "mill";
        SearchResponse<Account> search = client.search(s -> s.index("bank").query(q ->
                q.match(m ->
                        m.field("address").query(qs)
                )
        ).size(10).from(1), Account.class);


        assert search.hits().total() != null;
        long value = search.hits().total().value();

        System.out.println(value);
    }


    String testDataIndex = "qwz_index";

    /**
     * 创建索引
     */
    @Test
    void createIndex() throws IOException {
        CreateIndexResponse qwz_index = client.indices().create(c -> c.index("qwz_index"));
        Boolean acknowledged = qwz_index.acknowledged();
        System.out.println("acknowledged = " + acknowledged);
    }


    @Test
    void getIndex() throws IOException {
        GetIndexResponse qwz_index = client.indices().get(g -> g.index("qwz_index"));
        Map<String, IndexState> resultMap = qwz_index.result();
        String result = JSON.toJSONString(resultMap);
        System.out.println("result = " + result);
    }

    @Test
    void deleteIndex() throws IOException {
        DeleteIndexResponse qwz_index = client.indices().delete(d -> d.index("qwz_index"));
        boolean acknowledged = qwz_index.acknowledged();
        System.out.println(acknowledged);
    }

    @Test
    void addDoc() throws IOException {
        Account account = new Account();
        account.setFirstname("q");
        account.setLastname("wz");
        account.setGender("M");
        account.setAddress("zg");
        account.setCity("hn");

        IndexResponse bank = client.index(i -> i.index("bank").document(account));

//        String s ="{'level': 'warn'}";
//        StringReader stringReader = new StringReader(s);
//        IndexResponse json = client.index(i -> i.index("bank").withJson(stringReader));

        ShardStatistics shards = bank.shards();
        Number successful = shards.successful();
        System.out.println("successful = " + successful);
        account.setFirstname("qin");
        IndexResponse bank2 = client.index(i -> i.index("bank").id(bank.id()).document(account));
        System.out.println(bank2.shards().successful());
        System.out.println("bank2.id() = " + bank2.id());
    }


    @Test
    void getDoc() throws IOException {
        GetRequest request = new GetRequest.Builder().index("bank").id("2Cpen4ABSWOoZKugEt8G").build();

        GetResponse<Account> accountGetResponse = client.get(request, Account.class);
        Account source1 = accountGetResponse.source();
        assert source1 != null;
        String firstname = source1.getFirstname();
        System.out.println("firstname = " + firstname);

        // 或
        // 如果为 json 则使用 ObjectNode.class

        GetResponse<Account> bank = client.get(g -> g.index("bank").id("2Cpen4ABSWOoZKugEt8G"), Account.class);
        if (bank.found()){
            Account source = bank.source();
            assert source != null;
            System.out.println(source.getLastname());
        }
    }


    @Test
    void updateDoc() throws IOException {
        Account account = new Account();
        account.setFirstname("qin");
        account.setLastname("qinweizhao");
        account.setGender("M");
        account.setAddress("zg");
        account.setCity("hn");
        UpdateResponse<Account> bank = client.update(u -> u.id("2Cpen4ABSWOoZKugEt8G").index("bank").doc(account), Account.class);
        Number successful = bank.shards().successful();
        System.out.println("successful = " + successful);
        String id = bank.id();
        System.out.println("id = " + id);
    }

    @Test
    void deleteDoc() throws IOException {
        DeleteResponse bank = client.delete(d -> d.index("bank").id("2Cpen4ABSWOoZKugEt8G"));
        Number successful = bank.shards().successful();
        System.out.println("successful = " + successful);
    }

}
