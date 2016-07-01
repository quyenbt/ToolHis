package models;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Bang2_BYT")
public class Medicines {
	private List<Medicine> medicinesList;
    public Medicines() {    }

    public List<Medicine> getMedicinesList() { return medicinesList; }

    @XmlElement(name = "row")
    public void setMedicinesList(List<Medicine> medicinesList) {
          this.medicinesList = medicinesList;
    }	

}