package com.hackathon.hackathon.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.hackathon.model.Art;
import com.hackathon.hackathon.model.Bidder;
import com.hackathon.hackathon.model.Book;
import com.hackathon.hackathon.model.Item;
import com.hackathon.hackathon.service.HackathonService;


@RestController
public class HackathonController {
    private final HackathonService hackathonService;

    @Autowired
    public HackathonController(HackathonService hackathonService) {
        this.hackathonService = hackathonService;
    }

    @GetMapping("/getItems")
    public List<Item> getItems() {
        return hackathonService.getAllItems();
    }

    @GetMapping("/getItemsByType/{type}")
    public List<Item> getItemsByType(@PathVariable String type) {
        return hackathonService.getItemsByType(type);
    }

    @PostMapping("/putItem")
    public String putItem(@RequestBody Item item) {
        hackathonService.addItem(item);
        return "Item added to auction";
    }

    @PostMapping("/putBook")
    public String putBook(@RequestBody Book book) {
        hackathonService.addItem(book);
        return "Book added to auction";
    }

    @PostMapping("/putArt")
    public String putArt(@RequestBody Art art) {
        hackathonService.addItem(art);
        return "Art added to auction";
    }

    @PostMapping("/makeOffer")
    public String makeOffer(@RequestBody Map<String, Object> offerData) {
        String itemName = (String) offerData.get("itemName");
        double amount = (double) offerData.get("amount");
        LinkedHashMap lhm = (LinkedHashMap) offerData.get("bidder");
        Bidder bidder = new Bidder(lhm.get("name").toString(),lhm.get("email").toString());
        return hackathonService.makeOffer(itemName, amount, bidder);
    }

    @GetMapping("/getWinningBidder")
    public Map<String, String> getWinningBidder() {
        return hackathonService.getWinningBidder();
    }
}