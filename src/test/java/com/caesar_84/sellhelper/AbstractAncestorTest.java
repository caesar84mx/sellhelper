package com.caesar_84.sellhelper;

import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class AbstractAncestorTest {
    private static Logger logger = LoggerFactory.getLogger(AbstractAncestorTest.class);

    private static StringBuilder stringBuilder = new StringBuilder();

    @Rule
    public Stopwatch stopwatch = new Stopwatch() {
        @Override
        protected void finished(long nanos, Description description) {
            String result = String.format("%-21s|%7d", description.getMethodName(), TimeUnit.NANOSECONDS.toMillis(nanos));
            stringBuilder.append("||-").append(result).append(" ms   ||\n").append("+-----------------------+--------------+\n");
            logger.info(result + " ms\n");
        }
    };

    @AfterClass
    public static void printResults() {
        logger.info("\n+=======================+==============+" +
                    "\n|| Test                 || Duration   ||" +
                    "\n+=======================+==============+" +
                    "\n" + stringBuilder);
    }
}