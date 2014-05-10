package entitypart.program;

import entitypart.epf.Entity;
import entitypart.epf.EntityAdapted;
import entitypart.epf.EntityAdapter;
import entitypart.parts.Alliance;
import entitypart.util.XmlUtils;

public class EntityMaker {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException {
		Class<?>[] boundClasses = Bindings.BOUND_CLASSES;
		
		Entity meleer = CharacterFactory_Old.createMeleer("", Alliance.MONSTERS);
		XmlUtils.write(EntityAdapted.class, meleer, "meleer.xml", new EntityAdapter(), boundClasses, "bindings.xml");
		Entity ranger = CharacterFactory_Old.createFlyingRanger("", Alliance.MONSTERS);
		XmlUtils.write(EntityAdapted.class, ranger, "ranger.xml", new EntityAdapter(), boundClasses, "bindings.xml");
		Entity flyingRanger = CharacterFactory_Old.createFlyingRanger("", Alliance.MONSTERS);
		XmlUtils.write(EntityAdapted.class, flyingRanger, "flyingRanger.xml", new EntityAdapter(), boundClasses, "bindings.xml");
		Entity summoner = CharacterFactory_Old.createSummoner("", Alliance.MONSTERS);
		XmlUtils.write(EntityAdapted.class, summoner, "summoner.xml", new EntityAdapter(), boundClasses, "bindings.xml");
		Entity supportMage = CharacterFactory_Old.createSupportMage("", Alliance.MONSTERS);
		XmlUtils.write(EntityAdapted.class, supportMage, "supportMage.xml", new EntityAdapter(), boundClasses, "bindings.xml");
	}
	
}
