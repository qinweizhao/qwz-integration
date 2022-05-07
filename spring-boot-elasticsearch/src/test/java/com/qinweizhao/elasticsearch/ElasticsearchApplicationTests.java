package com.qinweizhao.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.qinweizhao.elasticsearch.entity.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ElasticsearchApplicationTests {

    @Test
    void contextLoads() {
    }


    @Autowired
    ElasticsearchClient elasticsearchClient;

//    GET bank/_search
//    {
//        "query": {
//        "match": {
//            "address": "mill"
//        }
//    },
//        "size": 0,
//            "aggs": {
//        "group_by_age": {
//            "terms": {
//                "field": "age"
//            }
//        },
//        "avg_age":{
//            "avg": {
//                "field": "age"
//            }
//        }
//    }
//    }

    @Test
    void searchData() throws Exception {

        SearchResponse<Account> bank = elasticsearchClient.search(s ->
                s.index("bank")
                        .query(q ->
                                q.matchAll(
                                        m->m
                                )), Account.class);
        assert bank.hits().total() != null;
        System.out.println(bank.hits().total().value());


        String qs = "mill";
        SearchResponse<Account> search = elasticsearchClient.search(s -> s.index("bank").query(q ->
                q.match(m ->
                        m.field("address").query(qs)
                )
        ).size(10).from(1), Account.class);


        assert search.hits().total() != null;
        long value = search.hits().total().value();

        System.out.println(value);
    }

}
