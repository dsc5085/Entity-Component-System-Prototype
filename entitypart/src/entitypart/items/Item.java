package entitypart.items;

import entitypart.epf.Entity;

public abstract class Item {

	private String name;
	
	public Item(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public abstract ItemType getItemType();
	
	public abstract void use(Entity target);
	
}
