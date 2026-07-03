package htw.webtech.WebTech.Projekt1.controller;

import htw.webtech.WebTech.Projekt1.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @GetMapping("/quote")
    public Map<String, Object> getQuote(@RequestParam String symbol) throws Exception {
        return  quoteService.getQuote(symbol);
    }
}
