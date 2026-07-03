package htw.webtech.WebTech.Projekt1.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

@Service
public class SearchService {

    public Map<String, Object> searchEtf(String symbol) throws Exception {
        String apiKey = System.getenv("FINNHUB_API_KEY");  // ← Neu!

        String url = "https://finnhub.io/api/v1/search?q=" + symbol + "&token=" + apiKey;  // ← Neue URL!

        // Rest bleibt gleich...
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

            // Finnhub gibt "result" zurück, nicht "bestMatches"!
            if (!result.containsKey("result")) {
                Map<String, Object> error = new HashMap<>();
                error.put("error", "Keine Ergebnisse gefunden");
                return error;
            }
            return result;

        } catch (Exception e) {
            return new HashMap<>();
        }
    }
}
