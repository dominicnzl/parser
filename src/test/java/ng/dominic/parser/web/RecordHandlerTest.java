package ng.dominic.parser.web;

import ng.dominic.parser.service.FileServiceImpl;
import ng.dominic.parser.service.RecordServiceCsvImpl;
import ng.dominic.parser.service.RecordServiceXmlImpl;
import ng.dominic.parser.service.ValidationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RecordHandler.class)
public class RecordHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FileServiceImpl fileService;

    @MockBean
    private RecordServiceCsvImpl recordServiceCsv;

    @MockBean
    private RecordServiceXmlImpl recordServiceXml;

    @MockBean
    private ValidationServiceImpl validationService;

    private MockMultipartFile csvFile = new MockMultipartFile(
            "file",
            "records.csv",
            "text/csv",
            "test".getBytes());


    private MockMultipartFile xmlFile = new MockMultipartFile(
            "file",
            "records.xml",
            "text/xml",
            "text".getBytes());

    @Test
    public void handleCsv() throws Exception {
        mockMvc.perform(multipart("/csv")
                .file(csvFile)
                .accept("text/csv"))
                .andExpect(status().isOk());
    }

    @Test
    public void handleXml() throws Exception {
        mockMvc.perform(multipart("/xml")
                .file(xmlFile)
                .accept("text/xml"))
                .andExpect(status().isOk());
    }

    @Test
    public void expectGetNotAllowed() throws Exception {
        mockMvc.perform(get("/csv")).andExpect(status().is4xxClientError());
    }
}