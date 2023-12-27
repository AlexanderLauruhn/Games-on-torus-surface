

import java.awt.event.*;
import java.awt.event.KeyListener;
public class KeyHandler implements KeyListener{

	public void keyPressed(KeyEvent e) {}

	/**
	 * movement of the catcher by keys
	 * @param e
	 */
	public void keyReleased(KeyEvent e) {
		zonkCollision();
		Wabe ausgang= Labyrinth.game.getCatcher().getWabe();
		if (e.getKeyCode()==KeyEvent.VK_LEFT && ausgang.isAccessible(ausgang.getLinks().getInhalt(),0)) {
			Labyrinth.game.getCatcher().setWabe(ausgang.getLinks());}
		if (e.getKeyCode()==KeyEvent.VK_RIGHT && ausgang.isAccessible(ausgang.getRechts().getInhalt(),1)) {
			Labyrinth.game.getCatcher().setWabe(ausgang.getRechts());}
		if (e.getKeyCode()==KeyEvent.VK_UP && ((ausgang.getX()+ausgang.getY())%2==0)&& ausgang.isAccessible(ausgang.getObenUnten().getInhalt(),2)) {
			Labyrinth.game.getCatcher().setWabe(ausgang.getObenUnten());}
		if (e.getKeyCode()==KeyEvent.VK_DOWN && ((ausgang.getX()+ausgang.getY())%2==1)&& ausgang.isAccessible(ausgang.getObenUnten().getInhalt(),2)) {
			Labyrinth.game.getCatcher().setWabe(ausgang.getObenUnten());}
		Labyrinth.gui.repaint();

	}

	public void keyTyped(KeyEvent e)    {}

	/**
	 * when catcher meets a zonk
	 */
	public void zonkCollision() {
		for(int i=0; i<Labyrinth.game.getZonkNumber(); i++) {
			if (Labyrinth.game.getZonk(i).getWabe()==Labyrinth.game.getCatcher().getWabe()) {
				Labyrinth.points++;
				Labyrinth.game.getZonk(i).setWabe(Labyrinth.waben[(int)(Math.random()*Labyrinth.width)][(int)(Math.random()*Labyrinth.length)]);
				Labyrinth.label.setText(Labyrinth.points+" Points");
			}
		}
	}
}
