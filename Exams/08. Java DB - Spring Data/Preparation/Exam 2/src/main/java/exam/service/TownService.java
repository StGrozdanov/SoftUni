package exam.service;



import exam.model.entities.Town;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface TownService {

    boolean areImported();

    String readTownsFileContent() throws IOException;
	
	String importTowns() throws JAXBException, IOException;

    Town findTownByName(String townName);
}
