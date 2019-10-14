package ng.dominic.parser.web;

import ng.dominic.parser.model.Record;
import ng.dominic.parser.service.FileServiceImpl;
import ng.dominic.parser.service.RecordServiceCsvImpl;
import ng.dominic.parser.service.RecordServiceXmlImpl;
import ng.dominic.parser.service.ValidationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class RecordHandler {

    @Autowired
    private FileServiceImpl fileService;

    @Autowired
    private RecordServiceCsvImpl recordService;

    @Autowired
    private RecordServiceXmlImpl recordServiceXml;

    @Autowired
    private ValidationServiceImpl validationService;

    // TODO: 12/10/2019 sanity check, remove when up and running
    @RequestMapping("/hello")
    public String hello() throws Exception {
        return "hallo, het is " + LocalDateTime.now();
    }

    @PostMapping("/csv")
    public String handleCsv(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        File file = fileService.convertToFile(multipartFile);
        List<Record> importedRecords = recordService.parseFile(file);
        List<Record> rejectedRecords = validationService.validateAll(importedRecords);
        return validationService.isValidated(rejectedRecords)
                ? "All records ok"
                : validationService.reportValidationFailures(rejectedRecords);
    }

    @PostMapping("/xml")
    public List<Record> handleXml(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        File file = fileService.convertToFile(multipartFile);
        List<Record> importedRecords = recordServiceXml.parseFile(file);
        return importedRecords;
    }
}
