package cn.joe.demo.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * HttpClient工具类
 * @author myijo
 *
 */

@Component
public class HttpClientService {

	@Autowired
	CloseableHttpClient httpClient;
	
	@Autowired
	RequestConfig config;
	
	/**
	 * get请求 
	 */
	public String getByUrl(String url){
		HttpGet get = new HttpGet(url);
		try {
			CloseableHttpResponse response = httpClient.execute(get);
			if(response.getStatusLine().getStatusCode() == 200){
				return EntityUtils.toString(response.getEntity(), "UTF-8");
				
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
}
