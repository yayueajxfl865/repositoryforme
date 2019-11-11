package com.okflow.middleware.redis;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * redis缓存操作
 * 
 * @author luoshanxing
 * @version 2017-06-26
 */
//@Component
public class RedisCache {
	public final static String CAHCENAME = "niitcache";// 缓存名
	public final static int CAHCETIME = 60;// 默认缓存时间 60S
	public final static int CAHCEHOUR = 60 * 60;// 默认缓存时间 1hr
	public final static int CAHCEDAY = 60 * 60 * 24;// 默认缓存时间 1Day
	public final static int CAHCEWEEK = 60 * 60 * 24 * 7;// 默认缓存时间 1week
	public final static int CAHCEMONTH = 60 * 60 * 24 * 7 * 30;// 默认缓存时间 1month
	public final static int EXPIRE = 60 * 60; // 默认缓存时间 1hr
	public final static int TRYS = 5;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	public <T> boolean putCache(String key, T obj) {
		final byte[] bkey = key.getBytes();
		final byte[] bvalue = SerializerUtil.serialize(obj);
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.setNX(bkey, bvalue);
			}
		});
		return result;
	}

	public <T> void putCacheWithExpireTime(String key, T obj, final long expireTime) {
		final byte[] bkey = key.getBytes();
		final byte[] bvalue = SerializerUtil.serialize(obj);
		redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.setEx(bkey, expireTime, bvalue);
				return true;
			}
		});
	}

	public <T> boolean putListCache(String key, List<T> objList) {
		final byte[] bkey = key.getBytes();
		final byte[] bvalue = SerializerUtil.serializeList(objList);
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.setNX(bkey, bvalue);
			}
		});
		return result;
	}

	public <T> boolean putListCacheWithExpireTime(String key, List<T> objList, final long expireTime) {
		final byte[] bkey = key.getBytes();
		final byte[] bvalue = SerializerUtil.serializeList(objList);
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.setEx(bkey, expireTime, bvalue);
				return true;
			}
		});
		return result;
	}

	public <T> T getCache(final String key, Class<T> targetClass) {
		byte[] result = redisTemplate.execute(new RedisCallback<byte[]>() {
			@Override
			public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.get(key.getBytes());
			}
		});
		if (result == null) {
			return null;
		}
		return SerializerUtil.deserialize(result, targetClass);
	}

	public <T> List<T> getListCache(final String key, Class<T> targetClass) {
		byte[] result = redisTemplate.execute(new RedisCallback<byte[]>() {
			@Override
			public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.get(key.getBytes());
			}
		});
		if (result == null) {
			return null;
		}
		return SerializerUtil.deserializeList(result, targetClass);
	}

	/**
	 * 精确删除key
	 * 
	 * @param key
	 */
	public void deleteCache(String key) {
		redisTemplate.delete(key);
	}

	/**
	 * 模糊删除key
	 * 
	 * @param pattern
	 */
	public void deleteCacheWithPattern(String pattern) {
		Set<String> keys = redisTemplate.keys(pattern);
		if (keys != null && !keys.isEmpty()) {
			redisTemplate.delete(keys);
		}
	}

	/**
	 * 清空工资标准缓存
	 */
	public void clearStandardCache() {
		deleteCacheWithPattern(CachePrefix.PREFIX_STANDARD + "*");
		deleteCacheWithPattern(CachePrefix.PREFIX_STANDARD_JF + "*");
		deleteCacheWithPattern(CachePrefix.PREFIX_STANDARD_G + "*");
		deleteCacheWithPattern(CachePrefix.PREFIX_STANDARD_GJF + "*");
	}

	/**
	 * 清空所有缓存
	 */
	public void clearCache() {
		deleteCacheWithPattern(CachePrefix.PREFIX_MENUTREE + "*");
		deleteCacheWithPattern(CachePrefix.PREFIX_OFFICE_INDEX_TREE + "*");
		deleteCacheWithPattern(CachePrefix.PREFIX_OFFICE_CATEGORY_TREE + "*");
		deleteCacheWithPattern(CachePrefix.PREFIX_OFFICEDEPARTMENTTREE + "*");
		deleteCacheWithPattern(CachePrefix.PREFIX_STANDARD + "*");
		deleteCacheWithPattern(CachePrefix.PREFIX_STANDARD_JF + "*");
		deleteCacheWithPattern(CachePrefix.PREFIX_STANDARD_G + "*");
		deleteCacheWithPattern(CachePrefix.PREFIX_STANDARD_GJF + "*");
		deleteCacheWithPattern(CachePrefix.PREFIX_BPMN + "*");
	}
}
