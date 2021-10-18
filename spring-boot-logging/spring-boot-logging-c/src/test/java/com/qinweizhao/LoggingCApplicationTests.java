package com.qinweizhao;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoggingCApplicationTests {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    void contextLoads() {

        logger.trace("this is {} 1","trace");

        logger.debug("this is {} 2","debug");

        logger.info("this is {} 3","info");

        logger.warn("this is {} 4","warn");

        logger.error("this is {} 5","error");

    }

}
