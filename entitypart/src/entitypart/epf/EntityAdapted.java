package entitypart.epf;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class EntityAdapted {

	@XmlElement
	private List<Part> parts;
	
	public EntityAdapted() {
	}
	
	public EntityAdapted(List<Part> parts) {
		this.parts = parts;
	}

	public List<Part> getParts() {
		return new ArrayList<Part>(parts);
	}
	
}
