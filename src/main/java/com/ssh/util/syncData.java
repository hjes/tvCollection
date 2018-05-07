package com.ssh.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.ssh.entity.OnlineBean;
import com.ssh.entity.TvBean;


public class syncData {
	public void syncDatas() {
		System.out.println("数据同步开始");
		try {
			List<TvBean> tvlist = initXpath();
			List<OnlineBean> list=new ArrayList<OnlineBean>();
			if(tvlist.size()>0){
				for (TvBean tvBean : tvlist) {
					List<OnlineBean> tmplist=parseHtml(tvBean);
					list.addAll(tmplist);
				}
			}
			 Collections.sort(list, new Comparator<OnlineBean>(){
		            /*
		             * int compare(Person p1, Person p2) 返回一个基本类型的整型，
		             * 返回负数表示：p1 小于p2，
		             * 返回0 表示：p1和p2相等，
		             * 返回正数表示：p1大于p2
		             */
		            public int compare(OnlineBean p1, OnlineBean p2) {
		                //按照Person的年龄进行升序排列
		                if(p1.getNumInteger() > p2.getNumInteger()){
		                    return -1;
		                }
		                if(p1.getNumInteger() == p2.getNumInteger()){
		                    return 0;
		                }
		                return 1;
		            }
		        });
			   JedisUtils.delkeyObject("list");
			   JedisUtils.setObject("list", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("数据同步结束");
	}
	public static void main(String[] args) {
		syncData s=new syncData();
		s.initXpath();
	}
	public List<OnlineBean> parseHtml(TvBean tv) throws Exception{
		String lhtml=getHtml(tv.getBaseurl());
		 Document dom = Jsoup.parse(lhtml);
		 List<OnlineBean> list=new ArrayList<OnlineBean>();
		   Elements doms = dom.getElementsByAttribute(tv.getLixpath());
		   for (Element element : doms) {
			   Elements href = element.select(tv.getHrefpath());
			   Elements tittle = element.select(tv.getTittlepath());
			   Elements name = element.select(tv.getNamepath());
			   Elements num = element.select(tv.getNumpath());
			   Elements pic = element.getElementsByAttribute(tv.getPicpath());
			   OnlineBean bean=new OnlineBean();
			   bean.setRoot(tv.getRoot());
			   bean.setRootHref(tv.getRoothref());
			   bean.setRoomTittle(tittle.get(0).html());
			   bean.setName(name.get(0).html());
			   bean.setNum(num.get(0).html());
			   if(bean.getNum().indexOf("万")!=-1){
				   String nums=bean.getNum().substring(0, bean.getNum().length()-1);
				   double n=Double.parseDouble(nums)*10000;
				   bean.setNumInteger(n);
			   }else{
				   bean.setNumInteger(Double.parseDouble(bean.getNum()));
			   }	
			   bean.setPicSrc(pic.get(0).attr("data-original"));
			   bean.setRoomID(href.get(0).attr("href"));
			   list.add(bean);
		   } 
		   return list;
	}
	public  String getHtml(String baseurl) throws Exception{
		StringBuilder sb=new StringBuilder();
		String url = "http://www.douyu.com/directory/game/yz"; 
		@SuppressWarnings("resource")
		HttpClient client = new SSLClient();  
        HttpGet request = new HttpGet(url);  
        
        HttpResponse response = client.execute(request);  
        System.out.println("Response Code: " +  
        response.getStatusLine().getStatusCode());  
      
        BufferedReader rd = new BufferedReader(  
            new InputStreamReader(response.getEntity().getContent(),"utf-8"));  
        String line = "";  
        while((line = rd.readLine()) != null) {  
        
        sb.append(line);
        }  
        return sb.toString();
	}
	public  List<TvBean> initXpath(){
		 Properties props = new Properties();
		 List<TvBean> tvlist=new ArrayList<TvBean>();
		 TvBean tv;
	            try {  
	                props=PropertiesLoaderUtils.loadAllProperties("xpath.properties");  
	                String baseurl=props.getProperty("baseurl");
	                String hrefpath=props.getProperty("hrefpath");
	                String numpath=props.getProperty("numpath");
	                String picpath=props.getProperty("picpath");
	                String namepath=props.getProperty("namepath");
	                String lixpath=props.getProperty("lixpath");
	                String tittlepath=props.getProperty("tittlepath");
	                String roothref=props.getProperty("roothref");
	                String root=props.getProperty("root");
	                if(baseurl==null||baseurl.equals("")){
	                	return tvlist;
	                }
	                if (baseurl.indexOf(",")!=-1) {
	                	String[] baseurls = baseurl.split(",");
	                	String[] hrefpaths = hrefpath.split(",");
	                	String[] numpaths = numpath.split(",");
	                	String[] picpaths = picpath.split(",");
	                	String[] namepaths = namepath.split(",");
	                	String[] lixpaths = lixpath.split(",");
	                	String[] tittlepaths = tittlepath.split(",");
	                	String[] roothrefs = roothref.split(",");
	                	String[] roots = root.split(",");
	                	 for (int i = 0; i < baseurls.length; i++) {
	                		 tv=new TvBean();
							 tv.setBaseurl(baseurls[i]);
							 tv.setHrefpath(hrefpaths[i]);
							 tv.setLixpath(lixpaths[i]);
							 tv.setNamepath(namepaths[i]);
							 tv.setNumpath(numpaths[i]);
							 tv.setPicpath(picpaths[i]);
							 tv.setRoot(roots[i]);
							 tv.setRoothref(roothrefs[i]);
							 tv.setTittlepath(tittlepaths[i]);
							 tvlist.add(tv);
						}
					}else{
						tv=new TvBean();
						 tv.setBaseurl(baseurl);
						 tv.setHrefpath(hrefpath);
						 tv.setLixpath(lixpath);
						 tv.setNamepath(namepath);
						 tv.setNumpath(numpath);
						 tv.setPicpath(picpath);
						 tv.setRoot(root);
						 tv.setRoothref(roothref);
						 tv.setTittlepath(tittlepath);
						 tvlist.add(tv);
					}
	            } catch (IOException e) {  
	                System.out.println(e.getMessage());  
	            }  
	            return tvlist;
	}
	
}
