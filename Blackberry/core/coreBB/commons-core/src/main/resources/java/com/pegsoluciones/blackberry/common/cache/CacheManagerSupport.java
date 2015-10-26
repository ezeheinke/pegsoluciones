package com.pegsoluciones.blackberry.common.cache;

/**
 *	Support class for any CacheManager Implementation which finds it useful. 
 *
 * 	@author PEG Soluciones S.A.
 *  @since 1.0
 */
abstract class CacheManagerSupport implements CacheManager {

    /**
     * Generates a key based on method name and arguments.  May be overridden.
     * Key format is:
     *     [fullyQualifiedClassName].[methodName]-[argValue1]-[argValue2]-[argValueN].
     * ...where the toString() function on each argument is called to produce 
     * values for argValue1 - argValueN.
     * @return A generated key
     */
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
