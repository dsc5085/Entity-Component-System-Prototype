package entitypart.parts;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import entitypart.epf.Part;

/**
 * Determines the side an entity is on for A.I. purposes.
 * @author David Chen
 *
 */
@XmlRootElement
public class AlliancePart extends Part {

	@XmlElement
	private Alliance alliance;
	
	public AlliancePart() {
	}
	
	public AlliancePart(Alliance alliance) {
		this.alliance = alliance;
	}
	
	public Alliance getAlliance() {
		return alliance;
	}
	
	public void setAlliance(Alliance alliance) {
		this.alliance = alliance;
	}
	
}
