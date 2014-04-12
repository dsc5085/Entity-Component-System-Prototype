package entitypart.items;

import entitypart.epf.Entity;
import entitypart.epf.EntityCreatedEvent;
import entitypart.parts.HealthPart;
import entitypart.util.EventManager;

public class SummonSpell extends Spell {

	private Entity summon;
	
	public SummonSpell(String name, float cost, Entity summon) {
		super(name, cost);
		this.summon = summon;
	}

	public void use(EventManager eventManager) {
		HealthPart healthPart = summon.get(HealthPart.class);
		healthPart.setHealth(healthPart.getMaxHealth());
		eventManager.notify(new EntityCreatedEvent(summon));
		System.out.println("\tCasting " + name);
	}

}
