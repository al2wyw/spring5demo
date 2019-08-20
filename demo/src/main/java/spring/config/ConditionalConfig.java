package spring.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 * User: johnny.ly
 * Date: 2019/8/20
 * Time: 11:01
 * Desc:
 */
@Configuration
@ConditionalOnProperty(prefix = "com.test", name="start", havingValue = "normal", matchIfMissing = true)
public class ConditionalConfig {
    protected final Logger logger = LoggerFactory.getLogger(ConditionalConfig.class);
    @Bean
    public Object normalBean(){
        logger.info("normal bean start");
        return new Object();
    }
}
