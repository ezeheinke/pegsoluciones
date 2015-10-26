package com.pegsoluciones.blackberry.common.cache;

/**
 *
 * 	@author PEG Soluciones S.A.
 *  @since 1.0
 */
abstract class CacheManagerSupport implements CacheManager {


	public String generateKey(String fullyQualifiedClassName, String methodName, Object[] argument ) {
        StringBuffer generatedKey = new StringBuffer();
        generatedKey.append(fullyQualifiedClassName);
        generatedKey.append(".");
        generatedKey.append(methodName);
        if ( argument != null ) {
        	
        	for(int index = 0; index < argument.length; index++) {
        		generatedKey.append( "-" ); // Dashes make key a bit more human-readable
        		generatedKey.append(argument[index]);
        	}
        }
        return generatedKey.toString();
    }

}
