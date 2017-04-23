package main.utils;

import listeners.MySessionListener;
import org.apache.log4j.Logger;

import javax.xml.bind.*;
import java.io.File;

/**
 * Created by Shoma on 15.04.2017.
 */
public class JaxBParser implements Parser{
    private static Logger userLogger = Logger.getLogger(MySessionListener.class);
    @Override
    public Object getObject(File file, Class c) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(c);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Object object = unmarshaller.unmarshal(file);
userLogger.debug("JaxBParser "+object);
        return object;
    }

    @Override
    public void saveObject(File file, Object o) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(o.getClass());
        Marshaller marshaller = context.createMarshaller();
      userLogger.debug("JaxBParser "+o+" with file "+file.getName());
        marshaller.marshal(o,file);
    }
}
