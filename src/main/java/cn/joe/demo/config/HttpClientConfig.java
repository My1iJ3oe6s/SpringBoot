package cn.joe.demo.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SpringBoot集成HttpClient
 * 
 * @author myijo
 *
 */

@Configuration
public class HttpClientConfig {



	// @Value("${http.maxTotal}")
	private Integer maxTotal = 5;

	// @Value("${http.defaultMaxPerRoute}")
	private Integer defaultMaxPerRoute = 2;

	// @Value("${http.connectTimeout}")
	private Integer connectTimeout = 5000;

	// @Value("${http.connectionRequestTimeout}")
	private Integer connectionRequestTimeout = 5000;

	// @Value("${http.socketTimeout}")
	private Integer socketTimeout = 5000;

	// @Value("${http.staleConnectionCheckEnabled}")
	private boolean staleConnectionCheckEnabled = true;

	@Bean(name = "httpClientConnectionManager")
	public PoolingHttpClientConnectionManager getHttpClientConnectionManager() {
		PoolingHttpClientConnectionManager httpClientConnectionManager = new PoolingHttpClientConnectionManager();
		httpClientConnectionManager.setMaxTotal(maxTotal/*最大连接数*/);
		httpClientConnectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
		return httpClientConnectionManager;
	}

	/**
	 *
	 * @Qualifier (合格者) 配合@autowired使用 但注入的是接口时
	 * 但是接的实现却有两个，Spring会不知道注入的哪一个
	 * 这个时候使用@Qualifier来进行筛选合格的实现类
	 *
	 */

	@Bean(name = "httpClientBuilder")
	public HttpClientBuilder getHttpClientBuilder(
			@Qualifier("httpClientConnectionManager") PoolingHttpClientConnectionManager httpClientConnectionManager) {

		HttpClientBuilder httpClientBuilder
				= HttpClientBuilder.create();

		httpClientBuilder.setConnectionManager(httpClientConnectionManager);
		return httpClientBuilder;
	}

	@Bean
	public CloseableHttpClient getCloseableHttpClient(
			@Qualifier("httpClientBuilder") HttpClientBuilder httpClientBuilder) {
		return httpClientBuilder.build();
	}

	/**
	 * Http请求执行的过程中，HttpClient会自动添加下面的属性到Http上下文中：
	 * RequestConfig对象，表示http request的配置信息
	 * 
	 * 同一个逻辑会话中的多个Http请求，应该使用相同的Http上下文来执行，这样就可以自动地在http请求中传递会话上下文和状态信息。
	 * @return
	 */
	@Bean
	public RequestConfig getBuilder() {
		return RequestConfig.custom().setConnectTimeout(connectTimeout/* 从连接池中获取连接的超时时间 */)
				.setConnectionRequestTimeout(connectionRequestTimeout).setSocketTimeout(socketTimeout)
				.setStaleConnectionCheckEnabled(staleConnectionCheckEnabled).build();
	}

}
