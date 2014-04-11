package entitypart.items;

import entitypart.epf.Entity;
import entitypart.parts.DescriptionPart;
import entitypart.parts.HealthPart;

public class HealSpell extends Spell {
	
	private float healAmount;

	public HealSpell(String name, float cost, float healAmount) {
		super(name, cost);
		this.healAmount = healAmount;
	}

	public void use(Entity target) {
		HealthPart healthPart = target.get(HealthPart.class);
		float maxHealAmount = Math.min(healthPart.getMaxHealth() - healthPart.getHealth(), healAmount);
		float newHealth = healthPart.getHealth() + maxHealAmount;
		healthPart.setHealth(newHealth);
		System.out.println("\tCasting " + name + ".  Healed " + maxHealAmount + " health on "
				+ target.get(DescriptionPart.class).getName());
	}
	
}
