package entitypart.parts;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import entitypart.epf.Part;
import entitypart.items.Spell;
import entitypart.items.Weapon;

/**
 * Manages entity weapon and spells.
 * @author David Chen
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class EquipmentPart extends Part {

	@XmlElement
	private Weapon weapon;
	@XmlElement
	private List<Spell> spells;
	
	public EquipmentPart() {
	}
	
	public EquipmentPart(Weapon weapon) {
		this(weapon, new ArrayList<Spell>());
	}
	
	public EquipmentPart(Weapon weapon, List<Spell> spells) {
		this.weapon = weapon;
		this.spells = spells;
	}
	
	public Weapon getWeapon() {
		return weapon;
	}
	
	public <T extends Spell> boolean hasSpell(Class<T> spellClass) {
		for (Spell spell : spells) {
			if (spell.getClass() == spellClass) {
				return true;
			}
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Spell> T getSpell(Class<T> spellClass) {
		for (Spell spell : spells) {
			if (spell.getClass() == spellClass) {
				return (T)spell;
			}
		}
		return null;
	}
	
}
