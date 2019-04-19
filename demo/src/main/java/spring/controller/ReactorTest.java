package spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Created by IntelliJ IDEA.
 * User: johnny.ly
 * Date: 2019/4/17
 * Time: 19:51
 * Desc:
 */
@RestController
@RequestMapping(value = "/reactor")
public class ReactorTest {

    @GetMapping("/test")
    public Mono<String> getUserBase() {
        return Mono.create(stringMonoSink -> stringMonoSink.success("getUserBase:" + Math.random()));
    }
}
