package com.termilion.realmartisan;

import com.termilion.realmartisan.model.Distribution;
import com.termilion.realmartisan.model.Ethnicity;
import com.termilion.realmartisan.repository.CharacterRepository;
import com.termilion.realmartisan.repository.EthnicityRepository;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class RealmArtisanApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(RealmArtisanApplication.class);

	@Autowired
	CharacterRepository characterRepository;

	@Autowired
	EthnicityRepository ethnicityRepository;

	public static void main(String[] args) {
		SpringApplication.run(RealmArtisanApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

	}

	private void saveDefaults() throws JDOMException, IOException {
		Ethnicity default_human = new Ethnicity();
		XMLOutputter outp = new XMLOutputter();
		SAXBuilder builder = new SAXBuilder();
		File file = new File("/home/termi/IdeaProjects/realm-artisan/src/main/resources/default_objects.xml");
		Document document = builder.build(file);
		Element root = document.getRootElement();
		Element ageDist = root.getChild("age").getChild("distribution");
		Element sizeDist = root.getChild("size").getChild("distribution");
		Element skinDist = root.getChild("skinTone").getChild("distribution");
		Element hairDist = root.getChild("hairTone").getChild("distribution");
		Element eyeDist = root.getChild("eyeTone").getChild("distribution");
		String[] features = new String[] {"curly hair", "muscular", "overweight", "charismatic", "rich", "pierced", "tattooed", "intelligent", "bearded"};

		default_human.setName("Human");
		default_human.setDescription("Default Human");
		default_human.setAge(outp.outputString(ageDist));
		default_human.setSize(outp.outputString(sizeDist));
		default_human.setSkinTones(outp.outputString(skinDist));
		default_human.setHairTones(outp.outputString(hairDist));
		default_human.setEyeTones(outp.outputString(eyeDist));
		default_human.setFeatures(features);

		ethnicityRepository.save(default_human);
	}
}
