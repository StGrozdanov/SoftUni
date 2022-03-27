package exam.util.interfaces;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface XMLTool {
    <T> T fromXML(String path, Class<T> clazz) throws JAXBException, IOException;

    <T> void toXMLFile(String path, Object obj) throws JAXBException;

    <T> String toXML(Object obj) throws JAXBException;
}