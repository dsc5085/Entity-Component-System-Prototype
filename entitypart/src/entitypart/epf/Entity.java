package entitypart.epf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Made up of parts that provide functionality and state for the entity.
 * There can only be one of each part type attached.
 * @author David Chen
 *
 */
public class Entity {
	
	private boolean isInitialized = false;
	private boolean isActive = false;
	private Map<Class<? extends Part>, Part> parts = new HashMap<Class<? extends Part>, Part>();
	private List<Part> partsToAdd = new ArrayList<Part>();
	private List<Class<? extends Part>> partsToRemove = new ArrayList<Class<? extends Part>>();
	
	/**
	 * @return If the entity will be updated.
	 */
	public boolean isActive() {
		return isActive;
	}
	
	/**
	 * Sets the entity to be active or inactive.
	 * @param isActive True to make the entity active.  False to make it inactive.
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	/**
	 * @param partClass The class of the part to check.
	 * @return If there is a part of type T attached to the entity.
	 */
	public <T extends Part> boolean has(Class<T> partClass) {
		return parts.containsKey(partClass);
	}
	
	/**
	 * @param partClass The class of the part to get.
	 * @return The part attached to the entity of type T.
	 * @throws IllegalArgumentException If there is no part of type T attached to the entity.
	 */
	@SuppressWarnings("unchecked")
	public <T extends Part> T get(Class<T> partClass) {
		if (!has(partClass)) {
			throw new IllegalArgumentException("Part of type " + partClass.getName() + " could not be found.");
		}
		return (T)parts.get(partClass);
	}

	/**
	 * @return A list of all parts the entity is composed of.
	 */
	public List<Part> getAll() {
		return new ArrayList<Part>(parts.values());
	}
	
	/**
	 * Adds a part.
	 * @param part The part.
	 */
	public void attach(Part part) {
		if (has(part.getClass())) {
			throw new IllegalArgumentException("Part of type " + part.getClass().getName() + " already exists.");
		}
		
		parts.put(part.getClass(), part);
		part.setEntity(this);
		
		if (isInitialized) {
			part.initialize();
		}
	}
	
	/**
	 * If a part of the same type already exists, removes the existing part.  Adds the passed in part.
	 * @param part The part.
	 */
	public void replace(Part part) {
		if (has(part.getClass())) {
			detach(part.getClass());
		}
		
		if (isInitialized) {
			partsToAdd.add(part);
		}
		else {
			attach(part);
		}
	}
	
	/**
	 * Removes a part of type T if it exists.
	 * @param partClass The class of the part to remove.
	 */
	public <T extends Part> void detach(Class<T> partClass) {
		if (has(partClass) && !partsToRemove.contains(partClass)) {
			partsToRemove.add(partClass);
		}
	}
	
	/**
	 * Makes the entity active.  Initializes attached parts.
	 */
	public void initialize() {
		isInitialized = true;
		isActive = true;
		for (Part part : parts.values()) {
			part.initialize();
		}
	}
	
	/**
	 * Makes the entity inactive.  Cleans up attached parts.
	 */
	public void cleanup() {
		isActive = false;
		for (Part part : parts.values()) {
			part.cleanup();
		}
	}
	
	/**
	 * Updates attached parts.  Removes detached parts and adds newly attached parts.
	 * @param delta Time passed since the last update.
	 */
	public void update(float delta) {
		for (Part part : parts.values()) {
			if (part.isActive()) {
				part.update(delta);
			}
		}
		
		while (!partsToRemove.isEmpty()) {
			remove(partsToRemove.remove(0));
		}
		
		while (!partsToAdd.isEmpty()) {
			attach(partsToAdd.remove(0));
		}
	}
	
	private <T extends Part> void remove(Class<T> partClass) {
		if (!has(partClass)) {
			throw new IllegalArgumentException("Part of type " + partClass.getName() + " could not be found.");
		}
		parts.get(partClass).cleanup();
		parts.remove(partClass);
	}
	
}
