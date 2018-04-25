package cn.joe.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * Created by myijo on 2018/4/24.
 */
// 表明是springMVC的controller控制器
@SpringBootApplication // (exclude = { RedisAutoConfiguration.class })
// 表明是springBoot的应用 主要是用来开启核心配置 开启自动配置
@Configuration
// spring的配置类
public class Application {

    public static void main(String[] args) {
        // 运行spring的应用
        // SpringApplication.run(HelloApplication.class, args);
        SpringApplication application = new SpringApplication(Application.class);
        // 启动时看到的图标 称之为banner
        // application.setBannerMode(Mode.OFF);
        application.run(args);


    }
}
