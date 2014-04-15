package entitypart.epf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import entitypart.util.EventManager;

/**
 * Manages a group of entities.  Provides accessing, removing, and adding entities.
 * @author David Chen
 *
 */
public class EntityManager {

	private List<Entity> entities = new ArrayList<Entity>();
	private List<Entity> entitiesToAdd = new ArrayList<Entity>();
	private List<Entity> entitiesToRemove = new ArrayList<Entity>();
	
	/**
	 * Constructor.
	 * @param eventManager the event manager that publishes entity create and entity remove events.
	 */
	public EntityManager(EventManager eventManager) {
		eventManager.listen(EntityCreateEvent.class, new EntityCreateListener() {
			@Override
			public void create(Entity entity) {
				add(entity);
			}
		});
		
		eventManager.listen(EntityRemoveEvent.class, new EntityRemoveListener() {
			@Override
			public void remove(Entity entity) {
				remove(entity);
			}
		});
	}
	
	/**
	 * @return all managed entities
	 */
	public List<Entity> getAll() {
		return new ArrayList<Entity>(entities);
	}
	
	/**
	 * Adds and manages an entity on the next call to update.
	 * @param entity the entity to add and manage
	 */
	public void add(Entity entity) {
		entity.initialize();
		entitiesToAdd.add(entity);
	}
	
	/**
	 * Adds the entities in the passed in collection on the next call to update.
	 * @param entities entities to add and manage
	 */
	public void addAll(Collection<Entity> entities) {
		for (Entity entity : entities) {
			add(entity);
		}
	}
	
	/**
	 * Removes an entity on the next call to update.
	 * @param entity entity to remove
	 */
	public void remove(Entity entity) {
		entity.cleanup();
		entitiesToRemove.add(entity);
	}

	/**
	 * Adds and removes entities passed into the add or remove methods.
	 */
	public void update() {
		while (!entitiesToAdd.isEmpty()) {
			entities.add(entitiesToAdd.remove(0));
		}
		
		while (!entitiesToRemove.isEmpty()) {
			entities.remove(entitiesToRemove.remove(0));
		}
	}
	
}
