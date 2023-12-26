import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class ActionHandler implements ActionListener{
	boolean lost;//if the player choose a button with mine

	public void actionPerformed (ActionEvent e) {
		if (Main.game.difficult.isSelected()) {Board.ratio =0.3f;}
		if (Main.game.medium.isSelected()) {Board.ratio =0.2f;}
		if (Main.game.easy.isSelected()) {Board.ratio =0.1f;}
		if(e.getSource() == Main.newGame) {//start a new game with a new board
			lost=false;
			Main.game.board=new Board();
			Main.game.requestFocus();
			for (byte i = 0; i < Main.size; i++) {
				for (byte j = 0; j < Main.size; j++) {
					Main.game.board=new Board();
					Main.button[i][j].setText("");
					Main.button[i][j].setBackground(new Color(255, (int)(Math.random()*50+180), (int)(Math.random()*50+180)));
					Main.game.board.setShown(false,i,j);
				}
			}
		}
		JButton src = (JButton) e.getSource();//set text and color of button, if not mine
		for (byte i = 0; i < Main.size; i++) {
			for (byte j = 0; j < Main.size; j++) {
				 if (src==Main.button[i][j] && !lost) {					 
					if (!Main.game.board.getMine(i,j)) {	
						showValue(i,j);
						Main.game.board.won();
						}
					else {lost=true;}
				 }
		     }
	 }
		if (lost) {
			for (byte i = 0; i < Main.size; i++) {
				for (byte j = 0; j < Main.size; j++) {
					if (Main.game.board.getMine(i, j)) {Main.button[i][j].setBackground(new Color(20,30,20));}
				}
			}
		}
		Main.game.requestFocus();
	}
	
	/**
	 * set the color of a button according to safety value
	 * @param safety
	 * @return
	 */
	private static Color chooseColor(int safety) {
		switch (safety) {
		case 0: return new Color(100,100,255);
		case 1: return new Color(100,170,255);
		case 2: return new Color(100,255,255);
		case 3: return new Color(100,255,150);
		case 4: return new Color(170,255,100);
		case 5: return new Color(255,255,100);
		case 6: return new Color(255,155,100);
		case 7: return new Color(255,100,100);
		default: return new Color(255,0,100);
		}
	}
	private void showValue(int i, int j) {
		Main.button[i][j].setText(Integer.toString(Main.game.board.getSafety(i,j)));
		Main.button[i][j].setBackground(chooseColor(Main.game.board.getSafety(i,j)));
		Main.game.board.setShown(true,i,j);
		if(Main.game.board.getSafety(i,j)==0) {
			if (!Main.game.board.getShown(Board.border(i-1, Main.size),j)) showValue(Board.border(i-1, Main.size),j);
			if (!Main.game.board.getShown(Board.border(i+1, Main.size),j))showValue(Board.border(i+1, Main.size),j);
			
			if (!Main.game.board.getShown(i,Board.border(j-1, Main.size))) showValue(i,Board.border(j-1, Main.size));
			if (!Main.game.board.getShown(i,Board.border(j+1, Main.size))) showValue(i,Board.border(j+1, Main.size));	
			
			if (!Main.game.board.getShown(Board.border(i-1, Main.size),Board.border(j-1, Main.size))) showValue(Board.border(i-1, Main.size),Board.border(j-1, Main.size));
			if (!Main.game.board.getShown(Board.border(i-1, Main.size),Board.border(j+1, Main.size))) showValue(Board.border(i-1, Main.size),Board.border(j+1, Main.size));
			if (!Main.game.board.getShown(Board.border(i+1, Main.size),Board.border(j-1, Main.size))) showValue(Board.border(i+1, Main.size),Board.border(j-1, Main.size));
			if (!Main.game.board.getShown(Board.border(i+1, Main.size),Board.border(j+1, Main.size))) showValue(Board.border(i+1, Main.size),Board.border(j+1, Main.size));
		}
	}
}
