/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author skorp
 */
public final class CardsManager {
	
	private String[] allCards = {
			"2c", "2d", "2h", "2s",
			"3c", "3d", "3h", "3s",
			"4c", "4d", "4h", "4s",
			"5c", "5d", "5h", "5s",
			"6c", "6d", "6h", "6s",
			"7c", "7d", "7h", "7s",
			"8c", "8d", "8h", "8s",
			"9c", "9d", "9h", "9s",
			"Tc", "Td", "Th", "Ts",
			"Jc", "Jd", "Jh", "Js",
			"Qc", "Qd", "Qh", "Qs",
			"Kc", "Kd", "Kh", "Ks",
			"Ac", "Ad", "Ah", "As"
	};
	
	private LinkedList deck;
	
	private final static CardsManager instance = new CardsManager();
	
	public static CardsManager getInstance() {
		return instance;
	}
	
	private CardsManager() {
		reset();
	}
	
	public void reset() {
		deck = new LinkedList(Arrays.asList(allCards));
		Collections.shuffle(deck);
	}
	
	public LinkedList getDeck() {
		return deck;
	}
	
	public String getCard() {
		return (String) deck.pop();
	}
	
	public List getCards(int amount) {
		if (amount > deck.size()) {
			throw new RuntimeException("Deck size exceeded");
		}
		List cards = new LinkedList(deck.subList(0, amount));
		deck.subList(0, amount).clear();
		return cards;
	}
	
	
	
}
