package entitypart.items;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import entitypart.epf.Entity;
import entitypart.parts.DescriptionPart;
import entitypart.parts.HealthPart;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class HealSpell extends Spell {
	
	@XmlElement
	private float healAmount;
	
	public HealSpell() {
	}

	public HealSpell(String name, float cost, float healAmount) {
		super(name, cost);
		this.healAmount = healAmount;
	}

	/**
	 * Heals up to max health.
	 * @param target entity to heal
	 */
	public void use(Entity target) {
		HealthPart healthPart = target.get(HealthPart.class);
		float maxHealAmount = Math.min(healthPart.getMaxHealth() - healthPart.getHealth(), healAmount);
		float newHealth = healthPart.getHealth() + maxHealAmount;
		healthPart.setHealth(newHealth);
		System.out.println("\tCasting " + name + ".  Healed " + maxHealAmount + " health on "
				+ target.get(DescriptionPart.class).getName());
	}
	
}
