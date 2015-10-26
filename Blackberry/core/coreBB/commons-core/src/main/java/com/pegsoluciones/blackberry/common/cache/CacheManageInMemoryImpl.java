
package com.pegsoluciones.blackberry.common.cache;

import java.util.Hashtable;



/**
 * In memory implementation of cache for {@link CacheManager}
 * This is a very straightforward implementation based on a simple hashmap.
 * Does not provided victim selection as well as time to live. Does not provided
 * a way to limit the cache size either ;).
 * 
 * @author PEG Soluciones S.A.
 * @since 1.0
 */
class CacheManageInMemoryImpl extends CacheManagerSupport implements CacheManager {

	private Hashtable cache = new Hashtable(10);
	
	public CacheManageInMemoryImpl() {
	}
	
	

	public Object get(Object key) {
		return this.cache.get(key);
	}


	public Object get(String cacheRegion, Object key) {
		throw new RuntimeException("Method not implemented.");
	}


	public boolean exists(Object key) {
		return this.cache.containsKey(key);
	}


	public boolean exists(String cacheRegion, Object key) {
		throw new RuntimeException("Method not implemented.");
	}


	public void put(Object key, Object value) {
		this.cache.put(key, value);
	}


	public void put(String cacheRegion, Object key, Object value) {
		throw new RuntimeException("Method not implemented.");
	}


	public void put(Object key, Object value, Integer ttl) {
		throw new RuntimeException("Method not implemented.");

	}

	public void put(String cacheRegion, Object key, Object value, Integer ttl) {
		throw new RuntimeException("Method not implemented.");
	}

	public String generateKey(String fullyQualifiedClassName,
			String methodName, Object[] argument) {
		return super.generateKey(fullyQualifiedClassName, methodName, argument);
	}


	public void invalidateAll() {
		this.cache.clear();
	}
	
}
