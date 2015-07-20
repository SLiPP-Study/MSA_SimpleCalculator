package config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import web.interceptor.AuthenticationInterceptor;

/**
 * Created by yoon on 15. 7. 8..
 */
@Configuration
@ComponentScan({"web"})
public class WebConfig extends WebMvcConfigurationSupport {

    public static final String COOKIE_NAME = "MSA_COOKIE";
    public static final String TEST_COOKIE_VALUE = "abcdefg";

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticationInterceptor())
            .addPathPatterns("/**")
            .excludePathPatterns("/users");

    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }
}
