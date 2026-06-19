package htw.webtech.WebTech.Projekt1.controller;

import htw.webtech.WebTech.Projekt1.model.WatchlistEntry;
import htw.webtech.WebTech.Projekt1.repository.WatchlistRepo;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class WatchlistController {

    @Autowired
    private WatchlistRepo watchlistRepo;

    @GetMapping("/watchlist")
    public List<WatchlistEntry> getWatchlistEntrys() {
        return watchlistRepo.findAll();
    }

    @PostMapping("/watchlist")
    public WatchlistEntry save(@RequestBody WatchlistEntry watchlistEntry) {
        return watchlistRepo.save(watchlistEntry);
    }

    @PutMapping("/watchlist/{id}")
    public WatchlistEntry putWatchlistEntry(@PathVariable long id, @RequestBody WatchlistEntry watchlistEntry) {
        Optional<WatchlistEntry> entry = watchlistRepo.findById(id);

        if (entry.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Watchlisteintrag nicht gefunden. ");
        }

        WatchlistEntry vorhandenerEintrag = entry.get();

        vorhandenerEintrag.setWkn(watchlistEntry.getWkn());
        vorhandenerEintrag.setKategorie(watchlistEntry.getKategorie());
        vorhandenerEintrag.setNotiz(watchlistEntry.getNotiz());

        return watchlistRepo.save(entry.get());
    }

    @DeleteMapping("/watchlist/{id}")
    public void  deleteWatchlistEntry(@PathVariable long id) {
        if (watchlistRepo.existsById(id)) {
            watchlistRepo.deleteById(id);

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Watchlisteintrag nicht gefunden. ");
        }
    }
}