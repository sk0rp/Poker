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
public class DealHistory {
	public List<List> history = new ArrayList<>();
	public List<List> currentDeal;
	public List<PlayerMove> currentPhase;
	public int lastBet = 0;
	private boolean canCheck;
	
	private final static DealHistory instance = new DealHistory();
	
	public static DealHistory getInstance() {
		return instance;
	}
	
	private DealHistory() { }
	
	public void openDeal() {
		currentDeal = new ArrayList<>();
		currentPhase = new ArrayList<>();
		canCheck = false;
	}
	
	public void nextPhase() {
		currentDeal.add(currentPhase);
		currentPhase = new ArrayList<>();
		canCheck = true;
	}
		
	public void closeDeal() {
		currentDeal.add(currentPhase);
		history.add(currentDeal);
	}
	
	public void record(Player player, Move.Type moveType, int value) {
		currentPhase.add(new PlayerMove(player, moveType, value));
		if (moveType == Move.Type.RAISE) {
			canCheck = false;
		}
	}
	
	public int toCall(Player player) {
		
	}
	
	
	public boolean playersFinished(List<Player> players) {
		boolean raise = false;
		int raiseValue
		for (PlayerMove pm: currentPhase) {
			if (pm.type == Move.Type.BB || pm.type == Move.Type.RAISE) {
				raise = true;
			}
		}
		for (Player player: players) {
			
		}
	}
	
	public boolean canCheck() {
		return canCheck;
	}
}
