package org.redis.framework;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository(value = "redisService")
public class RedisServiceImpl implements RedisService {

	private static String redisCode = "utf-8";

	private RedisServiceImpl() {

	}

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	@Override
	public long del(String... keys) {
		return (long) redisTemplate.execute(new RedisCallback() {
			public Long doInRedis(RedisConnection connection) {
				long result = 0;
				for (int i = 0; i < keys.length; i++) {
					result = connection.del(keys[i].getBytes());
				}
				return result;
			}
		});
	}

	@Override
	public void set(byte[] key, byte[] value, long liveTime) {
		this.redisTemplate.execute(new RedisCallback() {

			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				// TODO Auto-generated method stub
				connection.set(key, value);
				if (liveTime > 0) {
					connection.expire(key, liveTime);
				}
				return 1L;
			}
		});

	}

	@Override
	public void set(String key, String value) {

		this.set(key.getBytes(), value.getBytes());

	}

	@Override
	public void set(byte[] key, byte[] value) {
		this.set(key, value, 0L);

	}

	@Override
	public String get(String key) {
		return (String) redisTemplate.execute(new RedisCallback() {
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				try {
					return new String(connection.get(key.getBytes()), redisCode);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				return "";
			}
		});

	}

	@Override
	public Set Keys(String pattern) {
		return redisTemplate.keys(pattern);
	}

	@Override
	public boolean exists(String key) {
		return (boolean) redisTemplate.execute(new RedisCallback() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				// TODO Auto-generated method stub
				return connection.exists(key.getBytes());
			}
		});

	}

	@Override
	public String flushDB() {
		return (String) this.redisTemplate.execute(new RedisCallback() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.flushDb();
				return "OK";
			}
		});
	}

	@Override
	public long dbSize() {
		return (long) redisTemplate.execute(new RedisCallback() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.dbSize();
			}
		});
	}

	@Override
	public String ping() {
		return (String) redisTemplate.execute(new RedisCallback() {
			public String doInRedis(RedisConnection connection) throws DataAccessException {

				return connection.ping();
			}
		});
	}

	@Override
	public Object setCacheList(String key, List<Object> dataList) {

		ListOperations<Object, Object> listOperation = redisTemplate.opsForList();
		if (null != dataList) {
			int size = dataList.size();
			for (int i = 0; i < size; i++) {
				listOperation.rightPush(key, dataList.get(i));
			}
		}
		return listOperation;
	}

	@Override
	public List<Object> getCacheList(String key) {
		List<Object> dataList = new ArrayList<Object>();
		ListOperations<Object, Object> listOperation = redisTemplate.opsForList();
		Long size = listOperation.size(key);

		for (int i = 0; i < size; i++) {
			dataList.add(listOperation.leftPop(key));
		}
		return dataList;
	}

	@Override
	public List<Object> range(String key, long start, long end) {
		ListOperations<Object, Object> listOperation = redisTemplate.opsForList();
		return listOperation.range(key, start, end);
	}

	@Override
	public void listSet(String key, int index, Object obj) {
		redisTemplate.opsForList().set(key, index, obj);
	}

	@Override
	public long leftPush(String key, Object obj) {
		return redisTemplate.opsForList().leftPush(key, obj);
	}

	@Override
	public long rightPush(String key, Object obj) {
		return redisTemplate.opsForList().rightPush(key, obj);
	}

	@Override
	public void trim(String key, int start, int end) {
		redisTemplate.opsForList().trim(key, start, end);

	}

	@Override
	public long remove(String key, long i, Object obj) {
		return redisTemplate.opsForList().remove(key, i, obj);
	}

	@Override
	public BoundSetOperations<Object, Object> setCacheSet(String key, Set<Object> dataSet) {
		BoundSetOperations<Object, Object> setOperation = redisTemplate.boundSetOps(key);
		/*
		 * T[] t = (T[]) dataSet.toArray(); setOperation.add(t);
		 */

		Iterator<Object> it = dataSet.iterator();
		while (it.hasNext()) {
			setOperation.add(it.next());
		}
		return setOperation;
	}

	@Override
	public Set<Object> getCacheSet(String key) {
		Set<Object> dataSet = new HashSet<Object>();
		BoundSetOperations<Object, Object> operation = redisTemplate.boundSetOps(key);

		Long size = operation.size();
		for (int i = 0; i < size; i++) {
			dataSet.add(operation.pop());
		}
		return dataSet;
	}

	@Override
	public int setCacheMap(String key, Map<String, Object> dataMap) {
		if (null != dataMap) {
			HashOperations<Object, Object, Object> hashOperations = redisTemplate.opsForHash();
			for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
				/*
				 * System.out.println("Key = " + entry.getKey() + ", Value = " +
				 * entry.getValue());
				 */
				if (hashOperations != null) {
					hashOperations.put(key, entry.getKey(), entry.getValue());
				} else {
					return 0;
				}
			}
		} else {
			return 0;
		}
		return dataMap.size();
	}

	@Override
	public Map<Object, Object> getCacheMap(String key) {
		Map<Object, Object> map = redisTemplate.opsForHash().entries(key);
		/* Map<String, T> map = hashOperation.entries(key); */
		return map;
	}

	@Override
	public void setCacheIntegerMap(String key, Map<Integer, Object> dataMap) {
		HashOperations<Object, Object, Object> hashOperations = redisTemplate.opsForHash();
		if (null != dataMap) {
			for (Map.Entry<Integer, Object> entry : dataMap.entrySet()) {
				/*
				 * System.out.println("Key = " + entry.getKey() + ", Value = " +
				 * entry.getValue());
				 */
				hashOperations.put(key, entry.getKey(), entry.getValue());
			}

		}

	}

	@Override
	public Map<Object, Object> getCacheIntegerMap(String key) {
		Map<Object, Object> map = redisTemplate.opsForHash().entries(key);
		/* Map<String, T> map = hashOperation.entries(key); */
		return map;
	}

	@Override
	public long deleteMap(String key) {
		redisTemplate.setEnableTransactionSupport(true);
		return redisTemplate.opsForHash().delete(key);
	}

	@Override
	public boolean expire(String key, long time, TimeUnit unit) {
		return redisTemplate.expire(key, time, unit);
	}

	@Override
	public long increment(String key, long step) {
		return redisTemplate.opsForValue().increment(key, step);
	}

	@Override
	public Long listSize(String key) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForList().size(key);
	}

}
