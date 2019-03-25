package com.srjs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @FeignClient(value = "nacos-provider", fallback = EchoServiceFallback.class)
    public interface EchoService {
        @GetMapping(value = "/echo/{message}")
        String echo(@PathVariable("message") String message);
    }

    @Component
    public class EchoServiceFallback implements EchoService {
        @Override
        public String echo(String message) {
            return "echo fallback";
        }
    }


    @RestController
    public class NacosConsumerController {

        @Autowired
        EchoService echoService;

        @GetMapping(value = "/echo/hi")
        public String echo(HttpServletRequest request) {
            System.out.println(request.getHeader("a"));
            return echoService.echo("Hi Feign");
        }
    }
}
