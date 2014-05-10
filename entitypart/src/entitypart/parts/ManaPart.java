package entitypart.parts;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import entitypart.epf.Part;

@XmlRootElement
public class ManaPart extends Part {

	@XmlElement
	private float maxMana;
	@XmlElement
	private float mana;
	
	public ManaPart() {
	}
	
	public ManaPart(float maxMana) {
		this.maxMana = maxMana;
	}
	
	@Override
	public void initialize() {
		this.mana = maxMana;
	}
	
	public float getMaxMana() {
		return maxMana;
	}
	
	public float getMana() {
		return mana;
	}
	
	public void setMana(float mana) {
		this.mana = mana;
	}
	
}
