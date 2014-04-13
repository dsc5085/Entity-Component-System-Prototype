package entitypart.epf;

import entitypart.util.Event;

public class EntityRemoveEvent implements Event<EntityRemoveListener> {

	private Entity entity;
	
	public EntityRemoveEvent(Entity entity) {
		this.entity = entity;
	}
	
	@Override
	public void notify(EntityRemoveListener listener) {
		listener.remove(entity);
	}

}
