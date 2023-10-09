package com.dhruv.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ChatService {

    @Autowired
    RestTemplate appRestClient;

    @Value("${api-key}")
    String apiKey;

    public String chatWithPaLM(String prompt) throws URISyntaxException {

        JsonObject jsonObject = new JsonObject();
        JsonObject textJson = new JsonObject();
        textJson.addProperty("text", prompt);
        jsonObject.add("prompt", textJson);

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        URI uri = new URI("https://generativelanguage.googleapis.com/v1beta2/models/text-bison-001:generateText?key=" + apiKey);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity(jsonObject.toString(), httpHeaders);
        String s = appRestClient.postForObject(uri, httpEntity, String.class);

        System.out.println(s);

        JsonElement jsonElement = JsonParser.parseString(s);
        String m = jsonElement.getAsJsonObject().getAsJsonArray("candidates").get(0).getAsJsonObject().get("output").toString();

        return m;

    }

}
