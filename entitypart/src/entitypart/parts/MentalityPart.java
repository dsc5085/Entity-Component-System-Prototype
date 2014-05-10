package entitypart.parts;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import entitypart.epf.Part;

@XmlRootElement
public class MentalityPart extends Part {

	@XmlElement
	private Mentality mentality;
	
	public MentalityPart() {
	}
	
	public MentalityPart(Mentality mentality) {
		this.mentality = mentality;
	}
	
	public Mentality getMentality() {
		return mentality;
	}
	
}
