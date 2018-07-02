package org.redis.framework;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.BoundSetOperations;

public interface RedisService {

	/**
	 * 通过key进行删除
	 * 
	 * @param keys
	 * @return
	 */
	public abstract long del(String... keys);

	/**
	 * 通过添加 key value 并且设置存活时间
	 * 
	 * @param key
	 * @param value
	 * @param liveTime
	 */
	public abstract void set(byte[] key, byte[] value, long liveTime);

	/**
	 * 添加key value
	 * 
	 * @param key
	 * @param value
	 */
	public abstract void set(String key, String value);

	/**
	 * 添加key value （字节）序列化
	 * 
	 * @param key
	 * @param value
	 */
	public abstract void set(byte[] key, byte[] value);

	/**
	 * 通过key来获取redis的值
	 * 
	 * @param key
	 * @return
	 */
	public abstract String get(String key);

	/**
	 * 通过正则匹配Keys
	 * 
	 * @param pattern
	 */
	public abstract Set Keys(String pattern);

	/**
	 * 检查key是否已经存在
	 * 
	 * @param key
	 * @return
	 */
	public abstract boolean exists(String key);

	/**
	 * 清空redis所有数据
	 * 
	 * @return
	 */
	public abstract String flushDB();

	/**
	 * 查看redis里有多少数据
	 * 
	 * @return
	 */
	public abstract long dbSize();

	/**
	 * 检查连接是否成功
	 * 
	 * @return
	 */
	public abstract String ping();

	
	/**
	 * 缓存List数据
	 * @param <T>
	 * @param key         缓存的键值
	 * @param dataList    待缓存的List数据
	 * @return
	 */
	public abstract Object setCacheList(String key,List<Object>dataList);
	
	/**
	 * 获得缓存的list对象
	 * @param key 缓存的键值
	 * @return
	 */
	public abstract List<Object> getCacheList(String key);
	
	/**
	 * 获取缓存的list对象
	 * @param key   
	 * @param start
	 * @param end
	 * @return
	 */
	public abstract List<Object> range(String key,long start,long end);
	
    /** 
     * list集合长度
     * @param key 
     * @return 
     */ 
	 public abstract Long listSize(String key);
	
	 
	/**
	 * 覆盖list中指定位置的值
	 * @param key
	 * @param index
	 * @param obj
	 */
	public abstract  void listSet(String key,int index,Object obj);
	
	/**
	 * 向list尾部追加记录
	 * @param key
	 * @param obj
	 * @return
	 */
	public abstract long leftPush(String key,Object obj);
	
	
	/**
	 * 向list头部追加记录
	 * @param key
	 * @param obj
	 * @return
	 */
	public abstract long rightPush(String key,Object obj);
	
	
	/**
	 * 算是删除，保留start与end之间的记录
	 * @param key
	 * @param start
	 * @param end
	 */
	public abstract void trim(String key,int start,int end);
	
	/**
	 * 删除list中的C条记录，被删除的值为value
	 * @param key 
	 * @param i c 要删除的数量，如果为负数则从List中的尾部检查并删除符合的记录
	 * @param obj 要匹配的值
	 * @return    返回删除后的list中的记录
	 */
	public abstract long remove(String key,long i,Object obj); 
	
	/**
	 * 缓存set			    
	 * @param key      缓存兼职
	 * @param dataSet  缓存的数据
	 * @return         返回缓存数据的对象
	 */
	public abstract BoundSetOperations<Object,Object> setCacheSet(String key,Set<Object> dataSet);
	
	
	/**
	 * 获得缓存的set
	 * @param key
	 * @return
	 */
	public abstract Set<Object> getCacheSet(String key/*,BoundSetOperations<String,T> operation*/);
	
	
	/**
	 * 缓存map Map<String,Object>
	 * @param key
	 * @param dataMap
	 * @return
	 */
	public abstract int  setCacheMap(String key,Map<String,Object> dataMap);
	
	
	/**
     * 获得缓存的Map
     * @param key
     * @param hashOperation
     * @return
     */
	public Map<Object, Object> getCacheMap(String key/*,HashOperations<String,String,T> hashOperation*/);
	
	
	/**
     * 缓存Map Map<Integer, Object>
     * @param key
     * @param dataMap
     * @return
     */
	  public void setCacheIntegerMap(String key,Map<Integer, Object> dataMap);
	
	
	  /**
	     * 获得缓存的Map
	     * @param key
	     * @param hashOperation
	     * @return
	     */
	  public Map<Object, Object> getCacheIntegerMap(String key/*,HashOperations<String,String,T> hashOperation*/);
	  
	  
	  /**
	     * 从hash中删除指定的存储
	     * 
	     * @param String
	     * @return 状态码，1成功，0失败
	     * */
	  public long deleteMap(String key);
	  
	  /**
	      * 设置过期时间
	      * @param key
	      * @param time
	      * @param unit
	      * @return
	      */
	  public boolean expire(String key, long time, TimeUnit unit);
	  
	  
	  
	  /**
	     * increment +1
	     * @param key
	     * @param step
	     * @return
	     */
	  public long increment(String key, long step);
	  
	  
	  
	  
	  
	  
}
