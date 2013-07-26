/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

/**
 *
 * @author skorp
 */
public enum Position {
	BU("late"),
	SB("blinds"),
	BB("blinds"),
	EP1("early"),
	EP2("early"),
	MP1("middle"),
	MP2("middle"),
	MP3("middle"),
	CO("late");
	
	String range;
	
	private Position(String range) {
		this.range = range;
	}
}
