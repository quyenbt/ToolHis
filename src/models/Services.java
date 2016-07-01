package models;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Bang3_BYT")
public class Services {
	private List<Service> servicesList;
    public Services() {    }

    public List<Service> getServicesList() { return servicesList; }

    @XmlElement(name = "row")
    public void setServicesList(List<Service> servicesList) {this.servicesList = servicesList;}	
}
