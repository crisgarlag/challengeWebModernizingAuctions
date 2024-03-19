package com.hackathon.hackathon.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hackathon.hackathon.model.Art;
import com.hackathon.hackathon.model.Bidder;
import com.hackathon.hackathon.model.Book;
import com.hackathon.hackathon.model.Item;

@Configuration
public class MockDataConfig {

    @Bean
    public List<Item> items() {
        List<Item> itemList = new ArrayList<>();
        // Agregar libros y obras de arte alternativamente
        itemList.add(new Book("Orgullo y prejuicio", 12.99, new Bidder("John Doe", "john@example.com"), "Book", "Mark Twain", 1813));
        itemList.add(new Art("La persistencia de la memoria", 599.99, new Bidder("Alice Johnson", "alice@example.com"), "Art", "Pablo Picasso", 1931));
        itemList.add(new Book("Matar a un ruiseñor", 14.99, new Bidder("David Brown", "david@example.com"), "Book", "Ernest Hemingway", 1960));
        itemList.add(new Art("La noche estrellada", 799.99, new Bidder("Emily Taylor", "emily@example.com"), "Art", "Claude Monet", 1889));
        itemList.add(new Book("Cien años de soledad", 19.99, new Bidder("Michael White", "michael@example.com"), "Book", "Gabriel García Márquez", 1967));
        itemList.add(new Art("El grito", 900.0, new Bidder("Olivia Davis", "olivia@example.com"), "Art", "Edvard Munch", 1893));
        itemList.add(new Book("1984", 15.99, new Bidder("Daniel Martinez", "daniel@example.com"), "Book", "George Orwell", 1949));
        itemList.add(new Art("El nacimiento de Venus", 999.99, new Bidder("Sophia Miller", "sophia@example.com"), "Art", "Sandro Botticelli", 1484));        
        itemList.add(new Book("El hobbit", 10.99, new Bidder("William Johnson", "william@example.com"), "Book", "J.R.R. Tolkien", 1937));
        itemList.add(new Art("La última cena", 1099.99, new Bidder("Ava Wilson", "ava@example.com"), "Art", "Leonardo da Vinci", 1498));
        
        return itemList;
    }
}
