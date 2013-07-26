/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

/**
 *
 * @author skorp
 */
public class PlayerMove extends Move {
	public Player player;
	
	public PlayerMove(Player player, Type type, int value) {
		super(type, value);
		this.player = player;
	}
	
	@Override
	public String toString() {
		if (type != Type.FOLD && type != Type.CHECK) {
			return player.name + ": " + type + "(" + value + ")";
		}
		return player.name + ": " + type;
	}
			
}
