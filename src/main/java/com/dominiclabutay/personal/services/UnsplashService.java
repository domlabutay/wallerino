package com.dominiclabutay.personal.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class UnsplashService {
    private List<String> images;
    private String api_key = "";
    private String page = "1";

    public String fetchImageData(String query) throws IOException, InterruptedException {
        StringBuilder builder = new StringBuilder("https://api.unsplash.com/search/photos");
        builder.append("?page=");
        builder.append(URLEncoder.encode(page,StandardCharsets.UTF_8.toString()));
        builder.append("&query=");
        builder.append(URLEncoder.encode(query,StandardCharsets.UTF_8.toString()));
        builder.append("&client_id=");
        builder.append(URLEncoder.encode(api_key, StandardCharsets.UTF_8.toString()));

        URI uri = URI.create(builder.toString());
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(httpResponse.body());

        System.out.println(jsonNode.get("results").get(1).get("urls").get("full").asText());

        return jsonNode.get("results").get(1).get("urls").get("full").asText();
    }

}
