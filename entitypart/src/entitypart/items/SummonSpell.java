package entitypart.items;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import entitypart.epf.Entity;
import entitypart.epf.EntityCreateEvent;
import entitypart.util.EventManager;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class SummonSpell extends Spell {

	@XmlElement
	// TODO: Adapter
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
