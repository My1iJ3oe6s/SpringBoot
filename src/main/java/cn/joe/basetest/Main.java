package cn.joe.basetest;

import java.util.List;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication // (exclude = { RedisAutoConfiguration.class })

// 表明是springBoot的应用 主要是用来开启核心配置 开启自动配置

@Configuration

public class Main {

	public static void main(String[] args) {
		// 启动springboot並指定java bean配置文件
		AnnotationConfigApplicationContext content = new AnnotationConfigApplicationContext(Main.class);
		UserService bean = content.getBean(UserService.class);
		List<User> queryUserList = bean.queryUserList();
		for (User user : queryUserList) {
			System.out.println(user.toString());
		}
		content.destroy();
	}

}