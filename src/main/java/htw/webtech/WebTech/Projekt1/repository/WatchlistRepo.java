package htw.webtech.WebTech.Projekt1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import htw.webtech.WebTech.Projekt1.model.WatchlistEntry;

public interface WatchlistRepo extends JpaRepository<WatchlistEntry, Long> {

}
