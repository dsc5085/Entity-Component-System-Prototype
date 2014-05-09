package entitypart.program;

import entitypart.epf.Entity;
import entitypart.epf.EntityAdapted;
import entitypart.items.HealSpell;
import entitypart.items.Spell;
import entitypart.items.SummonSpell;
import entitypart.parts.AlliancePart;
import entitypart.parts.DescriptionPart;
import entitypart.parts.EquipmentPart;
import entitypart.parts.FlyingPart;
import entitypart.parts.HealthPart;
import entitypart.parts.ManaPart;
import entitypart.parts.MentalityPart;
import entitypart.parts.RestorePart;
import entitypart.parts.TimedDeathPart;

public class Bindings {

	public static Class<?>[] BOUND_CLASSES = new Class<?>[] {
		Spell.class, 
		HealSpell.class, 
		SummonSpell.class, 
		AlliancePart.class, 
		DescriptionPart.class, 
		EquipmentPart.class, 
		FlyingPart.class, 
		HealthPart.class, 
		ManaPart.class, 
		MentalityPart.class, 
		RestorePart.class, 
		TimedDeathPart.class,
		Entity.class, 
		EntityAdapted.class
	};
	
}
