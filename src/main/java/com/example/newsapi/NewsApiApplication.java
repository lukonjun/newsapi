package com.example.newsapi;

import com.example.newsapi.domain.News;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@SpringBootApplication
@Slf4j
public class NewsApiApplication {

	public static final String API_TOKEN = "f02dced1b8b64e6bb04e6443cf4d02e8";

	public static void main(String[] args) {
		SpringApplication.run(NewsApiApplication.class, args);
	}

	@Test
	public void httpRequestNewsApi() throws URISyntaxException, IOException, InterruptedException {

		String queryParameter = "country=de&apiKey=" + API_TOKEN;
		String urlString = "https://newsapi.org/v2/top-headlines" + "?" + queryParameter;

		log.info("Send GET request to " + urlString);
		HttpRequest request = HttpRequest.newBuilder()
				.uri(new URI(urlString))
				.GET()
				.build();

		var response = HttpClient.newHttpClient().send(request,
				HttpResponse.BodyHandlers.ofString());

		log.info("Status code: " + response.statusCode());
		log.info("Headers: " + response.headers());

		// Code Example for Gson from here https://www.techiediaries.com/java/java-11-httpclient-gson-send-http-get-parse-json-example/
		News obj = new Gson().fromJson(response.body(), News.class);

		obj.getArticles().forEach(article -> {
			System.out.println(article.getTitle());
			System.out.println(article.getAuthor());
			System.out.println("");
		});
	}

	//TODO Persist News in DataBase
	// First do functionality, then right design pattern
	// Write Controller -> Service -> Repository Class for Saving the News

}
