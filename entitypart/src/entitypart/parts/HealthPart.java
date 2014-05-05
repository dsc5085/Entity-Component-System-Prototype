package entitypart.parts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import entitypart.epf.Part;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class HealthPart extends Part {

	@XmlElement
	private float maxHealth;
	@XmlTransient
	private float health;
	
	public HealthPart() {
	}
	
	public HealthPart(float maxHealth) {
		this.maxHealth = maxHealth;
	}
	
	@Override
	public void initialize() {
		this.health = maxHealth;
	}
	
	public boolean isAlive() {
		return health > 0;
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
