package com.termilion.realmartisan;

import com.termilion.realmartisan.model.Distribution;
import com.termilion.realmartisan.model.Ethnicity;
import com.termilion.realmartisan.model.Region;
import com.termilion.realmartisan.repository.CharacterRepository;
import com.termilion.realmartisan.repository.EthnicityRepository;
import com.termilion.realmartisan.repository.RegionRepository;
import org.jdom2.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class RealmArtisanApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(RealmArtisanApplication.class);

	@Autowired
	CharacterRepository characterRepository;

	@Autowired
	EthnicityRepository ethnicityRepository;

	@Autowired
    RegionRepository regionRepository;

	public static void main(String[] args) {
		SpringApplication.run(RealmArtisanApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//loadDefaultsIntoDB();
	}

	private void loadDefaultsIntoDB() throws JDOMException, IOException {
		Ethnicity default_human = new Ethnicity();

		JsonReader reader = Json.createReader(
				new FileReader(new File(
						"/home/termi/IdeaProjects/realm-artisan/src/main/resources/default_objects.json"
				)));
		JsonObject object = reader.readObject().getJsonObject("objects");
		JsonObject ageDist = object.getJsonObject("age");
		JsonObject sizeDist = object.getJsonObject("size");
		JsonObject skinDist = object.getJsonObject("skinTone");
		JsonObject hairDist = object.getJsonObject("hairTone");
		JsonObject eyeDist = object.getJsonObject("eyeTone");
		JsonObject professions = object.getJsonObject("professions");

		String[] features = new String[] {"curly hair", "muscular", "overweight", "charismatic", "rich", "pierced", "tattooed", "intelligent", "bearded"};

		default_human.setName("Human");
		default_human.setDescription("Default Human");
		default_human.setAge(ageDist.toString());
		default_human.setSize(sizeDist.toString());
		default_human.setSkinTones(skinDist.toString());
		default_human.setHairTones(hairDist.toString());
		default_human.setEyeTones(eyeDist.toString());
		default_human.setFeatures(features);

		ethnicityRepository.save(default_human);

        List<String> ethIds = new ArrayList<>();
		ethnicityRepository.findAll().forEach(ethnicity -> {
		    ethIds.add(String.format("%s", ethnicity.getId()));
        });
        Distribution ethnicities = new Distribution(ethIds);

        Region region = new Region();
		region.setName("Alessa");
		region.setEthnicities(ethnicities.toJson());
		region.setProfessions(professions.toString());
		regionRepository.save(region);

	}
}