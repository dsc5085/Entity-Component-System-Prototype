package entitypart.parts;

import entitypart.epf.Part;

public class ManaPart extends Part {

	private float maxMana;
	private float mana;
	
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
