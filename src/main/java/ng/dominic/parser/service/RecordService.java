package ng.dominic.parser.service;

import ng.dominic.parser.model.Record;

import java.io.File;
import java.util.List;

public interface RecordService {

    public List<Record> parseFile(File file) throws Exception;

}
