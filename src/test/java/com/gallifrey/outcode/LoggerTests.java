package com.gallifrey.outcode;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = OutCodeApplication.class)

public class LoggerTests {
    private static final Logger logger= LoggerFactory.getLogger(LoggerTests.class);
    @Test
    public void testLogger(){
        System.out.println(logger.getName());
        logger.trace("trace log");
        logger.debug("debug log");
        logger.info("info log");
        logger.warn("warn log");
        logger.error("error log");

    }

}
