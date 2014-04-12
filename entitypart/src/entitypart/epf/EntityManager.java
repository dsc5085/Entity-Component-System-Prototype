package entitypart.epf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import entitypart.util.EventManager;

public class EntityManager {

	private List<Entity> entities = new ArrayList<Entity>();
	private List<Entity> entitiesToAdd = new ArrayList<Entity>();
	private List<Entity> entitiesToRemove = new ArrayList<Entity>();
	
	public EntityManager(EventManager eventManager) {
		eventManager.listen(EntityCreatedEvent.class, new EntityCreatedListener() {
			@Override
			public void created(Entity entity) {
				add(entity);
			}
		});
	}
	
	public List<Entity> getAll() {
		return new ArrayList<Entity>(entities);
	}
	
	public void add(Entity entity) {
		entity.initialize();
		entitiesToAdd.add(entity);
	}
	
	public void addAll(Collection<Entity> entities) {
		for (Entity entity : entities) {
			add(entity);
		}
	}
	
	public void remove(Entity entity) {
		entity.cleanup();
		entitiesToRemove.add(entity);
	}
	
	public void update() {
		while (!entitiesToAdd.isEmpty()) {
			entities.add(entitiesToAdd.remove(0));
		}
		
		while (!entitiesToRemove.isEmpty()) {
			entities.remove(entitiesToRemove.remove(0));
		}
	}
	
}
