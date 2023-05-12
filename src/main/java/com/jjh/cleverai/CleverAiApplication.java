package com.jjh.cleverai;
import io.github.asleepyfish.annotation.EnableChatGPT;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan("com.jjh.cleverai.dao")
//@EnableChatGPT
@SpringBootApplication(scanBasePackages = "com.jjh.cleverai")
public class CleverAiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CleverAiApplication.class, args);
    }

}
