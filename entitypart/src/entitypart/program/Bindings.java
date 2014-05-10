package entitypart.program;

import entitypart.items.HealSpell;
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

	/**
	 * Required for serializing list of base types to derived types, e.g. when a list of parts is serialized, binding 
	 * the health part class to the serialization will allow health parts in the list to be serialized correctly.
	 */
	public static Class<?>[] BOUND_CLASSES = new Class<?>[] {
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
		TimedDeathPart.class
	};
	
}
