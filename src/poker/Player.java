/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author skorp
 */
public abstract class Player {
	public String name;
	public int stack;
	public List<String> cards = new ArrayList();
	
	public Player(String name, int stack) {
		this.name = name;
		this.stack = stack;
	}
	
	@Override
	public String toString() {
		return name + "(" + stack + ";" + cards + ")";
		
	}
	
	public abstract Move takeAction(DealHistory dh);
}
