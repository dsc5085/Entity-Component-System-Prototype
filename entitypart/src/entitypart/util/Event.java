package entitypart.util;

/**
 * Notifies that an event occured.
 * @author David Chen
 *
 * @param <T> the listener interface
 */
public interface Event<T> {
	
	/**
	 * Handler for event listener.
	 * @param listener
	 */
	public void notify(final T listener);
	
}
