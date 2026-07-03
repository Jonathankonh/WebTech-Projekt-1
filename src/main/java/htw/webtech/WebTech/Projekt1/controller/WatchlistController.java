package htw.webtech.WebTech.Projekt1.controller;

import htw.webtech.WebTech.Projekt1.model.WatchlistEntry;
import htw.webtech.WebTech.Projekt1.service.WatchlistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
public class WatchlistController {

    @Autowired
    private WatchlistService watchlistService;

    Logger logger = LoggerFactory.getLogger(WatchlistController.class);

    @GetMapping("/watchlist")
    public List<WatchlistEntry> getWatchlistEntrys() {
        logger.info("get WatchlistEntrys ");
        return watchlistService.getWatchlistEntrys();
    }

    @PostMapping("/watchlist")
    public WatchlistEntry save(@RequestBody WatchlistEntry watchlistEntry) {
        logger.info("save WatchlistEntry ");
        return watchlistService.saveWatchlistEntry(watchlistEntry);
    }

    @PutMapping("/watchlist/{id}")
    public WatchlistEntry putWatchlistEntry(@PathVariable long id, @RequestBody WatchlistEntry watchlistEntry) {
        logger.info("put WatchlistEntry ");
        return watchlistService.putWatchlistEntry(id, watchlistEntry);
    }

    @DeleteMapping("/watchlist/{id}")
    public void  deleteWatchlistEntry(@PathVariable long id) {
        logger.info("delete WatchlistEntry ");
        watchlistService.deleteWatchlistEntry(id);
    }
}