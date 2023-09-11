package com.jojoldu.admin.springboot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//실제로 어플리케이션을 실행하는 부분
@EnableJpaAuditing  //Spring Data JPA가 정보를 자동으로 갱신하게 해주는 부분 - 제어역전은 여기서 일어난다
@SpringBootApplication
public class MintchocoDestroyer_Application {
    public static void main(String[] args) {
        SpringApplication.run(MintchocoDestroyer_Application.class, args);
    }
}
