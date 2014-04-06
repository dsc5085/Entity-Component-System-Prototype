package entitypart.items;

import entitypart.epf.Entity;
import entitypart.parts.DescriptionPart;
import entitypart.parts.FlyingPart;
import entitypart.parts.HealthPart;

public class AttackItem extends Item {
	
	private float minDamage;
	private float maxDamage;
	private AttackRange attackRange;
	
	public AttackItem(String name, float minDamage, float maxDamage, AttackRange attackRange) {
		super(name);
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
		this.attackRange = attackRange;
	}

	@Override
	public ItemType getItemType() {
		return ItemType.OFFENSIVE;
	}

	@Override
	public void use(Entity target) {
		boolean targetFlying = target.has(FlyingPart.class);
		if (!targetFlying || (targetFlying && attackRange == AttackRange.FAR)) {
			HealthPart healthPart = target.get(HealthPart.class);
			float damage = minDamage + (int)(Math.random() * (maxDamage - minDamage) + 1); 
			float newHealth = healthPart.getHealth() - damage;
			target.get(HealthPart.class).setHealth(newHealth);
			System.out.println(damage + " damage dealt to " + target.get(DescriptionPart.class).getName()
					+ ".  New health is " + newHealth);
		}
	}
	
}
