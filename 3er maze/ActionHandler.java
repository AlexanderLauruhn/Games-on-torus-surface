import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class ActionHandler implements ActionListener{
	/**
	 * if button is pressed, the quadrangle shall rotate and the color shall change
	 * @param e
	 */
	public void actionPerformed (ActionEvent e) {
		JButton src = (JButton) e.getSource();
		for (byte i = 0; i < Labyrinth.width; i++) {
			for (byte j = 0; j< Labyrinth.length; j++) {
		      if (src== Labyrinth.button[i][j]) {
				  Labyrinth.waben[i][j].setInhalt(Labyrinth.border(Labyrinth.waben[i][j].getInhalt()+1, 6));
		      }
			}
		}
		for(int i=0; i<Labyrinth.game.getZonkNumber(); i++) {
			int j = (int) (Math.random() * 3);
			if (j == 0) {
				Labyrinth.game.getZonk(i).setWabe(Labyrinth.game.getZonk(i).getWabe().getLinks());
			}
			if (j == 1) {
				Labyrinth.game.getZonk(i).setWabe(Labyrinth.game.getZonk(i).getWabe().getRechts());
			}
			if (j == 2) {
				Labyrinth.game.getZonk(i).setWabe(Labyrinth.game.getZonk(i).getWabe().getObenUnten());
			}
		}
		Labyrinth.gui.repaint();
			}
	
}
