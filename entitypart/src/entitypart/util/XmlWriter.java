package entitypart.util;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.namespace.QName;

import org.eclipse.persistence.jaxb.JAXBContextProperties;

public class XmlWriter {
	
	public static <T, TAdapted> void write(Class<TAdapted> adaptedObjectClass, T object, String path, 
			XmlAdapter<T, TAdapted> xmlAdapter, Class<?>[] boundClasses, String bindingsPath) {
		Map<String, Object> properties = new HashMap<String, Object>(1);
		properties.put(JAXBContextProperties.OXM_METADATA_SOURCE, bindingsPath);
		JAXBContext jaxbContext;
		
		try {
			jaxbContext = JAXBContext.newInstance(boundClasses, properties);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			TAdapted adaptedObject = xmlAdapter.unmarshal(object);
			
			if (adaptedObjectClass.isAnnotationPresent(XmlRootElement.class)) {
				marshaller.marshal(adaptedObject, System.out);
			}
			else {
				QName qName = new QName(adaptedObjectClass.getSimpleName().toLowerCase());
				JAXBElement<TAdapted> element = new JAXBElement<TAdapted>(qName, adaptedObjectClass, adaptedObject);
				marshaller.marshal(element, System.out);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
