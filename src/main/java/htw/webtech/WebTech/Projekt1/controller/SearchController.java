package htw.webtech.WebTech.Projekt1.controller;

import htw.webtech.WebTech.Projekt1.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
public class SearchController {

    @Autowired
    private SearchService searchService = new SearchService();

    @GetMapping("/searchEtf")
    public Map<String, Object> searchEtf(@RequestParam String symbol) throws Exception {
        return searchService.searchEtf(symbol);
    }
}
