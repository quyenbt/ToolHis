package util;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import models.Medicines;
import models.Patients;
import models.Services;

public class XMLWriter {
	 public static void writeXML(Object jaxbObject) {
	        try {
	            JAXBContext context = JAXBContext.newInstance(jaxbObject.getClass());
	            Marshaller marshaller = context.createMarshaller();
	            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	            marshaller.marshal(jaxbObject, new File("/XMLCheckout/" + jaxbObject.getClass().getSimpleName() + ".xml"));
	            marshaller.marshal(jaxbObject, System.out);
	        } catch (JAXBException e) {
	            e.printStackTrace();
	        }
	    }	

}
