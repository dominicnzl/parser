package ng.dominic.parser.web;

import ng.dominic.parser.model.Record;
import ng.dominic.parser.service.FileServiceImpl;
import ng.dominic.parser.service.RecordServiceCsvImpl;
import ng.dominic.parser.service.RecordServiceXmlImpl;
import ng.dominic.parser.service.ValidationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
public class RecordHandler {

    @Autowired
    private FileServiceImpl fileService;

    @Autowired
    private RecordServiceCsvImpl recordServiceCsv;

    @Autowired
    private RecordServiceXmlImpl recordServiceXml;

    @Autowired
    private ValidationServiceImpl validationService;

    @PostMapping("/csv")
    public String handleCsv(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        File file = fileService.convertToFile(multipartFile);
        List<Record> importedRecords = recordServiceCsv.parseFile(file);
        return validationService.validateAll(importedRecords);
    }

    @PostMapping("/xml")
    public String handleXml(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        File file = fileService.convertToFile(multipartFile);
        List<Record> importedRecords = recordServiceXml.parseFile(file);
        return validationService.validateAll(importedRecords);
    }
}
