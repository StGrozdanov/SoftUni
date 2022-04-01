package hiberspring.utils;

import hiberspring.utils.interfaces.XMLTool;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class XMLToolImpl implements XMLTool {

    private final Map<String, Marshaller> marshallerMap = new HashMap<>();
    private final Map<String, Unmarshaller> unmarshallerMap = new HashMap<>();

    @Override
    public <T> T fromXML(String path, Class<T> clazz) throws JAXBException, IOException {
        String clazzName = clazz.getSimpleName();
        Unmarshaller unmarshaller;

        if (this.unmarshallerMap.containsKey(clazzName)){
            unmarshaller = this.unmarshallerMap.get(clazzName);
        } else {
            JAXBContext context = JAXBContext.newInstance(clazz);
            unmarshaller = context.createUnmarshaller();
        }
        return (T) unmarshaller.unmarshal(new FileInputStream(path));
    }

    @Override
    public <T> void toXMLFile(String path, Object object) throws JAXBException {
        Marshaller marshaller = this.optimizeMarshaller(object);
        marshaller.marshal(object, new File(path));
    }

    @Override
    public <T> String toXML(Object object) throws JAXBException {
        Marshaller marshaller = this.optimizeMarshaller(object);

        StringWriter stringWriter = new StringWriter();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(object, stringWriter);

        return stringWriter.toString();
    }

    private Marshaller optimizeMarshaller(Object object) throws JAXBException {
        Marshaller marshaller;

        String objectName = object.getClass().getSimpleName();

        if (this.marshallerMap.containsKey(objectName)) {
            marshaller = this.marshallerMap.get(objectName);
        } else {
            JAXBContext context = JAXBContext.newInstance(object.getClass());
            marshaller = context.createMarshaller();
            this.marshallerMap.put(objectName, marshaller);
        }
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        return marshaller;
    }
}
