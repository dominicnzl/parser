package ng.dominic.parser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.Charset;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParserApplicationTests {

	@Test
	public void contextLoads() {

		// Locally, this prints out UTF-8 -> todo: handle encoding for input files
		System.out.println(Charset.defaultCharset().displayName());
	}

}
