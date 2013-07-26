/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

/**
 *
 * @author skorp
 */
public class ComputerPlayer extends Player {
	public ComputerPlayer(String name, int stack) {
		super(name, stack);
	}

	@Override
	public Move takeAction(DealHistory dh) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}