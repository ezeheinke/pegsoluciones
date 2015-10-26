/**
 * 
 */
package com.pegsoluciones.blackberry.common.event;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;


/**
 *
 * @author PEG Soluciones S.A.
 * @since 1.0
 */
public class EventManager {


	private static final EventManager instance = new EventManager();


	private static final Hashtable topics = new Hashtable();


	private EventManager(){}


	/**
	 * Get the event manager instance
	 * @return
	 */
	public static EventManager getInstance() {

		return instance;
	}


	/**
	 *  Registers a given listener for a notifications
	 *  regarding the specified topic.
	 *
	 * @param anObserver Object which will receive notifications.
	 *                   Can not be null.
	 * @param aTopic Event name or notification topic to observe.
	 *               Can not be and empty string or null.
	 * @throws Runtime Exception If any of the parameters are null.
	 */
	public void addObserver(EventObserver anObserver, String aTopic) {

		if(anObserver == null) {
			throw new RuntimeException("The observer cannot be null.");
		}
		if(aTopic == null || aTopic.length() < 1) {
			throw new RuntimeException("A topic cannot be empty.");
		}
		// Find the observer list by topic
		Vector observers = (Vector) topics.get(aTopic);

		if(observers == null) {

			// The topic does not exist already. Create
			// a new one and add the observer into the
			// new observer list.
			Vector newListOfObservers = new Vector();
			newListOfObservers.addElement(anObserver);
			topics.put(aTopic, newListOfObservers);

		} else {

			if(!this._isAlreadyRegistered(anObserver, observers)){

				// The observer was not registered already
				// Add it to the list
				observers.addElement(anObserver);
			}
		}
	}

	/**
	 * Unregisters a given listener from notifications
	 * regarding all topics in which such listener is
	 * registered.
	 *
	 *  @param anObserver To be removed from the service.
	 *  @param aTopic The notification topic or event name.
	 *                If this parameter is null the observer
	 *                will be removed from all topics.
	 */
	public void removeObserver(EventObserver anObserver, String aTopic) {

		if(aTopic == null) {

			// The observer should be removed
			// from the service.
			Enumeration keys = topics.keys();
			for(String key = (String) keys.nextElement(); keys.hasMoreElements();) {
				this._removeObserverFromTopic(anObserver, key);  
			}
		} else {

			// Its has to be removed from the give
			// topic only
			this._removeObserverFromTopic(anObserver, aTopic);
		}
		// remove unusable topics
		//this._compactTopicsMap();
	}

	/**
	 * Notifies all registered listeners for the given topic.
	 *
	 * @param aTopic The notification topic or event name.
	 * @param someData Data to be passed.
	 * @throws Runtime Exception
	 */
	public void notify(String aTopic, Object someData) {

		Vector observers = (Vector) topics.get(aTopic);

		if(observers == null) {
			throw new RuntimeException("There is no observer to notify.");
		}
		// For each observer registered execute
		// the method formed as 'on' plus the topic
		for(int index = 0; index < observers.size(); index++) {
			
			EventObserver observer = (EventObserver) observers.elementAt(index);
			this._doNotify(observer, aTopic, someData);
		}
 
	}


	/**
	 * Removes the observer. The hash map is updated.
	 *
	 * @param anObserver to be removed.
	 * @param aTopic
	 */
	private void _removeObserverFromTopic(Object anObserver, String aTopic) {

		Vector observers = (Vector) topics.get(aTopic);
		observers.removeElement(anObserver);
	}


	/**
	 * This function calls a given function by name.
	 *
	 * @param object that receive the function call.
	 * @param Target The function name.
	 * @param someData to be passed as parameter.
	 * @throws Runtime Exception if the object does not understand
	 *         the method name.
	 */
	private void _doNotify(EventObserver object, String topic, Object someData) {

		try {
			object.onEvent(topic, someData);
		} catch (Exception ex) {
			System.out.println(ex);
			throw new RuntimeException("An exception has ocurred when invoking a method: " + ex);
		}
	}

	/**
	 * This function check is the object is
	 * into the list.
	 *
	 * @return true if exists. Otherwise false.
	 */
	private boolean _isAlreadyRegistered(Object object, Vector list) {

		return list.contains(object);
	}

	/**
	 * Delete topics from list which
	 * no observers are asociated
	 */
	private void _compactTopicsMap() {
		
		for(String key = (String) topics.keys().nextElement(); topics.keys().hasMoreElements(); ) {
			
			Vector observers = (Vector) topics.get(key);

			if(observers == null || observers.isEmpty()) {

				topics.remove(key);
			}
		}
	}

}
