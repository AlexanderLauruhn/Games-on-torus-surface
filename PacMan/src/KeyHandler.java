import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
public class KeyHandler implements KeyListener{
	
	public void keyPressed(KeyEvent e) {
		catchDiamonds();//catch diamonds
		Figure p = Main.pacman;// move the pacman with keys
		if (e.getKeyCode()==KeyEvent.VK_UP && Main.main.labyrinth[p.getX()][p.getY()].moveUp()) {
			p.setY(Main.border(p.getY()-1,Main.size));}
		else if (e.getKeyCode()==KeyEvent.VK_DOWN && Main.main.labyrinth[p.getX()][p.getY()].moveDown()) {
			p.setY(Main.border(p.getY()+1,Main.size));}
		else if (e.getKeyCode()==KeyEvent.VK_LEFT && Main.main.labyrinth[p.getX()][p.getY()].moveLeft()) {
			p.setX(Main.border(p.getX()-1,Main.size));}
		else if (e.getKeyCode()==KeyEvent.VK_RIGHT && Main.main.labyrinth[p.getX()][p.getY()].moveRight())  {
			p.setX(Main.border(p.getX()+1,Main.size));}
		catchDiamonds();
		Main.gui.repaint();
	}
	/**
	 * change size or set new games by keys 
	 */
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_S) {
			Main.randomMax=2+Main.border(Main.randomMax-1, 4)
			;}
		else if (e.getKeyCode()==KeyEvent.VK_PLUS && Main.size<101) {
			Main.size++;
			changeSize();}
		else if (e.getKeyCode()==KeyEvent.VK_MINUS && Main.size>2) {
			Main.size--;
			changeSize();}
		else if (e.getKeyCode()==KeyEvent.VK_ENTER) {
			Main.createNewLabyrinth();}
		Main.gui.repaint();
		}
	public void keyTyped(KeyEvent e)    {}
	/**
	 * set a new game with another size
	 */
	public void changeSize() {
		Main.main.labyrinth = new Position[Main.size][Main.size];
		Main.main.setPositions();
		Main.createNewLabyrinth();
	}
	/**
	 * determines if the pacman has the same coordinates like one of the diamonds
	 * if yes, remove this diamond
	 */
	public void catchDiamonds() {
		for (int i=0; i<Main.diamonds.size(); i++) {
			if(Main.diamonds.get(i).getX()==Main.pacman.getX() &&
				Main.diamonds.get(i).getY()==Main.pacman.getY()) {
				Main.diamonds.remove(i);
			}	
		}
	}
}