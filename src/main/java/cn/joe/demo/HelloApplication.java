package cn.joe.demo;

import java.nio.charset.Charset;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.joe.demo.service.HttpClientService;

/**
 * Spring boot项目一啊不能都会有一个*Application的入口类，入口类中会有一个main 的方法， 这是一个标准的java应用程序的入库方法
 * 
 * @author wanqiao
 *
 */

@Controller
// 表明是springMVC的controller控制器
@SpringBootApplication // (exclude = { RedisAutoConfiguration.class })
// 表明是springBoot的应用 主要是用来开启核心配置 开启自动配置
@Configuration
// spring的配置类
public class HelloApplication {

	@Autowired
	RedisTemplate<String, String> redisTemplate;

	@Autowired
	HttpClientService httpService;

	@RequestMapping("hello")
	@ResponseBody
	public String hello() {
		return "hello world！ JOE！万桥";
	}

	@RequestMapping("redis")
	@ResponseBody
	public String redis() {
		redisTemplate.opsForValue().set("wanqiao", "Joe");
		return "success";
	}

	@RequestMapping("redis/get")
	@ResponseBody
	public String redisGetValue() {

		return redisTemplate.opsForValue().get("msg");
	}

	@RequestMapping(value="country/location/{country}/{level}",method=RequestMethod.GET)
	@ResponseBody
	public String getLocationByCountry(@PathVariable  String country,@PathVariable  String level) {
		System.out.println(country);
		return httpService
				.getByUrl("http://restapi.amap.com/v3/config/district?key=8d556c3e396640ae1d02bd1be72077b4&keywords="
						+ "中国" + "&subdistrict=" + level + "&extensions=base");
	}

	/**
	 * 配置自定义消息转换器
	 * 
	 * @return
	 */
	/*
	 * @Bean public StringHttpMessageConverter stringHttpMessageConverter() {
	 * StringHttpMessageConverter converter = new
	 * StringHttpMessageConverter(Charset.forName("ISO-8859-1")); return
	 * converter; }
	 */

	public static void main(String[] args) {
		// 运行spring的应用
		// SpringApplication.run(HelloApplication.class, args);
		SpringApplication application = new SpringApplication(HelloApplication.class);
		// 启动时看到的图标 称之为banner
		// application.setBannerMode(Mode.OFF);
		application.run(args);

	}

}
