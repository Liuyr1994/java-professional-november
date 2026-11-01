package ru.otus;

import com.google.common.base.Strings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HelloOtus {
    private static final Logger logger = LogManager.getLogger(HelloOtus.class.getName());

    public static void main(String[] args) {
        String result = Strings.repeat("Guava! ", 3);
        logger.info(result);
    }
}
