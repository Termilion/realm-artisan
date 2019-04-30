package com.termilion.realmartisan;

import com.termilion.realmartisan.model.Distribution;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RealmArtisanApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testDistributionLoader(){
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"<distribution><element key=\"A\" range=\"30\" /><element key=\"B\" range=\"20\" /><element key=\"C\" range=\"50\" /></distribution>";
		Distribution d = new Distribution(xml);
		System.out.println(d.toString());
		System.out.println(d.getMaxValue());
		System.out.println(d.roll());
	}
}
