package htw.webtech.WebTech.Projekt1.controller;

import htw.webtech.WebTech.Projekt1.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
public class SearchController {

    @Autowired
    private SearchService searchService;

    Logger logger = LoggerFactory.getLogger(SearchController.class);

    @GetMapping("/searchEtf")
    public Map<String, Object> searchEtf(@RequestParam String symbol) throws Exception {
        logger.info("search Etf for symbol {}",  symbol);
        return searchService.searchEtf(symbol);
    }
}
