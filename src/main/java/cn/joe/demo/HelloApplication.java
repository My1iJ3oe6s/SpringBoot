package cn.joe.demo;

import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Spring boot项目一啊不能都会有一个*Application的入口类，入口类中会有一个main 的方法，
 * 这是一个标准的java应用程序的入库方法
 * @author wanqiao
 *
 */

@Controller 
//表明是springMVC的controller控制器
@SpringBootApplication//(exclude = { RedisAutoConfiguration.class })
//表明是springBoot的应用 主要是用来开启核心配置  开启自动配置
@Configuration 
//spring的配置类
public class HelloApplication {
	
	@Autowired
	RedisTemplate<String, String> redisTemplate;

    @RequestMapping("hello")
    @ResponseBody
    public String hello() {
        return "hello world！ JOE！万桥";
    }
    
    
    @RequestMapping("redis")
    @ResponseBody
    public String redis() {
         redisTemplate.opsForValue().set("wanqiao", "我爱你");
         return "success";
    }
    
    @RequestMapping("redis/get")
    @ResponseBody
    public String redisGetValue() {
         
         return redisTemplate.opsForValue().get("msg");
    }
    
    /**
     * 配置自定义消息转换器
     * @return
     */
    /*@Bean
    public StringHttpMessageConverter stringHttpMessageConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("ISO-8859-1"));
        return converter;
    }*/

    public static void main(String[] args) {
    	//运行spring的应用
        // SpringApplication.run(HelloApplication.class, args);
        SpringApplication application = new SpringApplication(HelloApplication.class);
        //启动时看到的图标  称之为banner
        //application.setBannerMode(Mode.OFF);
        application.run(args);
        
        
    }

}
