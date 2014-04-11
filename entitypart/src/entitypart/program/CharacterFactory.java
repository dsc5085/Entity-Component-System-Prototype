package entitypart.program;

import java.util.ArrayList;
import java.util.List;

import entitypart.epf.Entity;
import entitypart.items.AttackRange;
import entitypart.items.HealSpell;
import entitypart.items.Spell;
import entitypart.items.Weapon;
import entitypart.parts.Alliance;
import entitypart.parts.AlliancePart;
import entitypart.parts.DescriptionPart;
import entitypart.parts.EquipmentPart;
import entitypart.parts.FlyingPart;
import entitypart.parts.HealthPart;
import entitypart.parts.ManaPart;
import entitypart.parts.Mentality;
import entitypart.parts.MentalityPart;
import entitypart.parts.RestorePart;

public class CharacterFactory {

	/**
	 * Creates a tank with a weapon that deals high damage at close range.
	 * @return Meleer entity.
	 */
	public static Entity createMeleer(String name, Alliance alliance) {
		Entity meleer = createBaseCharacter(name, 200, 0, alliance, Mentality.OFFENSIVE);
		Weapon sword = new Weapon("Sword", 25, 50, AttackRange.CLOSE);
		meleer.attach(new EquipmentPart(sword));
		return meleer;
	}
	
	/**
	 * Creates a ranger with a weapon that deals moderate damage at long range.
	 * @return Ranger entity.
	 */
	public static Entity createRanger(String name, Alliance alliance) {
		Entity ranger = createBaseCharacter(name, 125, 0, alliance, Mentality.OFFENSIVE);
		Weapon bow = new Weapon("Bow", 15, 35, AttackRange.FAR);
		ranger.attach(new EquipmentPart(bow));
		return ranger;
	}
	
	/**
	 * Creates a flying ranger with a weapon that deals moderate damage at long range.
	 * @return Ranger entity.
	 */
	public static Entity createFlyingRanger(String name, Alliance alliance) {
		Entity ranger = createBaseCharacter(name, 100, 0, alliance, Mentality.OFFENSIVE);
		Weapon bow = new Weapon("Bow", 15, 35, AttackRange.FAR);
		ranger.attach(new EquipmentPart(bow));
		ranger.attach(new FlyingPart());
		return ranger;
	}
	
	/**
	 * Creates a support mage with spells to heal and buff.
	 * @return Mage entity.
	 */
	public static Entity createSupportMage(String name, Alliance alliance) {
		Entity mage = createBaseCharacter(name, 100, 100, alliance, Mentality.SUPPORT);
		Weapon staff = new Weapon("Staff", 10, 15, AttackRange.CLOSE);
		List<Spell> spells = new ArrayList<Spell>();
		spells.add(new HealSpell("Light Heal", 25, 30));
		mage.attach(new EquipmentPart(staff, spells));
		return mage;
	}
	
	private static Entity createBaseCharacter(String name, float health, float mana, Alliance alliance, 
			Mentality mentality) {
		// create a character entity that has parts all characters should have
		Entity character = new Entity();
		character.attach(new DescriptionPart(name));
		character.attach(new HealthPart(health));
		character.attach(new ManaPart(mana));
		character.attach(new RestorePart(0.01f, 0.03f));
		character.attach(new AlliancePart(alliance));
		character.attach(new MentalityPart(mentality));
		return character;
	}
	
}
