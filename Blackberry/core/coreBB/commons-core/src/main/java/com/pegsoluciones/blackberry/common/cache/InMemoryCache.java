package com.pegsoluciones.blackberry.common.cache;

import java.util.Timer;
import java.util.TimerTask;


/**
 *  Use the CacheManager to cache the results of the target method 
 *	call.  Bypass subsequent calls to the target method each time
 *	the input arguments are the same.
 * 
 * @author PEG Soluciones S.A.
 * @since 1.0
 *
 */
public class InMemoryCache {
	
	private CacheManager cacheManager = new CacheManageInMemoryImpl();
	private Timer timer;
	private long rate = 5 * 60 * 1000;
	
	public InMemoryCache() {
	
		this.timer = new Timer();
		this.timer.scheduleAtFixedRate(new InvalidateTimerTask(), rate, rate);
	}
	
	public CacheManager getCacheManager() {
		return cacheManager;
	}
	
	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}
	
	public Object doCaching(ProceedingJoinPoint pjp) {
        
        String className    = "class"; //pjp.getTarget().getClass().getName();
        String methodName   = "method"; //pjp.getSignature().getName();
        Object[] arguments  = pjp.arguments();  
        
        //	Obtain the override value for cache region.  This value 
        //	will be null when no override is stipulated in the annotaion:
        //  String cacheName = getCacheNameFromAnnotation(pjp);
        String key = cacheManager.generateKey( className, methodName, arguments);
        Object result;      
        
        if (cacheManager.exists(key)) {
        	
        	synchronized (cacheManager) {
        		result = cacheManager.get(key);
			}
        } else {
        	// causes the underlying method to execute
        	result = pjp.proceed();
            
            // generate a key, store the
            // object in cache and return it
        	synchronized (cacheManager) {
        		cacheManager.put(key, result);
        	}
            
            return result;
        }
        
        // cache hit. Return the cached value:
        return result;
    }
	
	/**
	 * This task invalidates cach.
	 */
	private class InvalidateTimerTask extends TimerTask {
		public void run() {
			synchronized (cacheManager) {
				cacheManager.invalidateAll();
			}
		}
	}
}
