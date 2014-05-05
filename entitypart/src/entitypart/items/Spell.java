package entitypart.items;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public abstract class Spell {

	@XmlElement
	protected String name;
	@XmlElement
	private float cost;
	
	public Spell() {
	}
	
	public Spell(String name, float cost) {
		this.name = name;
		this.cost = cost;
	}
	
	public float getCost() {
		return cost;
	}
	
}
