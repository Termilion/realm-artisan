package com.termilion.realmartisan;

import com.termilion.realmartisan.model.Distribution;
import com.termilion.realmartisan.model.Group;
import com.termilion.realmartisan.model.Region;
import com.termilion.realmartisan.repository.CharacterRepository;
import com.termilion.realmartisan.repository.GroupRepository;
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
	GroupRepository ethnicityRepository;

	@Autowired
    RegionRepository regionRepository;

	public static void main(String[] args) {
		SpringApplication.run(RealmArtisanApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {}
}