package entitypart.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.namespace.QName;

public class XmlWriter {

	public static <T> void write(Class<T> objectClass, T object, String path) {
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(objectClass);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			//File file = new File(path);
			
			if (objectClass.isAnnotationPresent(XmlRootElement.class)) {
				marshaller.marshal(object, System.out);
			}
			else {
				QName qName = new QName(objectClass.getSimpleName().toLowerCase());
				JAXBElement<T> element = new JAXBElement<T>(qName, objectClass, object);
				marshaller.marshal(element, System.out);
			}
		}
		catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public static <T, TAdapted> void write(Class<TAdapted> adaptedObjectClass, T object, String path, 
			XmlAdapter<T, TAdapted> xmlAdapter, Class<?>[] boundClasses) {
		JAXBContext jaxbContext;
		try {
			TAdapted adaptedObject = xmlAdapter.unmarshal(object);
			jaxbContext = JAXBContext.newInstance(boundClasses);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			//File file = new File(path);
			
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
