package entitypart.items;

import entitypart.epf.Entity;
import entitypart.epf.EntityCreateEvent;
import entitypart.util.EventManager;

public class SummonSpell extends Spell {

	private Entity summon;
	
	public SummonSpell(String name, float cost, Entity summon) {
		super(name, cost);
		this.summon = summon;
	}

	public void use(EventManager eventManager) {
		eventManager.notify(new EntityCreateEvent(summon));
		System.out.println("\tCasting " + name);
	}

}
