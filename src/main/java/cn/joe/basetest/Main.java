package cn.joe.basetest;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		// 啓動springboot並指定java bean配置文件
		AnnotationConfigApplicationContext content = new AnnotationConfigApplicationContext(SpringConfig.class);
		UserService bean = content.getBean(UserService.class);
		List<User> queryUserList = bean.queryUserList();
		for (User user : queryUserList) {
			System.out.println(user.toString());
		}
		content.destroy();
	}

}
