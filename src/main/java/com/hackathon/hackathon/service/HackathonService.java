package com.hackathon.hackathon.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackathon.hackathon.model.Bidder;
import com.hackathon.hackathon.model.Item;

/**
 * Para el desarrollo de la prueba:
 * 
 * (La lista de items ya viene inyectada en el servicio procedente del fichero
 * MockDataConfig.java)
 * 
 * - Completa el cuerpo del método getItemsByType(String type) que recibiendo el
 * parámetro type, devuelva una lista de ítems del tipo especificado.
 *
 * - Completa el cuerpo del método makeOffer(String itemName, double amount,
 * Bidder bidder), que al recibir el nombre del ítem (itemName), la cantidad de
 * la oferta (amount), y el postor que realiza la oferta (bidder). Comprueba si
 * el ítem especificado por itemName existe en la lista de ítems: # Si el ítem
 * no se encuentra, devuelve la constante ITEM_NOT_FOUND. # Si el ítem se
 * encuentra, compara la oferta (amount) con la oferta más alta actual del ítem
 * (highestOffer). # Si la oferta es mayor que la oferta más alta, actualiza la
 * oferta más alta y el postor actual del ítem y devuelve la constante
 * OFFER_ACCEPTED. # Si la oferta es igual o menor que la oferta más alta,
 * devuelve la constante OFFER_REJECTED.
 * 
 * - Completa el cuerpo del método getWinningBidder() que debe devolver un Map
 * de los Items en los que se haya pujado (que existe un Bidder) y cuyo valor
 * sea el nombre del Bidder que ha pujado.
 */

@Service
public class HackathonService {

	private static String ITEM_NOT_FOUND = "Item not found";
	private static String OFFER_ACCEPTED = "Offer accepted";
	private static String OFFER_REJECTED = "Offer rejected";

	private List<Item> items;

	@Autowired
	public HackathonService(List<Item> items) {
		this.items = items;
	}

	public List<Item> getAllItems() {
		return new ArrayList<>(items);
	}
	
	/**
	 * Obtiene todos los items de la lista del mismo tipo
	 * @param type es el tipo de los items que se quiere obtener
	 * @return una Lista con los elementos del tipo pasado como parametro
	 */
	public List<Item> getItemsByType(String type) {
		
		//Se filtra los elementos de la lista items si cumplen con la condicion de coincidir la propiedad
		//type con la pasada como parametro
		List<Item> itemsByType = items.stream().filter(e -> e.getType().equals(type)).toList();

		return itemsByType;
	}

	public void addItem(Item item) {
		items.add(item);
	}

	/**
	 * Se encarga de la gestion de las pujas realizadas en la casa de subastas
	 * @param itemName del item sobre el que se puja
	 * @param amount cantidad de la puja
	 * @param bidder nombre de quien realiza la puja
	 * @return el resultado de la puja
	 */
	public String makeOffer(String itemName, double amount, Bidder bidder) {
		//Se filtra el primer elemento de la lista items si hay items que coincidan con el nombre 
		//pasado como parametro y se almacena en un Optionals
		Optional<Item> findItem = items.stream().filter(e -> e.getName().equals(itemName)).findFirst();
		
		//Se comprueba si el optinals esta vacio
		if (findItem.isEmpty())
			return ITEM_NOT_FOUND;
		//En caso de tener un item se comprueba si la oferta es mas baja que la actual
		if (amount <= findItem.get().getHighestOffer())
			return OFFER_REJECTED;
		//Al ser la oferta mas alta que la actual se modifican los datos de la cantidad
		//ofertada y del actual bidder.
		items = items.stream().map(e -> {
			if (e.getName().equals(itemName)) {
				e.setHighestOffer(amount);
				e.setCurrentBidder(bidder);
			}
			return e;
		}).toList();

		return OFFER_ACCEPTED;
	}

	/**
	 * @return Un map con los items en los que se hay pujado siendo la key el nombre del item y el valor
	 * es nombre del Bidder que ha pujado.
	 */
	public Map<String, String> getWinningBidder() {

		Map<String, String> winningBidder = new HashMap<String, String>();
		
		//Bucle y se compueba los items cuyo currentBidder es distinto de null
		// y en caso de ser positivo se añade al map que será devuelto
		for (Item item : items) {
			if (item.getCurrentBidder() != null) {
				winningBidder.put(item.getName(), item.getCurrentBidder().getName());
			}
		}

		return winningBidder;
	}
}
