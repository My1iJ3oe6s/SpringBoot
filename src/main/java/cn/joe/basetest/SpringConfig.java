package cn.joe.basetest;

import com.jolbox.bonecp.BoneCPDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
// 通过该注解来表明该类是一个Spring的配置，相当于一个xml文件
@ComponentScan(basePackages = "cn.joe.javaconfig")
// 配置扫描包
@PropertySource(value = { "classpath:jdbc.properties" }, ignoreResourceNotFound = true)
public class SpringConfig {

	@Bean
	// 通过该注解来表明是一个Bean对象，相当于xml中的<bean>
	public UserDAO getUserDAO() {
		return new UserDAO(); // 直接new对象做演示
	}

	@Bean(name = "user2")
	// 通过该注解来表明是一个Bean对象，相当于xml中的<bean>
	public User getUser() {
		User user = new User();
		user.setAge(10);
		user.setUsername("wanqiao");
		return user; // 直接new对象做演示
	}

	@Bean(name = "user3")
	public User getUser1() {
		User user = new User();
		user.setAge(50);
		user.setUsername("joe");
		return user;
	}

	@Bean(name = "user1")//User存在多个实例的时候依赖注入需要指定name属性 通过@Qualifier注解来筛选
	public User get(@Qualifier("user3") User user) {
		user.setAge(user.getAge() + 20);
		return user ;
	}

	@Value("${jdbc.url}")
	private String jdbcUrl;

	@Value("${jdbc.driverClassName}")
	private String jdbcDriverClassName;

	@Value("${jdbc.username}")
	private String jdbcUsername;

	@Value("${jdbc.password}")
	private String jdbcPassword;

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		BoneCPDataSource boneCPDataSource = new BoneCPDataSource();
		// 数据库驱动
		boneCPDataSource.setDriverClass(jdbcDriverClassName);
		// 相应驱动的jdbcUrl
		boneCPDataSource.setJdbcUrl(jdbcUrl);
		// 数据库的用户名
		boneCPDataSource.setUsername(jdbcUsername);
		// 数据库的密码
		boneCPDataSource.setPassword(jdbcUsername);
		// 检查数据库连接池中空闲连接的间隔时间，单位是分，默认值：240，如果要取消则设置为0
		boneCPDataSource.setIdleConnectionTestPeriodInMinutes(60);
		// 连接池中未使用的链接最大存活时间，单位是分，默认值：60，如果要永远存活设置为0
		boneCPDataSource.setIdleMaxAgeInMinutes(30);
		// 每个分区最大的连接数
		boneCPDataSource.setMaxConnectionsPerPartition(100);
		// 每个分区最小的连接数
		boneCPDataSource.setMinConnectionsPerPartition(5);
		return boneCPDataSource;
	}

}
