package ng.dominic.parser.service;

import ng.dominic.parser.model.Record;

import java.io.File;
import java.util.List;

public interface RecordService {

    // Takes a file and returns a list of records
    public List<Record> parseFile(File file) throws Exception;

}
