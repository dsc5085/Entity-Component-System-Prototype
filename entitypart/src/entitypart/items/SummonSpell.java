package entitypart.items;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import entitypart.epf.Entity;
import entitypart.epf.EntityAdapter;
import entitypart.epf.EntityCreateEvent;
import entitypart.util.EventManager;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class SummonSpell extends Spell {

	@XmlJavaTypeAdapter(EntityAdapter.class)
	@XmlElement
	private Entity summon;
	
	public SummonSpell() {
	}
	
	public SummonSpell(String name, float cost, Entity summon) {
		super(name, cost);
		this.summon = summon;
	}
	
	public Entity getSummon() {
		return summon;
	}

	public void use(EventManager eventManager) {
		eventManager.notify(new EntityCreateEvent(summon));
		System.out.println("\tCasting " + name);
	}

}
