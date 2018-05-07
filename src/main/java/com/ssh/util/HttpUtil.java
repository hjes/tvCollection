
package com.ssh.util;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpUtil {
	public static HttpClient httpclient = null;
	private static String cookies;
	static {
		try {
				httpclient = new SSLClient();
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}
	//get缃戦〉
	public static String GetPageContent(String url, String cookie) throws ClientProtocolException, IOException{
		StringBuffer result = null;
		HttpResponse response = null;
		HttpGet request = new HttpGet(url);
		//System.out.println(cookies);
		request.setHeader("Cookie", cookie);
		response = httpclient.execute(request);
        BufferedReader rd = null;
        rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(),"UTF-8"));
		result = new StringBuffer();
		String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line+"\n");
			}
			rd.close();
			setCookies(
					response.getFirstHeader("Set-Cookie") == null ? "" : response.getFirstHeader("Set-Cookie").toString());
		return result.toString();
	}
	
	//鑾峰緱鍥剧墖楠岃瘉鐮�
	public static void GetPhotoContent(String url, String cookie) throws ClientProtocolException, IOException{
		HttpResponse response = null;
		HttpGet request = new HttpGet(url);
		//System.out.println(cookies);
		request.setHeader("Cookie", cookie);
		response = httpclient.execute(request);
		OutputStream out = new FileOutputStream(new File("c:\\newfile.png"));  
		response.getEntity().writeTo(out);                      
		out.flush();  
		out.close();  
		System.out.println("璇峰湪c:\\newfile.png鏌ョ湅楠岃瘉鐮�");
	    setCookies(
					response.getFirstHeader("Set-Cookie") == null ? "" : response.getFirstHeader("Set-Cookie").toString());
	}
    //post璇锋眰
	@SuppressWarnings("unchecked")
	public static String postWithParameters(Map<String,String> map, String postUrl, String cookie) throws IOException {
		StringBuffer result = null;
		HttpResponse response = null;
		HttpPost httpost = new HttpPost(postUrl);
		httpost.setHeader("Cookie", cookie);
		//璁剧疆鍙傛暟  
        List<NameValuePair> list = new ArrayList<NameValuePair>();  
        java.util.Iterator<Entry<String, String>> iterator = map.entrySet().iterator();  
        while(iterator.hasNext()){  
            Entry<String,String> elem = (Entry<String, String>) iterator.next();  
            list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));  
        }  
        if(list.size() > 0){  
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,"UTF-8");  
            httpost.setEntity(entity);
        }  
		response = httpclient.execute(httpost);
		Header[] myheader=response.getAllHeaders();
//		for(int i=0;i<myheader.length;i++){
//			System.out.println(myheader[i]);
//		}
			BufferedReader rd = null;
			rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(),"UTF-8"));
			result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line+"\n");
			}
			setCookies(response.getFirstHeader("Set-Cookie") == null ? ""
					: response.getFirstHeader("Set-Cookie").toString());
			rd.close();
		return result.toString();
	}
	public static String getCookies() {
		return cookies;
	}

	public static void setCookies(String cookies) {
		HttpUtil.cookies = cookies;
	}

}
