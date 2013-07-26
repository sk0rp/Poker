/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

import java.util.Scanner;

/**
 *
 * @author skorp
 */
public class HumanPlayer extends Player {
	public HumanPlayer(String name, int stack) {
		super(name, stack);
	}

	@Override
	public Move takeAction(DealHistory dh) {
		if (dh.canCheck()) {
			System.out.println("Wybierz ruch: (f)old, c(h)eck, (c)all, (r)aise");
		}
		System.out.println("Wybierz ruch: (f)old, (c)all, (r)aise");
		Scanner sc = new Scanner(System.in);
		String option = sc.next();
		int value = 0;
		Move.Type mt = Move.Type.FOLD;
		switch (option) {
			case "r":
				System.out.println("O ile chcesz podnieść zakład?");
				value = sc.nextInt();
				mt = Move.Type.RAISE;
				break;
			case "c":
				mt = Move.Type.CALL;
				break;
			case "f":
				mt = Move.Type.FOLD;
				break;
			default:
				if (dh.canCheck()) {
					mt = Move.Type.CHECK;
				} else {
					mt = Move.Type.FOLD;
				}
		}
		return new Move(mt, value);
 	}
	
	
}
