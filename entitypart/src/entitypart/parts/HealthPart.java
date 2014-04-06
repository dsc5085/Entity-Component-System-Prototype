package entitypart.parts;

import entitypart.epf.Part;

public class HealthPart extends Part {

	private float maxHealth;
	private float health;
	
	public HealthPart(float maxHealth) {
		this.maxHealth = maxHealth;
		this.health = maxHealth;
	}
	
	public float getMaxHealth() {
		return maxHealth;
	}
	
	public float getHealth() {
		return health;
	}
	
	public void setHealth(float health) {
		this.health = health;
	}
	
}
