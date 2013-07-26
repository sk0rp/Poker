/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author skorp
 */
public class Game {
	public Object[][] sits = new Object[9][2];
	public Board board;
	public int buyinMin;
	public int buyinMax;
	public int sb;
	public int bb;
	public List<Player> players = new ArrayList<>();
	public boolean positionsSet = false;
	public int buIndex;
	public int bbIndex;
	
	public static final Map<Integer, Position[]> positionOrder = new HashMap<>();
	static {
		positionOrder.put(2, new Position[]{Position.BU, Position.BB});
		positionOrder.put(3, new Position[]{Position.BU, Position.SB, Position.BB});
		positionOrder.put(4, new Position[]{Position.BU, Position.SB, Position.BB, Position.CO});
		positionOrder.put(5, new Position[]{Position.BU, Position.SB, Position.BB, Position.MP3, Position.CO});
		positionOrder.put(6, new Position[]{Position.BU, Position.SB, Position.BB, Position.MP2, Position.MP3, Position.CO});
		positionOrder.put(7, new Position[]{Position.BU, Position.SB, Position.BB, Position.MP1, Position.MP2, Position.MP3, Position.CO});
		positionOrder.put(8, new Position[]{Position.BU, Position.SB, Position.BB, Position.EP2, Position.MP1, Position.MP2, Position.MP3, Position.CO});
		positionOrder.put(9, new Position[]{Position.BU, Position.SB, Position.BB, Position.EP1, Position.EP2, Position.MP1, Position.MP2, Position.MP3, Position.CO});
	}
	
	
	
	public Game(int buyinMin, int buyinMax, int sb, int bb) {
		this.buyinMin = buyinMin;
		this.buyinMax = buyinMax;
		this.sb = sb;
		this.bb = bb;
	}
	
	public void addPlayer(Player player, int sitNumber) {
		if (player.stack < buyinMin || player.stack > buyinMax) {
			throw new RuntimeException("Buyin out of the game's range.");
		}
		if (sits[sitNumber][0] != null) {
			throw new RuntimeException("Sit is already occupied.");
		}
		
		sits[sitNumber][0] = player;
		sits[sitNumber][1] = "Not in game";
	}
	
	public void setPositions() {
		// players counting
		for (int i = 0; i < 9; i++) {
			if (sits[i][0] instanceof Player) {
				players.add((Player)sits[i][0]);
				if (sits[i][1] == Position.BU) {
					buIndex = i;
					positionsSet = true;
				}
			}
		}
		
		if (players.size() < 2) {
			throw new RuntimeException("Insufficient number of players.");
		}
		
		for (Object[] sit: sits) {
			sit[1] = null;
		}
		
		if (positionsSet) {
			// przesuwamy pozycje
			for (int i = buIndex + 1, p = 0; ; i++) {
				if (sits[i%9][0] != null) {
					if (!(sits[i%9][1] instanceof Position)) {
						Position newPosition = positionOrder.get(players.size())[p++];
						sits[i%9][1] = newPosition;
						if (newPosition == Position.BU) {
							buIndex = i%9;
						}
					} else {
						break;
					}
				}
			}
			
		} else {
			// ustawiamy pozycje BU losowo
			Random rand = new Random();
			int buSeq = rand.nextInt(players.size());
			
			/*
			 * Przechodzimy wszystkie elementy tablicy sits zwracając uwagę
			 * jedynie na elementy z graczami; czekamy az buSeq spadnie do 0
			 * i od tego momentu ustawiamy pozycje graczy
			 */
			for (int i = 0, p = 0; ; i++) {
				if (sits[i%9][0] != null) {
					if (buSeq-- <= 0 && !(sits[i%9][1] instanceof Position)) {
						sits[i%9][1] = positionOrder.get(players.size())[p++];
					} else if (buSeq < 0) {
						break;
					}
				}
			}
			
		}
	}
	
	
	public void deal() {
		for (Object[] sit: sits) {
			if (sit[0] instanceof Player) {
				((Player)sit[0]).cards.clear();
			}
		}
		CardsManager cm = CardsManager.getInstance();
		cm.reset();
		for (int i = buIndex + 1; ; i++) {
			if (sits[i%9][0] instanceof Player) {
				Player player = (Player)sits[i%9][0];
				if (player.cards.size() < 2) {
					player.cards.add(cm.getCard());
				} else {
					break;
				}
			}
		}
	}
	
	
	public void preflop() {
		DealHistory dh = DealHistory.getInstance();
		
		// Zaklady w ciemno
		for (int i = buIndex; ; i++) {
			if (sits[i%9][1] == Position.BU && players.size() == 2 || sits[i%9][1] == Position.SB) {
				Player player = (Player)sits[i%9][0];
				if (player.stack < sb) {
					dh.record(player, Move.Type.SB, sb - player.stack);
					player.stack = 0;
				} else {
					dh.record(player, Move.Type.SB, sb);
					player.stack -= sb;
				}
			} else if (sits[i%9][1] == Position.BB) {
				Player player = (Player)sits[i%9][0];
				if (player.stack < bb) {
					dh.record(player, Move.Type.BB, bb - player.stack);
					player.stack = 0;
				} else {
					dh.record(player, Move.Type.BB, bb);
					player.stack -= bb;
				}
				bbIndex = i%9;
				break;
			}
		}
		
		for (int i = bbIndex + 1; ; i++) {
			if (sits[i%9][0] instanceof Player) {
				Player player = (Player)sits[i%9][0];
				
			}
		}
	}
}
