package entitypart.parts;

import entitypart.epf.Part;
import entitypart.items.Item;

public class EquipmentPart extends Part {

	private Item heldItem;
	
	public EquipmentPart(Item heldItem) {
		this.heldItem = heldItem;
	}
	
	public Item getHeldItem() {
		return heldItem;
	}
	
}
