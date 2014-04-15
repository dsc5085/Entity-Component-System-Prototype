package entitypart.epf;

/**
 * Listener for entity create event.
 * @author David Chen
 *
 */
public interface EntityCreateListener {

	/**
	 * Handles the event that an entity is about to be created.
	 * @param entity entity to be created
	 */
	public void create(final Entity entity);
	
}
