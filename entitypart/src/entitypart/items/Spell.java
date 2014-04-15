package entitypart.items;

public abstract class Spell {

	protected String name;
	private float cost;
	
	public Spell(String name, float cost) {
		this.name = name;
		this.cost = cost;
	}
	
	public float getCost() {
		return cost;
	}
	
}
