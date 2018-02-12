package pack;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestStart {

    Logger log = LoggerFactory.getLogger(TestStart.class);

    @Test
    public void test() throws Exception{
        log.debug("some debug level log");
        log.info("some info level log");
        log.error("some error level log");
    }

}
