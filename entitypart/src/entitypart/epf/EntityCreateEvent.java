package entitypart.epf;

import entitypart.util.Event;

/**
 * Notify that an entity is ready to be created.
 * @author David Chen
 *
 */
public class EntityCreateEvent implements Event<EntityCreateListener> {

	private Entity entity;
	
	/**
	 * Constructor.
	 * @param entity entity to be created
	 */
	public EntityCreateEvent(Entity entity) {
		this.entity = entity;
	}
	
	@Override
	public void notify(EntityCreateListener listener) {
		listener.create(entity);
	}
	
}
