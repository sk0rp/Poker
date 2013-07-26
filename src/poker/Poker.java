/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

import java.util.Arrays;

/**
 *
 * @author skorp
 */
public class Poker {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		Game game = new Game(1500, 1500, 10, 20);
		game.addPlayer(new HumanPlayer("Andrzej", 1500), 1);
		game.addPlayer(new HumanPlayer("Bartek", 1500), 2);
		game.addPlayer(new HumanPlayer("Maciek", 1500), 3);
		game.addPlayer(new HumanPlayer("Michał", 1500), 5);
		game.setPositions();
		game.deal();
		System.out.println(Arrays.deepToString(game.sits));
		
		DealHistory dh = DealHistory.getInstance();
		dh.openDeal();
		dh.record(new HumanPlayer("Andrzej", 1500), Move.Type.CALL,	20);
		dh.record(new HumanPlayer("Bartek", 1500), Move.Type.RAISE, 80);
		dh.record(new HumanPlayer("Maciek", 1500), Move.Type.FOLD, 0);
		dh.nextPhase();
		dh.record(new HumanPlayer("Michał", 1500), Move.Type.CHECK, 0);
		dh.closeDeal();
		System.out.println(dh.history);
	}
}
