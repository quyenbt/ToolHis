package util;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import models.Medicines;
import models.Patients;
import models.Services;

public class XMLReader {

public static Patients getPatients() {
		
		Patients patients = null;
		try {

			File file = new File("b1.xml");

			JAXBContext jaxbContext = JAXBContext.newInstance(Patients.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			patients = (Patients) jaxbUnmarshaller.unmarshal(file);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return patients;
	}
	
public static Medicines getMedicines() {
		
		Medicines medicines = null;
		try {

			File file = new File("b2.xml");

			JAXBContext jaxbContext = JAXBContext.newInstance(Medicines.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			medicines = (Medicines) jaxbUnmarshaller.unmarshal(file);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return medicines;
	}

public static Services getServices() {
	
	Services services = null;
	try {

		File file = new File("b3.xml");

		JAXBContext jaxbContext = JAXBContext.newInstance(Services.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

		services = (Services) jaxbUnmarshaller.unmarshal(file);

	} catch (JAXBException e) {
		e.printStackTrace();
	}

	return services;
}
}