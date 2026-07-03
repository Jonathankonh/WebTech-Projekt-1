package htw.webtech.WebTech.Projekt1.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class QuoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("should return 200 when finds nothing")
    void should_return_200() throws Exception {
        mockMvc.perform(
                get("/quote").param("symbol", "SPY")
        ) .andExpect(status().isOk());
    }
}

