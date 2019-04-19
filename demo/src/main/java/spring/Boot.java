package spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by IntelliJ IDEA.
 * User: johnny.ly
 * Date: 2019/4/3
 * Time: 11:03
 * Desc:
 */
@SpringBootApplication
public class Boot implements CommandLineRunner {

    protected final Logger logger = LoggerFactory.getLogger(Boot.class);

    public static void main(String[] args) {

        // start spring boot
        SpringApplication.run(Boot.class, args);

    }

    @Override
    public void run(String... strings) throws Exception {
        logger.info("app start, hello,");
    }
}
