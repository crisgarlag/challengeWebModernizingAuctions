package com.hackathon.hackathon;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.hackathon.JsonEvent;
import com.hackathon.TestResult;
import com.hackathon.hackathon.model.Art;
import com.hackathon.hackathon.model.Bidder;
import com.hackathon.hackathon.model.Item;
import com.hackathon.hackathon.model.Offer;

import jakarta.annotation.PostConstruct;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc

public class HackathonControllerTest {


    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private List<Item> items;

    private static List<TestResult> testResults = new ArrayList<>();

    private static ApplicationContext contextoTest = null;

    /**
     * 
     * Se evalúa la implementación del constructor, getter y setter de la clase Art y el código necesario para que extienda de la clase Item
     * 
     * **/
    @Test
    public void testArt_Success() {
        try {
            String name = "Guernica";
            double startingPrice = 20000.0;
            String artist = "Picasso";
            int yearCreated = 1937;
            Bidder currentBidder = new Bidder("Susan Smith", "john@example.com");
            String type = "Art";

            Art art = new Art(name, startingPrice, currentBidder, type, artist, yearCreated);

            assertEquals(name, art.getName());
            assertEquals(startingPrice, art.getInitialPrice());
            assertEquals(currentBidder, art.getCurrentBidder());
            assertEquals(type, art.getType());
            assertEquals(artist, art.getArtist());
            assertEquals(yearCreated, art.getYearCreated());
            
            assertTrue(art instanceof Item, "El objeto Art debe ser hijo de la clase Item");

            String newArtist = "Van Gogh";
            int newYearCreated = 1889;
            art.setArtist(newArtist);
            art.setYearCreated(newYearCreated);

            assertEquals(newArtist, art.getArtist());
            assertEquals(newYearCreated, art.getYearCreated());

            testResults.add(new TestResult("testArt_Success", 0, 1));
        } catch (java.lang.AssertionError e) {
            testResults.add(new TestResult("testArt_Success", 0, 0));
        }
    }

    /**
     * 
     * Se evalúa la implementación del método getItemsByType, que devolverá un objeto del tipo indicado, que será una instáncia de tipo (Book)
     * 
     * **/
    @Test
    public void testGetItemsByType() throws Exception {
    	try {
            Item bookItem = items.get(0);

            mockMvc.perform(get("/getItemsByType/{type}", bookItem.getType()))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$", hasSize(5)))
                    .andExpect(jsonPath("$[0].name", is(bookItem.getName())))
                    .andExpect(jsonPath("$[0].initialPrice", is(bookItem.getInitialPrice())))
                    .andExpect(jsonPath("$[0].currentBidder.name", is(bookItem.getCurrentBidder().getName())))
                    .andExpect(jsonPath("$[0].type", is(bookItem.getType())));

            mockMvc.perform(get("/getItemsByType/{type}", "Fashion"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$", hasSize(0)));

            mockMvc.perform(get("/getItemsByType/{type}", "InvalidType"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$", hasSize(0)));

            testResults.add(new TestResult("testGetItemsByType", 0, 1));
    	} catch (java.lang.AssertionError e) {
            testResults.add(new TestResult("testGetItemsByType", 0, 0));
    	}
    }

    /**
     * 
     * Se evalúa la implementación del método makeOffer, con las casuísticas ya definidas 
     * 
     * **/
    @Test
    public void testMakeOffer_Success() throws Exception {
        try {
            // Obtener un elemento existente de MockDataConfig
            Item item = items.get(0); // Suponiendo que obtienes el primer elemento de la lista

            // Construir el objeto Offer usando los datos del elemento obtenido
            Bidder bidder = new Bidder("Olivia Davis", "olivia@example.com");

            // Caso 1: Oferta mayor que la oferta más alta actual
            double higherOffer = item.getInitialPrice() + 100; // Aumentar el precio inicial en 100
            Offer higherOfferObj = new Offer(item.getName(), higherOffer, bidder);
            Gson gson = new Gson();
            String higherOfferJson = gson.toJson(higherOfferObj);

            mockMvc.perform(post("/makeOffer")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(higherOfferJson))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Offer accepted"));

            // Caso 2: Oferta igual a la oferta más alta actual
            Offer equalOfferObj = new Offer(item.getName(), higherOffer, bidder); // Oferta igual a la anterior
            String equalOfferJson = gson.toJson(equalOfferObj);

            mockMvc.perform(post("/makeOffer")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(equalOfferJson))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Offer rejected"));

            // Caso 3: Oferta menor que la oferta más alta actual
            double lowerOffer = item.getInitialPrice() - 50; // Reducir el precio inicial en 50
            Offer lowerOfferObj = new Offer(item.getName(), lowerOffer, bidder);
            String lowerOfferJson = gson.toJson(lowerOfferObj);

            mockMvc.perform(post("/makeOffer")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(lowerOfferJson))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Offer rejected"));


            // Si todos los assert pasan, agregar el resultado del test a testResults
            testResults.add(new TestResult("testMakeOffer_Success", 0, 1));
    	} catch (java.lang.AssertionError e) {
            // Si algún assert falla, agregar el resultado del test con valor 0 a testResults
            testResults.add(new TestResult("testMakeOffer_Success", 0, 0));
        }
    }
    
    /**
     * 
     * Se evalúa la implementación del método getBidder, que devolverá un objeto del tipo indicado, que será una instáncia de tipo (Book)
     * 
     * **/
    @Test
    public void testGetWinningBidder() throws Exception {
        try {
            // Construir el mapa esperado de nombres de ítems y postores utilizando la lista de ítems directamente
            Map<String, String> expectedBidderMap = new HashMap<>();
            for (Item item : items) {
                if (item.getCurrentBidder() != null) {
                    expectedBidderMap.put(item.getName(), item.getCurrentBidder().getName());
                }
            }

            // Act & Assert para el caso exitoso
            MvcResult result = mockMvc.perform(get("/getWinningBidder"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andReturn();

            // Obtener el cuerpo de la respuesta y convertirlo a un objeto Map
            String responseContent = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> actualBidderMap = objectMapper.readValue(responseContent, new TypeReference<Map<String, String>>() {});

            // Comparar los mapas actual y esperado
            assertEquals(expectedBidderMap, actualBidderMap);

            // Si todos los assert pasan, agregar el resultado del test a testResults
            testResults.add(new TestResult("testGetWinningBidder", 0, 1));
    	} catch (java.lang.AssertionError e) {
            // Si algún assert falla, agregar el resultado del test con valor 0 a testResults
            testResults.add(new TestResult("testGetWinningBidder", 0, 0));
        }
    }


    @AfterAll
    public static void publishTestResults() {
        testResults.forEach(result -> {
            boolean valueBoolean = result.getValue() != 0;
            System.out.println(String.format("Test: %s, Resultado: %s", result.getName(), valueBoolean));
        });
    }

    @PostConstruct
    protected void init() {
        contextoTest = this.applicationContext;
    }




}

