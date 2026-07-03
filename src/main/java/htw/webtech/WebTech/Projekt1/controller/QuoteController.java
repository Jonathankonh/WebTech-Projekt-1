package htw.webtech.WebTech.Projekt1.controller;

import htw.webtech.WebTech.Projekt1.service.QuoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    Logger logger = LoggerFactory.getLogger(QuoteController.class);

    @GetMapping("/quote")
    public Map<String, Object> getQuote(@RequestParam String symbol) throws Exception {
        logger.info("Getting Quote for Symbol {}",  symbol);
        return  quoteService.getQuote(symbol);
    }
}
