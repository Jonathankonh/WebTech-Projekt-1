package htw.webtech.WebTech.Projekt1.controller;

import htw.webtech.WebTech.Projekt1.model.WatchlistEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import htw.webtech.WebTech.Projekt1.repository.WatchlistRepo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;  // neuer Pfad


@SpringBootTest
@AutoConfigureMockMvc
class WatchlistControllerTest {

   @Autowired
   private MockMvc mockMvc;
    // Spring initiert MockMvc automatisch (Simuliert GET/POST/PUT/DELETE)

   @Autowired
    private WatchlistRepo watchlistRepo;

   // löscht alle Testdateien, damit alle Tests unabhängig voneinander sind
   @BeforeEach
   void setUp() {
       watchlistRepo.deleteAll();

       var watchlistEntry1 = new WatchlistEntry();
       watchlistEntry1.setWkn("123FRT");
       watchlistEntry1.setKategorie("USA");
       watchlistEntry1.setNotiz("Das ist der erste Test");
       watchlistRepo.save(watchlistEntry1);

       var watchlistEntry2 = new WatchlistEntry();
       watchlistEntry2.setWkn("123456");
       watchlistEntry2.setKategorie("DE");
       watchlistEntry2.setNotiz("Das ist der zweite Test");
       watchlistRepo.save(watchlistEntry2);
   }

   @Test
   @DisplayName("Test Add Watchlist")
   void addWatchlist() throws Exception {
       String articleJson =  "{\"wkn\": \"123FRT\", \"kategorie\": \"USA\", \"notiz\": \"Das ist der erste Test\"}";

       mockMvc.perform(
               post("/watchlist")
               .contentType(MediaType.APPLICATION_JSON)
               .content(articleJson)
       ).andExpect(status().isOk())
               .andExpect(jsonPath("$.wkn").value("123FRT"));
   }

   @Test
   @DisplayName("Test Delete Watchlist")
   void deleteWatchlist() throws Exception {
       var watchlistEntry1 = new WatchlistEntry();
       watchlistEntry1.setWkn("123456");
       watchlistEntry1.setKategorie("Europa");
       watchlistRepo.save(watchlistEntry1);

       mockMvc.perform(
               delete("/watchlist/" + watchlistEntry1.getId())
       ) .andExpect(status().isOk());
   }

   @Test
   @DisplayName("Test Update Watchlist")
   void updateWatchlist() throws Exception {
       var watchlistEntry1 = new WatchlistEntry();
       watchlistEntry1.setWkn("456789");
       watchlistEntry1.setKategorie("Tech");
       watchlistRepo.save(watchlistEntry1);

        String updateJson = "{\"wkn\": \"456789\", \"kategorie\": \"Tech\"}";

        mockMvc.perform(
                put("/watchlist/" + watchlistEntry1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateJson)
        )   .andExpect(status().isOk())
                .andExpect(jsonPath("$.kategorie").value("Tech"));
   }

   @Test
   @DisplayName("Test Get Watchlist")
   void getWatchlist() throws Exception {
       mockMvc.perform(get("/watchlist"))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.size()").value(2))
                       .andExpect(jsonPath("$[0].wkn").value("123FRT"))
                       .andExpect(jsonPath("$[1].wkn").value("123456"));

   }

    @Test
    @DisplayName("should throw an exception when id is not found")
    void should_return_404() throws Exception {
        mockMvc.perform(
                        delete("/watchlist/999991"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("should throw 404 when watchlistEntry is not found")
    void should_return_404_put() throws Exception {
        String watchlistEntry = "{\"wkn\": \"...\", \"kategorie\": \"...\", \"notiz\":\"...\"}";
        mockMvc.perform(
                        put("/watchlist/999991")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(watchlistEntry)
                )
                .andExpect(status().isNotFound());

    }

}
