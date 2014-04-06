package entitypart.epf;

/**
 * Provides partial functionality and state for an entity.
 * @author David Chen
 *
 */
public abstract class Part {

	private boolean isActive = true;
	protected Entity entity;
	
	/**
	 * @return If the part will be updated.
	 */
	public final boolean isActive() {
		return isActive;
	}
	
	/**
	 * @return The entity the part is attached to.
	 */
	public final Entity getEntity() {
		return entity;
	}
	
	/**
	 * Sets the entity the part is attached to.
	 * @param entity The entity.
	 */
	public final void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	/**
	 * Initialization logic.
	 */
	public void initialize() {
	}
	
	/**
	 * Cleanup logic.
	 */
	public void cleanup() {
	}
	
	/**
	 * Update logic.
	 * @param delta Time since last update.
	 */
	public void update(float delta) {
	}
	
}
