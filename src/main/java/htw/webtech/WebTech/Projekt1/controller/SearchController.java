package htw.webtech.WebTech.Projekt1.controller;

import org.springframework.web.bind.annotation.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class SearchController {

    @GetMapping("/searchEtf")
    public Map<String, Object> searchEtf(@RequestParam String symbol) throws Exception {
        String apiKey = System.getenv("ALPHA_VANTAGE_API_KEY");

        String url = "https://www.alphavantage.co/query?function=SYMBOL_SEARCH&keywords=" + symbol + "&apikey=" + apiKey;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new java.net.URI(url))
                .GET()
                .build();

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();

            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> result = mapper.readValue(responseBody, Map.class);
            return result;

        } catch (Exception e) {
            return new HashMap<>();
        }
    }
}
