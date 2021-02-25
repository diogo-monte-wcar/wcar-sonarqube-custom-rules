import org.apache.logging.log4j.Logger;

class MyClass {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyClass.class);

    MyClass(MyClass mc) {
    }

    void info(String s) {
        // Empty
    }

    void checkLogQuality(int value) {
        LOGGER.info("test"); // Noncompliant {{Avoid logging only a string literal.}}

        String k = "test";
        LOGGER.info(k);

        this.info("test");

    }
}
