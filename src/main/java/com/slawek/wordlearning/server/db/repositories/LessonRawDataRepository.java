package com.slawek.wordlearning.server.db.repositories;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.slawek.wordlearning.server.db.model.Lesson;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class LessonRawDataRepository {

	private MongoDatabase db;

	@Value(value = "${spring.data.mongodb.host}")
	private String host;
	@Value(value = "${spring.data.mongodb.port}")
	private int port;
	@Value(value = "${spring.data.mongodb.database}")
	private String database;
	@Value(value = "${spring.data.mongodb.username}")
	private String username;
	@Value(value = "${spring.data.mongodb.password}")
	private String password;

	@Value(value = "${db}")
	private String dbs;

	@PostConstruct void init() {
		ServerAddress serverAddress = new ServerAddress();
		MongoClientURI uri = new MongoClientURI("mongodb://heroku_nvwngddl:XXxxxx33$@ds023373.mlab.com:23373/heroku_nvwngddl");
		MongoClient mongoClient = new MongoClient(uri);
		db = mongoClient.getDatabase("heroku_nvwngddl");
	}

	public List<String> getAllLessonIds() {
		FindIterable<Document> cursor = db.getCollection(Lesson.COLLECTION_NAME)
				.find()
				.projection(Projections.include("_id"));

		ArrayList<String> lessonIds = new ArrayList<>();
		for (Document docu : cursor) {
			lessonIds.add(docu.get("_id").toString());
		}
		return lessonIds;
	}

	public String query(String id) {
		MongoCollection<Document> coll = db.getCollection(Lesson.COLLECTION_NAME);
		FindIterable<Document> documents = coll.find(Filters.eq("_id", new ObjectId(id)));
		List<String> value = new ArrayList<>();

		documents.forEach((Block<Document>) document -> {
			value.add(document.toJson());
		});

		if (value.size() == 1) {
			return value.get(0);
		} else {
			return "kupa";
		}

	}
}