/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

/**
 *
 * @author skorp
 */
public class Move {
	public enum Type {
		SB, BB, FOLD, CHECK, CALL, RAISE
	}
	public Type type;
	public int value;
	
	public Move(Type type, int value) {
		this.type = type;
		if (type != Type.FOLD && type != Type.CHECK) {
			this.value = value;
		}
		
	}
	
	@Override
	public String toString() {
		if (type != Type.FOLD && type != Type.CHECK) {
			return type + "(" + value + ")";
		}
		return type.toString();
	}
}
