package entitypart.program;

import entitypart.epf.Entity;
import entitypart.items.AttackItem;
import entitypart.items.AttackRange;
import entitypart.items.Item;
import entitypart.parts.Alliance;
import entitypart.parts.AlliancePart;
import entitypart.parts.DescriptionPart;
import entitypart.parts.EquipmentPart;
import entitypart.parts.HealthPart;

public class CharacterFactory {

	/**
	 * Creates a tank character with a weapon that deals high damage at close range.
	 * @return Meleer entity.
	 */
	public static Entity createMeleer(String name, Alliance alliance) {
		Entity meleer = createBaseCharacter(name, 200, alliance);
		Item heldItem = new AttackItem("Sword", 25, 50, AttackRange.CLOSE);
		meleer.attach(new EquipmentPart(heldItem));
		return meleer;
	}
	
	private static Entity createBaseCharacter(String name, float health, Alliance alliance) {
		// create a character entity that has parts all characters should have
		Entity character = new Entity();
		character.attach(new DescriptionPart(name));
		character.attach(new HealthPart(health));
		character.attach(new AlliancePart(alliance));
		return character;
	}
	
}
