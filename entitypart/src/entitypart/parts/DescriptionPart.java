package entitypart.parts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import entitypart.epf.Part;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class DescriptionPart extends Part {

	@XmlElement
	private String name;
	
	public DescriptionPart() {
	}
	
	public DescriptionPart(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
