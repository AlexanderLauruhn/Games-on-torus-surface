

import java.awt.event.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class KeyHandler implements KeyListener{
	static int color=0;
	boolean left=true, right =false;

	public void keyPressed(KeyEvent e) {}
	/*+
	keys are used to move the catcher
	 */
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_F){color++;}
		Wabe ausgang= Labyrinth.game.getCatcher().getWabe();
		if (e.getKeyCode()==KeyEvent.VK_LEFT  && ausgang.isAccessible(ausgang.getLeft(),0)) {
			Labyrinth.game.getCatcher().setWabe(ausgang.getLeft());}
		if (e.getKeyCode()==KeyEvent.VK_RIGHT && ausgang.isAccessible(ausgang.getRight(),3)){
			Labyrinth.game.getCatcher().setWabe(ausgang.getRight());}
		if (e.getKeyCode()==KeyEvent.VK_UP && ausgang.isAccessible(ausgang.getTopRight(),2)){
			Labyrinth.game.getCatcher().setWabe(ausgang.getTopRight());}
		if (e.getKeyCode()==KeyEvent.VK_DOWN && ausgang.isAccessible(ausgang.getBottomRight(),4)){
			Labyrinth.game.getCatcher().setWabe(ausgang.getBottomRight());}
		if (e.getKeyCode()==KeyEvent.VK_K && ausgang.isAccessible(ausgang.getTopLeft(),1)){
			Labyrinth.game.getCatcher().setWabe(ausgang.getTopLeft());}
		if (e.getKeyCode()==KeyEvent.VK_M && ausgang.isAccessible(ausgang.getBottomLeft(),5)){
			Labyrinth.game.getCatcher().setWabe(ausgang.getBottomLeft());}
		Labyrinth.gui.repaint();
	}

	public void keyTyped(KeyEvent e)    {}


}
