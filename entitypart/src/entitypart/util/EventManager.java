package entitypart.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Modified from original implementation at 
 * http://stackoverflow.com/questions/937302/simple-java-message-dispatching-system
 *
 */
public final class EventManager {

	/** mapping of class events to active listeners **/
	@SuppressWarnings("rawtypes")
	private final Map<Class, Collection> eventListenerMap = new HashMap<Class, Collection>(10);

    /** Add a listener to an event class **/
    public <T> void listen(Class<? extends Event<T>> evtClass, T listener) {
    	final Collection<T> listeners = listenersOf(evtClass);
	    synchronized(listeners) {
	    	if (!listeners.contains(listener)) {
	    		listeners.add(listener);
	        }
	    }
    }

    /** Stop sending an event class to a given listener **/
    public <T> void mute(Class<? extends Event<T>> evtClass, T listener) {
    	final Collection<T> listeners = listenersOf(evtClass);
    	synchronized(listeners) {
    		listeners.remove(listener);
    	}
    }

    /** Notify a new event to registered listeners of this event class **/
    public <T> void notify(final Event<T> evt) {
    	@SuppressWarnings("unchecked")
        Class<Event<T>> evtClass = (Class<Event<T>>) evt.getClass();

        for (T listener : listenersOf(evtClass)) {
        	evt.notify(listener);
        }
    }

    /** Gets listeners for a given event class **/
    private <T> Collection<T> listenersOf(Class<? extends Event<T>> evtClass) {
    	synchronized (eventListenerMap) {
    		@SuppressWarnings("unchecked")
	        final Collection<T> existing = eventListenerMap.get(evtClass);
	        if (existing != null) {
	        	return existing;
	        }
	
	        final Collection<T> emptyList = new ArrayList<T>(5);
	        eventListenerMap.put(evtClass, emptyList);
	        return emptyList;
        }
    }

}