package entitypart.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.namespace.QName;

import org.eclipse.persistence.jaxb.JAXBContextProperties;

public class XmlUtils {
	
	/**
	 * Reads an xml file to an object.
	 * @param path path to the xml file
	 * @param xmlAdapter adapter
	 * @param boundClasses used to correctly unmarshal base types to derived types
	 * @param bindingsPath path to the bindings file used to specify JAXB attributes on unmodifiable classes
	 * @return object of type T
	 */
	public static <TAdapted, T> T read(String path, XmlAdapter<TAdapted, T> xmlAdapter, Class<?>[] boundClasses, 
			String bindingsPath) {
		Map<String, Object> properties = new HashMap<String, Object>(1);
		properties.put(JAXBContextProperties.OXM_METADATA_SOURCE, bindingsPath);
		JAXBContext jaxbContext;
		T object = null;
		
		try {
			jaxbContext = JAXBContext.newInstance(boundClasses, properties);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			File file = new File(path);
			
			@SuppressWarnings("unchecked")
			TAdapted adaptedObject = (TAdapted)unmarshaller.unmarshal(file);
			object = xmlAdapter.unmarshal(adaptedObject);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return object;
	}
	
	/**
	 * Writes an object to an xml file.
	 * @param adaptedObjectClass adapted object class
	 * @param object object to write
	 * @param path path of the xml file to write to
	 * @param xmlAdapter xml adapter
	 * @param boundClasses used to correctly marshal base types to derived types
	 * @param bindingsPath path to the bindings file used to specify JAXB attributes on unmodifiable classes
	 */
	public static <TAdapted, T> void write(Class<TAdapted> adaptedObjectClass, T object, String path, 
			XmlAdapter<TAdapted, T> xmlAdapter, Class<?>[] boundClasses, String bindingsPath) {
		Map<String, Object> properties = new HashMap<String, Object>(1);
		properties.put(JAXBContextProperties.OXM_METADATA_SOURCE, bindingsPath);
		JAXBContext jaxbContext;
		
		try {
			jaxbContext = JAXBContext.newInstance(boundClasses, properties);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			TAdapted adaptedObject = xmlAdapter.marshal(object);
			File file = new File(path);
			
			if (adaptedObjectClass.isAnnotationPresent(XmlRootElement.class)) {
				marshaller.marshal(adaptedObject, file);
			}
			else {
				QName qName = new QName(adaptedObjectClass.getSimpleName().toLowerCase());
				JAXBElement<TAdapted> element = new JAXBElement<TAdapted>(qName, adaptedObjectClass, adaptedObject);
				marshaller.marshal(element, file);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
