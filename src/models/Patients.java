package models;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Bang1_BYT")
public class Patients {
	private List<Patient> patientsList;

    public Patients() {    }

    public List<Patient> getPatientsList() { return patientsList; }

    @XmlElement(name = "row")
    public void setPatientsList(List<Patient> patientsList) {this.patientsList = patientsList;}	

}