 package service;

import util.XMLReader;
import models.Medicines;
import models.Patients;
import models.Services;

public class XMLService {

      public Patients getPatients() {

            return XMLReader.getPatients();

      }
      public Medicines getMedicines() {

          return XMLReader.getMedicines();

    }
      public Services getServices() {

          return XMLReader.getServices();

    }
}