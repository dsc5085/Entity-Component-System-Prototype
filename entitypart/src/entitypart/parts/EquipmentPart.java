package entitypart.parts;

import java.util.ArrayList;
import java.util.List;

import entitypart.epf.Part;
import entitypart.items.Spell;
import entitypart.items.Weapon;

public class EquipmentPart extends Part {

	private Weapon weapon;
	private List<Spell> spells;
	
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
	
	public <T extends Spell> Spell getSpell(Class<T> spellClass) {
		for (Spell spell : spells) {
			if (spell.getClass() == spellClass) {
				return spell;
			}
		}
		return null;
	}
	
}
