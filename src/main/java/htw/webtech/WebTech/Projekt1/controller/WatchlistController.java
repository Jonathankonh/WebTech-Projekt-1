package htw.webtech.WebTech.Projekt1.controller;

import htw.webtech.WebTech.Projekt1.model.WatchlistEntry;
import htw.webtech.WebTech.Projekt1.service.WatchlistService;
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

    @GetMapping("/watchlist")
    public List<WatchlistEntry> getWatchlistEntrys() {
        return watchlistService.getWatchlistEntrys();
    }

    @PostMapping("/watchlist")
    public WatchlistEntry save(@RequestBody WatchlistEntry watchlistEntry) {
        return watchlistService.saveWatchlistEntry(watchlistEntry);
    }

    @PutMapping("/watchlist/{id}")
    public WatchlistEntry putWatchlistEntry(@PathVariable long id, @RequestBody WatchlistEntry watchlistEntry) {
        return watchlistService.putWatchlistEntry(id, watchlistEntry);
    }

    @DeleteMapping("/watchlist/{id}")
    public void  deleteWatchlistEntry(@PathVariable long id) {
        watchlistService.deleteWatchlistEntry(id);
    }
}