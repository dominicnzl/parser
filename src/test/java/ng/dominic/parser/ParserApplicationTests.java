package ng.dominic.parser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParserApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testBigD() {
		BigDecimal bd = new BigDecimal("-1");
		bd = bd.add(new BigDecimal("-22.24"));
		bd = bd.add(new BigDecimal("+100.01"));
		System.out.println(bd);
	}

}
