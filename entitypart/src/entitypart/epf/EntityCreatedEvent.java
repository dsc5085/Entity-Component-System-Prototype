package entitypart.epf;

import entitypart.util.Event;

public class EntityCreatedEvent implements Event<EntityCreatedListener> {

	private Entity entity;
	
	public EntityCreatedEvent(Entity entity) {
		this.entity = entity;
	}
	
	@Override
	public void notify(EntityCreatedListener listener) {
		listener.created(entity);
	}
	
}
