package entitypart.epf;

/**
 * Listener for entity remove event.
 * @author David Chen
 *
 */
public interface EntityRemoveListener {

	/**
	 * Handles the event that an entity is to be removed.
	 * @param entity entity to be removed.
	 */
	public void remove(final Entity entity);
	
}
