package com.qinweizhao;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author qinweizhao
 * @since 2021/9/7
 */
@SpringBootTest
class LevelTest {

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 日志的级别 由高到低 OFF > FATAL > ERROR > WARN > INFO > DEBUG > TRACE > ALL
     * SpringBoot 默认使用的是 info 级别，日志输出只会输出 info 级别之后的
     */
    @Test
    void testLevel() {
        logger.trace("这是trace日志...");
        logger.debug("这是debug日志...");
        logger.info("这是info日志...");
        logger.warn("这是warn日志...");
        logger.error("这是error日志...");
        logger.debug("this is a test");
        logger.info("今天是2021.9.8");
    }

}
