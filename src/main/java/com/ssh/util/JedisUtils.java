package com.ssh.util;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtils {

	    //Redis������IP
	    private static String ADDR = "192.168.0.123";

	    //Redis�Ķ˿ں�
	    private static int PORT = 6379;

	    //��������

	    //��������ʵ���������Ŀ��Ĭ��ֵΪ8��
	    //�����ֵΪ-1�����ʾ�����ƣ����pool�Ѿ�������maxActive��jedisʵ�������ʱpool��״̬Ϊexhausted(�ľ�)��
	    private static int MAX_ACTIVE = 1024;

	    //����һ��pool����ж��ٸ�״̬Ϊidle(���е�)��jedisʵ����Ĭ��ֵҲ��8��
	    private static int MAX_IDLE = 200;

	    //�ȴ��������ӵ����ʱ�䣬��λ���룬Ĭ��ֵΪ-1����ʾ������ʱ����������ȴ�ʱ�䣬��ֱ���׳�JedisConnectionException��
	    private static int MAX_WAIT = 10000;

	    private static int TIMEOUT = 10000;

	    //��borrowһ��jedisʵ��ʱ���Ƿ���ǰ����validate���������Ϊtrue����õ���jedisʵ�����ǿ��õģ�
	    private static boolean TEST_ON_BORROW = true;

	    private static JedisPool jedisPool = null;

	    /**
	     * ��ʼ��Redis���ӳ�
	     */
	    static {
	        try {
	            JedisPoolConfig config = new JedisPoolConfig();
	            config.setMaxTotal(MAX_ACTIVE);
	            config.setMaxIdle(MAX_IDLE);
	            config.setMaxWaitMillis(MAX_WAIT);
	            config.setTestOnBorrow(TEST_ON_BORROW);
	            jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    /**
	     * ��ȡJedisʵ��
	     * @return
	     */
	    public synchronized static Jedis getJedis() {
	        try {
	            if (jedisPool != null) {
	                Jedis resource = jedisPool.getResource();
	                return resource;
	            } else {
	                return null;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }

	    /**
	     * �ͷ�jedis��Դ
	     * @param jedis
	     */
	    public static void returnResource(final Jedis jedis) {
	        if (jedis != null) {
	            jedisPool.returnResource(jedis);
	        }
	    }

	    /**
	     * ��ȡredis��ֵ-object
	     * 
	     * @param key
	     * @return
	     */
	    public static Object getObject(String key) {
	        Jedis jedis = null;
	        try {
	            jedis = jedisPool.getResource();
	            byte[] bytes = jedis.get(key.getBytes());
	            if(bytes!=null) {
	                return SerializableUtil.unserializable(bytes);
	            }
	        } catch (Exception e) {
	            System.out.println("getObject��ȡredis��ֵ�쳣:key=" + key + " cause:" + e.getMessage());
	        } finally {
	            jedis.close();
	        }
	        return null;
	    }

	    /**
	     * ����redis��ֵ-object
	     * @param key
	     * @param value
	     * @param expiretime
	     * @return
	     */
	    public static String setObject(String key, Object value) {
	        Jedis jedis = null;
	        try {
	            jedis = jedisPool.getResource();
	            return jedis.set(key.getBytes(), SerializableUtil.serializable(value));
	        } catch (Exception e) {
	        	 System.out.println("setObject����redis��ֵ�쳣:key=" + key + " value=" + value + " cause:" + e.getMessage());
	            return null;
	        } finally {
	            if(jedis != null)
	            {
	                jedis.close();
	            }
	        }
	    }
	    
	    public static void setList(String key,List<Object> list){
	    	Jedis jedis = null;
	        try {
	            jedis = jedisPool.getResource();
	            for (Object object : list) {
	            	jedis.lpush(key.getBytes(), SerializableUtil.serializable(object));
				}
	        } catch (Exception e) {
	        	 System.out.println("setObject����redis��ֵ�쳣:key=" + key + " value=" + list + " cause:" + e.getMessage());
	        } finally {
	            if(jedis != null)
	            {
	                jedis.close();
	            }
	        }
	    }
	   
	    
	    public static String setObject(String key, Object value,int expiretime) {
	        String result = "";
	        Jedis jedis = null;
	        try {
	            jedis = jedisPool.getResource();
	            result = jedis.set(key.getBytes(), SerializableUtil.serializable(value));
	            if(result.equals("OK")) {
	                jedis.expire(key.getBytes(), expiretime);
	            }
	            return result;
	        } catch (Exception e) {
	        	System.out.println("setObject����redis��ֵ�쳣:key=" + key + " value=" + value + " cause:" + e.getMessage());
	        } finally {
	            if(jedis != null)
	            {
	                jedis.close();
	            }
	        }
	        return result;
	    }

	    /**
	     * ɾ��key
	     */
	    public static Long delkeyObject(String key) {
	        Jedis jedis = null;
	        try {
	            jedis = jedisPool.getResource();
	            return jedis.del(key.getBytes());
	        }catch(Exception e) {
	            e.printStackTrace();
	            return null;
	        }finally{
	            if(jedis != null)
	            {
	                jedis.close();
	            }
	        }
	    }

	    public static Boolean existsObject(String key) {
	        Jedis jedis = null;
	        try {
	            jedis = jedisPool.getResource();
	            return jedis.exists(key.getBytes());
	        }catch(Exception e) {
	            e.printStackTrace();
	            return null;
	        }finally{
	            if(jedis != null)
	            {
	                jedis.close();
	            }
	        }
	    }
	    public static void main(String[] args) {
	    	String res = setObject("test", "111");
	    	System.out.println(res);
	    	String r=(String) getObject("test");
	    	System.out.println(r);
		}
}
