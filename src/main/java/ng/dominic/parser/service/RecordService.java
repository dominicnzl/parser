package ng.dominic.parser.service;

import ng.dominic.parser.model.Record;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface RecordService {

    public File convertToFile(MultipartFile multipartFile) throws IOException;

    public List<Record> parseFile(File file) throws Exception;

    public Record createRecordDTO(String[] elements);
}
