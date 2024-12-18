package it.hype.centrico.token.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
@ComponentScan(basePackages = {
        "it.hype.centrico.token.manager",
        "it.hype.errorhandler",
        "it.hype.authhandler",
        "it.hype.requestinfoaspect"
})
@EnableFeignClients
@EnableMongoAuditing
public class Application {
    public static void main(String[] args) {
        System.out.println("Spring Boot Version: " +
                SpringBootVersion.getVersion());
        SpringApplication.run(Application.class, args);
    }
}
