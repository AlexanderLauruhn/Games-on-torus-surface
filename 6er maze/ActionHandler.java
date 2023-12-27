
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;

public class ActionHandler implements ActionListener{
	/**
	 * if button is pressed, the quadrangle shall rotate and the color shall change
	 * @param e
	 */
	public void actionPerformed (ActionEvent e) {
		JButton src = (JButton) e.getSource();
		for (byte i = 0; i < Labyrinth.width; i++) {
			for (byte j = 0; j < Labyrinth.length; j++) {
				if (src == Labyrinth.button[i][j]) {
					Labyrinth.waben[i][j].setContent(Labyrinth.border(Labyrinth.waben[i][j].getContent() + 1, 6));
					Labyrinth.game.setSafetyValues();
					Labyrinth.gui.repaint();
					setJumperToAnotherField();
				}
			}
		}
	}

	/**
	 * put the jumper to a field with higher safety score if any button in turned.
	 */
	public void setJumperToAnotherField(){
		Wabe jum = Labyrinth.game.getJumper().getWabe();
		ArrayList<Wabe> around = new ArrayList<Wabe>();
		around.add(jum.getLeft());//all all neighbours
		around.add(jum.getTopLeft());
		around.add(jum.getTopRight());
		around.add(jum.getRight());
		around.add(jum.getBottomRight());
		around.add(jum.getBottomLeft());
		int maxSafety=0;
		for (Wabe w: around){
			if (w.getSafety()>maxSafety){maxSafety=w.getSafety();}
		}
		if (jum.getLeft().getSafety()<maxSafety){around.remove(jum.getLeft());}//remove every neighbour wabe, if safety score is lower than maximum
		if (jum.getTopLeft().getSafety()<maxSafety){around.remove(jum.getTopLeft());}
		if (jum.getTopRight().getSafety()<maxSafety){around.remove(jum.getTopRight());}
		if (jum.getRight().getSafety()<maxSafety){around.remove(jum.getRight());}
		if (jum.getBottomRight().getSafety()<maxSafety){around.remove(jum.getBottomRight());}
		if (jum.getBottomLeft().getSafety()<maxSafety){around.remove(jum.getBottomLeft());}
		Labyrinth.game.getJumper().setWabe(around.get((int)(Math.random()*(around.size()-1))));
	}
}
