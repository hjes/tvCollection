package com.ssh.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
public class httputiltest {

	/**
	 * @param args
	 * @throws Exception 
	 * @throws HttpException 
	 */
	public static void main(String[] args) throws Exception{
	   //��¼��ҳ��
	   String mainurl = "https://jx.122.gov.cn/"; 
	   //��¼ҳ��
	   String loginurl="https://jx.122.gov.cn/m/login";
	   //��ȡ��֤��ҳ��
	   String yanzhengmaurl="https://jx.122.gov.cn/captcha1?nocache="+new Random().nextInt(1000);
       //post �û�������ҳ��
	   String postloginurl="https://jx.122.gov.cn/user/m/login";
	   //��¼�ɹ����ҳ��
	   String loginsucess="https://jx.122.gov.cn/views/member";
	   //Υ����ʷ��¼url
	   String breakLowHistory="https://jx.122.gov.cn/user/m/uservio/vehssuris";
	   //������Ϣ
	   String myInfo="https://jx.122.gov.cn/user/m/index";
	   //ѡ�����switch
	   String myCitySwitch="https://jx.122.gov.cn/m/index/switch";
	   //��ʻ֤����URL
	   String myJiaShiZhengJiFen="https://jx.122.gov.cn/user/m/userinfo/drvendorse";
	   
	   
	   //��¼����ҳ
       String myresult= HttpUtil.GetPageContent(mainurl, "");
       //����¼ҳ��
       String loginresult=HttpUtil.GetPageContent(loginurl, "");
       //��ȡͼƬ��֤��
       HttpUtil.GetPhotoContent(yanzhengmaurl,"userpub=1");
        System.out.print("����ͼƬ��֤��:");
        Scanner scan = new Scanner(System.in);
        String read = scan.nextLine();
        System.out.println("�������ˣ�"+read); 
        
       //post��¼ 
        Map<String,String> createMap = new HashMap<String,String>();  
        createMap.put("usertype","1");  
        createMap.put("systemid","main");  
        createMap.put("username","");  
        createMap.put("password",""); 
        createMap.put("captcha",read);
        String mypostresult=HttpUtil.postWithParameters(createMap, postloginurl, "");
        
        String loginsucessstr=HttpUtil.GetPageContent(loginsucess, "");
       
        //post��ȡΥ����ʷ��¼
        Map<String,String> createMapList = new HashMap<String,String>();  
        createMapList.put("hpzl","02");  
        createMapList.put("hphm","");  
        createMapList.put("page","0");  
        createMapList.put("size","10"); 
        System.out.println("------------��ӡΥ����ʷ��¼��-----------");
        String breakLowHistoryStr=HttpUtil.postWithParameters(createMapList, breakLowHistory, ""); 
        System.out.println(breakLowHistoryStr);
        
        
        //post��ȡ��ʻ֤�Ʒ�
        Map<String,String> createMapJiFen = new HashMap<String,String>();  
        createMapJiFen.put("page","0");  
        createMapJiFen.put("size","10"); 
        System.out.println("------------��ӡ��ʻ֤���ּ�¼��-----------");
        String myJiaShiZhengJiFenStr=HttpUtil.postWithParameters(createMapJiFen, myJiaShiZhengJiFen, ""); 
        System.out.println(myJiaShiZhengJiFenStr);
        
       

	}

}
