package cn.mibcxb.java.mime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class McMimeLog {
    private static final String LOGGER_NAME = "McMime@" + McMime.VERSION;
    private static final Logger LOGGER = LoggerFactory.getLogger(LOGGER_NAME);

    public static Logger logger() {
        return LOGGER;
    }

    public static void printE(Throwable throwable) {
        printE(null, throwable);
    }

    public static void printE(String message, Throwable throwable) {
        if (throwable == null) {
            if (McMimeUtils.isNotEmpty(message)) {
                LOGGER.error(message);
            }
        } else {
            if (message == null) {
                message = throwable.getMessage();
            }
            LOGGER.error(message, throwable);
        }
    }
}
