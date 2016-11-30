package org.siqisource.stone.runtime.web;

import java.net.URI;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtils {

	public static Map<String, String> get(String url){
		Map<String, String> result = new HashMap<String, String>();

		try {
			CloseableHttpClient httpClient = null;
			if (url.startsWith("https://")) {
				httpClient = createSSLClientDefault();
			} else {
				httpClient = HttpClientBuilder.create().build();
			}

			HttpGet get = new HttpGet();
			get.setURI(new URI(url));
			HttpResponse httpResponse = httpClient.execute(get);
			Integer statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode == 200) {
				HttpEntity httpEntity = httpResponse.getEntity();
				String repoJson = EntityUtils.toString(httpEntity);// 取出应答字符串
				ObjectMapper mapper = new ObjectMapper();
				result = mapper.readValue(repoJson, new TypeReference<Map<String, String>>() {
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private static CloseableHttpClient createSSLClientDefault()
			throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
			public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				return true;
			}
		}).build();

		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
		return HttpClients.custom().setSSLSocketFactory(sslsf).build();

	}

}
