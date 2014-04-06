package entitypart.parts;

import entitypart.epf.Part;

public class AlliancePart extends Part {

	private Alliance alliance;
	
	public AlliancePart(Alliance alliance) {
		this.alliance = alliance;
	}
	
	public Alliance getAlliance() {
		return alliance;
	}
	
}
