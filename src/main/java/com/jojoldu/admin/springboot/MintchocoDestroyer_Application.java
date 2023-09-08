package com.jojoldu.admin.springboot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MintchocoDestroyer_Application {
    public static void main(String[] args) {
        SpringApplication.run(MintchocoDestroyer_Application.class, args);
    }
}
