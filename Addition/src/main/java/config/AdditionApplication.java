package config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yoon on 15. 7. 20..
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("web")
public class AdditionApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdditionApplication.class, args);
    }
}
