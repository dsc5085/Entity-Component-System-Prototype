package entitypart.parts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import entitypart.epf.Part;

/**
 * Restores entity health and mana each timestep.
 * @author David Chen
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class RestorePart extends Part {

	@XmlElement
	private float healthRestoreRate;
	@XmlElement
	private float manaRestoreRate;
	
	public RestorePart() {
	}

	public RestorePart(float healthRestoreRate, float manaRestoreRate) {
		this.healthRestoreRate = healthRestoreRate;
		this.manaRestoreRate = manaRestoreRate;
	}
	
	@Override
	public void update(float dt) {
		HealthPart healthPart = getEntity().get(HealthPart.class);
		float newHealth = calculateRestoredValue(healthPart.getMaxHealth(), healthPart.getHealth(), healthRestoreRate * dt);
		healthPart.setHealth(newHealth);
		
		ManaPart manaPart = getEntity().get(ManaPart.class);
		float newMana = calculateRestoredValue(manaPart.getMaxMana(), manaPart.getMana(), manaRestoreRate * dt);
		manaPart.setMana(newMana);
	}
	
	private float calculateRestoredValue(float maxValue, float currentValue, float restoreRate) {
		float manaRestoreAmount = maxValue * restoreRate;
		float maxManaRestoreAmount = Math.min(maxValue - currentValue, manaRestoreAmount);
		float newMana = currentValue + maxManaRestoreAmount;
		return newMana;
	}
	
}
