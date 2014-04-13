package entitypart.epf;

import entitypart.util.Event;

public class EntityCreateEvent implements Event<EntityCreateListener> {

	private Entity entity;
	
	public EntityCreateEvent(Entity entity) {
		this.entity = entity;
	}
	
	@Override
	public void notify(EntityCreateListener listener) {
		listener.create(entity);
	}
	
}
