package com.shupro.core.utils.http;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
 
public class HttpClientUtils_old {
 
    private static PoolingHttpClientConnectionManager connectionManager = null;
    private static HttpClientBuilder httpBulder = null;
    private static RequestConfig requestConfig = null;
 
    private static int MAX_CONN = 10;
 
    private static int DEFAULT_MAX_CONN = 5;
 
//    private static String IP;
//    private static int PORT;
 
    static {
        //设置http的状态参数
        requestConfig = RequestConfig.custom()
                .setSocketTimeout(1000*60*60)//时间单位：毫秒
                .setConnectTimeout(1000*60*60)
                .setConnectionRequestTimeout(1000*60*60)
                .build();
 
        connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(MAX_CONN);
        connectionManager.setDefaultMaxPerRoute(DEFAULT_MAX_CONN);
        httpBulder = HttpClients.custom();
        httpBulder.setConnectionManager(connectionManager);
    }
 
    @Deprecated
    public static CloseableHttpClient getConnection(String ip, int port) {
        HttpHost target = new HttpHost(ip, port);
        connectionManager.setMaxPerRoute(new HttpRoute(target), 20);
        CloseableHttpClient httpClient = httpBulder.build();
       
        return httpClient;
    }
    
    /**
     * 获得http连接
     * @param url 请求地址
     * @return
     */
    public static CloseableHttpClient getConnection(String url) {
    	HttpHost target = new HttpHost(url);
    	connectionManager.setMaxPerRoute(new HttpRoute(target), 20);
    	CloseableHttpClient httpClient = httpBulder.build();
    	
    	return httpClient;
    }
 
    /**
     * 发送请求
     * @param map	参数
     * @param url	请求地址
     * @param method	post或get方式
     * @return
     */
    public static HttpUriRequest getRequestMethod(Map<String, Object> map, String url, String method) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        Set<Map.Entry<String, Object>> entrySet = map.entrySet();
        for (Map.Entry<String, Object> e : entrySet) {
            String name = e.getKey();
            String value = e.getValue().toString();
            NameValuePair pair = new BasicNameValuePair(name, value);
            params.add(pair);
        }
        RequestBuilder reqBuilder = null;
        if ("post".equals(method)) {
        	reqBuilder = RequestBuilder.post();
        } else if ("get".equals(method)) {
        	reqBuilder = RequestBuilder.get();
        }
        HttpUriRequest reqMethod = reqBuilder.setUri(url)
        		.addParameters(params.toArray(new BasicNameValuePair[params.size()]))
                .setConfig(requestConfig).build();
        //reqMethod.setHeader("Content-Length", "10240");
        
//        HttpUriRequest reqMethod = null;
//        if ("post".equals(method)) {
//        	reqMethod = RequestBuilder.post().setUri(url)
//        			.addParameters(params.toArray(new BasicNameValuePair[params.size()]))
//        			.setConfig(requestConfig).build();
//        	//reqMethod.setHeader("Content-Length", "10240");
//        } else if ("get".equals(method)) {
//        	reqMethod = RequestBuilder.get().setUri(url)
//        			.addParameters(params.toArray(new BasicNameValuePair[params.size()]))
//        			.setConfig(requestConfig).build();
//        }
        return reqMethod;
    }
    
    /**
     * 获得返回结果String
     * @param map
     * @param url 路径
     * @param method 请求方式
     * @return
     * @throws Exception
     */
	public static String getResult(Map<String, Object> map, String url, String method) throws Exception {
		String message = "";
		
		HttpClient client = getConnection(url);
    	HttpUriRequest reqMethod = getRequestMethod(map, url, method);
    	HttpResponse response = client.execute(reqMethod);
		if (response.getStatusLine().getStatusCode() == 200) {
		    HttpEntity entity = response.getEntity();
		    message = EntityUtils.toString(entity, "utf-8");
		} else {
		    System.out.println("请求失败");
		}
		
		return message;
	}
	
	public static String doGet(String url) throws Exception {
		return getResult(null, url, "get");
	}
    
	public static String doGet(Map<String, Object> map, String url) throws Exception {
		return getResult(map, url, "get");
	}
	
	public static String doPost(String url) throws Exception {
		return getResult(null, url, "post");
	}
	
	public static String doPost(Map<String, Object> map, String url) throws Exception {
		return getResult(map, url, "post");
	}

}
