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
	   //登录主页面
	   String mainurl = "https://jx.122.gov.cn/"; 
	   //登录页面
	   String loginurl="https://jx.122.gov.cn/m/login";
	   //获取验证码页面
	   String yanzhengmaurl="https://jx.122.gov.cn/captcha1?nocache="+new Random().nextInt(1000);
       //post 用户名密码页面
	   String postloginurl="https://jx.122.gov.cn/user/m/login";
	   //登录成功后的页面
	   String loginsucess="https://jx.122.gov.cn/views/member";
	   //违法历史记录url
	   String breakLowHistory="https://jx.122.gov.cn/user/m/uservio/vehssuris";
	   //个人信息
	   String myInfo="https://jx.122.gov.cn/user/m/index";
	   //选择城市switch
	   String myCitySwitch="https://jx.122.gov.cn/m/index/switch";
	   //驾驶证积分URL
	   String myJiaShiZhengJiFen="https://jx.122.gov.cn/user/m/userinfo/drvendorse";
	   
	   
	   //登录到主页
       String myresult= HttpUtil.GetPageContent(mainurl, "");
       //到登录页面
       String loginresult=HttpUtil.GetPageContent(loginurl, "");
       //获取图片验证码
       HttpUtil.GetPhotoContent(yanzhengmaurl,"userpub=1");
        System.out.print("输入图片验证码:");
        Scanner scan = new Scanner(System.in);
        String read = scan.nextLine();
        System.out.println("你输入了："+read); 
        
       //post登录 
        Map<String,String> createMap = new HashMap<String,String>();  
        createMap.put("usertype","1");  
        createMap.put("systemid","main");  
        createMap.put("username","");  
        createMap.put("password",""); 
        createMap.put("captcha",read);
        String mypostresult=HttpUtil.postWithParameters(createMap, postloginurl, "");
        
        String loginsucessstr=HttpUtil.GetPageContent(loginsucess, "");
       
        //post获取违章历史记录
        Map<String,String> createMapList = new HashMap<String,String>();  
        createMapList.put("hpzl","02");  
        createMapList.put("hphm","");  
        createMapList.put("page","0");  
        createMapList.put("size","10"); 
        System.out.println("------------打印违章历史记录：-----------");
        String breakLowHistoryStr=HttpUtil.postWithParameters(createMapList, breakLowHistory, ""); 
        System.out.println(breakLowHistoryStr);
        
        
        //post获取驾驶证计分
        Map<String,String> createMapJiFen = new HashMap<String,String>();  
        createMapJiFen.put("page","0");  
        createMapJiFen.put("size","10"); 
        System.out.println("------------打印驾驶证积分记录：-----------");
        String myJiaShiZhengJiFenStr=HttpUtil.postWithParameters(createMapJiFen, myJiaShiZhengJiFen, ""); 
        System.out.println(myJiaShiZhengJiFenStr);
        
       

	}

}
