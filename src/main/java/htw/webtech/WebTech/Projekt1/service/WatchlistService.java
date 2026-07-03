package htw.webtech.WebTech.Projekt1.service;

import htw.webtech.WebTech.Projekt1.model.WatchlistEntry;
import htw.webtech.WebTech.Projekt1.repository.WatchlistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class WatchlistService {

    @Autowired
    private WatchlistRepo watchlistRepo;


    public List<WatchlistEntry> getWatchlistEntrys() {
        return watchlistRepo.findAll();
    }

    public WatchlistEntry saveWatchlistEntry(WatchlistEntry watchlistEntry){
        return watchlistRepo.save(watchlistEntry);
    }

    public WatchlistEntry putWatchlistEntry(Long id, WatchlistEntry watchlistEntry) {
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

    public void deleteWatchlistEntry(Long id) {
        if (watchlistRepo.existsById(id)) {
            watchlistRepo.deleteById(id);

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Watchlisteintrag nicht gefunden. ");
        }
    }
}
