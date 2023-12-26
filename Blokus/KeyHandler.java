import java.awt.event.*;
public class KeyHandler implements KeyListener{

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_UP){
			Blokus.spiel.player.getElement().turnAround();
		}
		if (e.getKeyCode()==KeyEvent.VK_LEFT){Blokus.spiel.player.getElement().mirrorH();}//horizontal mirroring
		if (e.getKeyCode()==KeyEvent.VK_RIGHT){Blokus.spiel.player.getElement().mirrorV();}//vertical mirroring
		if (e.getKeyCode()==KeyEvent.VK_H){Gui.help++;}//help
		Blokus.setupAccessiblePosition(Blokus.spiel.player);
		Blokus.gui.repaint();
	}
	
	
	public void keyReleased(KeyEvent e) {	
			Blokus.gui.repaint();
		}
	
	public void keyTyped(KeyEvent e)    {
		Blokus.gui.repaint();
		}

	
}
