package entitypart.program;

import entitypart.epf.Entity;
import entitypart.epf.EntityAdapter;
import entitypart.parts.Alliance;
import entitypart.parts.AlliancePart;
import entitypart.parts.DescriptionPart;
import entitypart.util.XmlUtils;

public class CharacterFactory {
	
	/**
	 * Creates an character entity from a file path.
	 * @param path path to the serialized character definition
	 * @param name
	 * @param alliance
	 * @return new character
	 */
	public static Entity createCharacter(String path, String name, Alliance alliance) {
		Entity character = XmlUtils.read(path, new EntityAdapter(), Bindings.BOUND_CLASSES, "bindings.xml");
		character.get(DescriptionPart.class).setName(name);
		character.get(AlliancePart.class).setAlliance(alliance);
		return character;
	}
	
}
