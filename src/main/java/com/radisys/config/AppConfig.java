package com.radisys.config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "users")
public class AppConfig {

	private String userColl;

	@Autowired
	private MongoTemplate mongoTemplate;

	@PostConstruct
	public void name() throws FileNotFoundException {

		if (!mongoTemplate.collectionExists(userColl)) {
			CollectionOptions collectionOptions = CollectionOptions.empty().capped().size(2048).maxDocuments(10);
			MongoCollection<Document> createCollection = mongoTemplate.createCollection(userColl, collectionOptions);
			createCollection.createIndex(new BasicDBObject("phone", 1));
		}

		Map<String, String> mapping = new HashMap<>();
		mapping.put("name", "userName");
		mapping.put("PhoneNumber", "number");
		mapping.put("Age", "age");
		mapping.put("Dept", "dept");
		HeaderColumnNameTranslateMappingStrategy<User> strategy = 
				new HeaderColumnNameTranslateMappingStrategy<>();
		strategy.setType(User.class);
		strategy.setColumnMapping(mapping);
		CSVReader csvReader = new CSVReader(new FileReader("C:\\Users\\uvalluru\\Desktop\\Records.csv"));
		CsvToBean<User> csv = new CsvToBean<>();

		
		List<User> list = csv.parse(strategy, csvReader);

		list.forEach(i -> {
			mongoTemplate.save(i, userColl);
		});

	}
}
