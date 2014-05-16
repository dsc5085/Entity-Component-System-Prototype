package entitypart.program;

import entitypart.epf.Entity;
import entitypart.epf.EntityAdapted;
import entitypart.epf.EntityAdapter;
import entitypart.parts.Alliance;
import entitypart.util.XmlUtils;

/**
 * Helper program that uses the old character factory to create entities and write them to xml files.
 * @author David Chen
 *
 */
public class EntityMaker {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException {
		Class<?>[] boundClasses = Bindings.BOUND_CLASSES;
		
		Entity meleer = CharacterFactory_Old.createMeleer("", Alliance.MONSTERS);
		XmlUtils.write(EntityAdapted.class, meleer, Paths.CHARACTERS + "meleer.xml", new EntityAdapter(), 
				boundClasses, "bindings.xml");
		Entity ranger = CharacterFactory_Old.createFlyingRanger("", Alliance.MONSTERS);
		XmlUtils.write(EntityAdapted.class, ranger, Paths.CHARACTERS + "ranger.xml", new EntityAdapter(), 
				boundClasses, "bindings.xml");
		Entity flyingRanger = CharacterFactory_Old.createFlyingRanger("", Alliance.MONSTERS);
		XmlUtils.write(EntityAdapted.class, flyingRanger, Paths.CHARACTERS + "flyingRanger.xml", new EntityAdapter(), 
				boundClasses, "bindings.xml");
		Entity summoner = CharacterFactory_Old.createSummoner("", Alliance.MONSTERS);
		XmlUtils.write(EntityAdapted.class, summoner, Paths.CHARACTERS + "summoner.xml", new EntityAdapter(), 
				boundClasses, "bindings.xml");
		Entity supportMage = CharacterFactory_Old.createSupportMage("", Alliance.MONSTERS);
		XmlUtils.write(EntityAdapted.class, supportMage, Paths.CHARACTERS + "supportMage.xml", new EntityAdapter(), 
				boundClasses, "bindings.xml");
	}
	
}
