package entitypart.parts;

import entitypart.epf.Part;

public class DescriptionPart extends Part {

	private String name;
	
	public DescriptionPart(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
}
