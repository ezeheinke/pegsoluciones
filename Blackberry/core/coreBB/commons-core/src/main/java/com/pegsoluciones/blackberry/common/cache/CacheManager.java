package com.pegsoluciones.blackberry.common.cache;

/**
 *	Contract to be fulfilled by underlying Caching implementation.
 *
 * 	@author PEG Soluciones S.A.
 *  @since 1.0
 */
interface CacheManager {
	
	/**
	 *	Obtain and return value from the default cache. 
	 * @param key		Key under which to obtain the value.
	 * @return	Object value associated with the key, null if key is missing or value itself is null.
	 */
	Object get(Object key);

	
	/**
	 *	Obtain and return value from the given cache. 
	 *	Revert to the default cache when given cache
	 *	name is incorrect.
	 * @param cacheName	Name of the cache / region in which to obtain this value.  Uses the default cache when this parameter is null, empty, or invalid.
	 * @param key		Key under which to obtain the value.
	 * @return	Object value associated with the key, null if key is missing or value itself is null.
	 */
	Object get(String cacheRegion, Object key);
	
	
	/**
	 *	Determine if the given key EXISTS within the cache.  This is very
	 *	different from calling "get" and checking for a null result, as
	 *	get will return null if the key is missing OR if the value associated
	 *	with the key is null. 
	 * @param key		Key under which to obtain the value.
	 * @return	boolean true if key is present in the cache.
	 */
	boolean exists(Object key);

	
	/**
	 *	Determine if the given key EXISTS within the given cache.  This is very
	 *	different from calling "get" and checking for a null result, as
	 *	get will return null if the key is missing OR if the value associated
	 *	with the key is null. Revert to the default cache when given cache
	 *	name is incorrect.
	 * @param cacheName	Name of the cache / region in which to obtain this value.  Uses the default cache when this parameter is null, empty, or invalid.
	 * @param key		Key under which to obtain the value.
	 * @return	boolean true if key is present in the cache.
	 */
	boolean exists(String cacheRegion, Object key);
	
	
	/**
	 *	Place the given object into the default 
	 *	cache under the given key. 
	 * @param key		Key under which to store the value.
	 * @param value		Value to store
	 */
	void put(Object key, Object value);
	
	
	/**
	 *	Place the given object into the given cache 
	 *	cache under the given key.  Revert to the default 
	 *	cache when given cache name is incorrect.
	 * @param cacheName	Name of the cache / region in which to store this value.  Uses the default cache when this parameter is null, empty, or invalid.
	 * @param key		Key under which to store the value.
	 * @param value		Value to store
	 */
	void put(String cacheRegion, Object key, Object value);
	
	
	/**
	 *	Place the given object into the default cache 
	 *	under the given key for the given number of seconds.
	 * @param key		Key under which to store the value.
	 * @param value		Value to store
	 * @param ttl		Time to keep the object in the cache.  Specified in seconds.  Uses default timeToLive if value null or =< 0.
	 */
	void put(Object key, Object value, Integer ttl);
	
	
	/**
	 *	Place the given value into the given cache 
	 *	cache under the given key.    
	 * @param cacheName	Name of the cache / region in which to store this value.  Uses the default cache when this parameter is null, empty, or invalid.
	 * @param key		Key under which to store the value.
	 * @param value		Value to store
	 * @param ttl		Time to keep the object in the cache.  Specified in seconds.  Uses default timeToLive if value null or =< 0.
	 */
	void put(String cacheRegion, Object key, Object value, Integer ttl);

	
    /**
     * Generates a key based on method name and arguments.  Key format is:
     *     [fullyQualifiedClassName].[methodName]-[argValue1]-[argValue2]-[argValueN].
     * @return A generated key
     */
	String generateKey(String fullyQualifiedClassName, String methodName, Object[] argument );
	
	
	/**
	 * Invalidate all cache data. In others words this method
	 * empty the cache.
	 */
	void invalidateAll();
}
