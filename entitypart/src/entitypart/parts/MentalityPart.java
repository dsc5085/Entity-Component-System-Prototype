package entitypart.parts;

import entitypart.epf.Part;

public class MentalityPart extends Part {

	private Mentality mentality;
	
	public MentalityPart(Mentality mentality) {
		this.mentality = mentality;
	}
	
	public Mentality getMentality() {
		return mentality;
	}
	
}
