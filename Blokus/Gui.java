import javax.swing.*;
import java.awt.*;
public class Gui extends JPanel {
	static int green=250, blue=250, red=250, help=0;
		
	public void paintComponent(Graphics g) {
		int size=Blokus.spiel.size;
		int distance=Blokus.spiel.distance;
		
		super.paintComponent(g);
			g.setColor(new Color(red, green, blue));//colors for background
			g.fillRect(0,distance,600,600);//background
			Element e = Blokus.spiel.player.getElement();
			Element o = Blokus.spiel.opponent.getElement();
			g.setColor(new Color(200,50,50));
			for (int i=0; i<e.getHorizontal().length; i++) {//small painting of element
				g.fillRect(e.getHorizontal()[i]*15+15, e.getVertical()[i]*15+5, 15,15);
			}
			g.setColor(new Color(50,50,250));//small painting of element
			for (int i=0; i<o.getHorizontal().length; i++) {
				g.fillRect(o.getHorizontal()[i]*15+150, o.getVertical()[i]*15+5, 15,15);
			}
			g.setColor(new Color(0,0,0));//black point in the small element painting
			g.fillOval(e.getHorizontal()[0]*15+17, e.getVertical()[0]*15+5, 10,10);
			for (int i=0; i<20; i++) {
				g.setColor(new Color(0,0,0));
				g.drawLine(0,i*size+distance,600,i*size+distance);
				g.drawLine(i*size,distance,i*size,600+distance);}
			for (int i=0; i<20; i++) {
				for (int j=0; j<20; j++) {
					g.setColor(new Color(200,50,50));
					if (Blokus.spiel.player.position[i][j].isOccupied()) {
						g.fillRect(i*size, j*size+distance, size,size);
					}
					g.setColor(new Color(50,50,250));
					if (Blokus.spiel.opponent.position[i][j].isOccupied()) {
						g.fillRect(i*size, j*size+distance, size,size);
					}
					if (Blokus.spiel.player.position[i][j].isAccessible()&& help%2==1) {
						g.setColor(new Color(0,0,0));
						g.drawOval(i*size+4, j*size+distance+4, 17,17);
					}
				/*	if (Blokus.spiel.opponent.position[i][j].isAccessible()&& help%2==1) {
						g.setColor(new Color(0,0,0));
						g.fillOval(i*size+7, j*size+distance+7, 11,11);
					}*/
				}				
		}
	}	
}
	