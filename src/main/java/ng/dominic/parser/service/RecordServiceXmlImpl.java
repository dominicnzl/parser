package ng.dominic.parser.service;

import ng.dominic.parser.model.Record;
import ng.dominic.parser.model.Records;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.List;

@Service
public class RecordServiceXmlImpl implements RecordService {

    @Override
    public List<Record> parseFile(File file) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Records.class);
        Records records = (Records) context.createUnmarshaller().unmarshal(file);
        return records.getRecords();
    }
}
