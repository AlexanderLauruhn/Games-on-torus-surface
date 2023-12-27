

import javax.swing.*;
import java.awt.*;
public class Gui extends JPanel {

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(65,65,65));
		//draw the dark shapes quadrangle on the hexagons
		for (int i = 0; i< Labyrinth.width; i++) {
			for (int j = 0; j< Labyrinth.length; j++) {
				int[] v = Labyrinth.waben[i][j].vertical();
				int[] h = Labyrinth.waben[i][j].horizontal();
				g.setColor(new Color(65,65,65));
				g.fillPolygon(v,h,4);
				g.setColor(Color.BLACK);//black shape border
				g.drawPolygon(v,h,4);}
		}
		//jumper
		int	x= Labyrinth.game.getJumper().getWabe().positionX();
		int y= Labyrinth.game.getJumper().getWabe().positionY();
		g.setColor(Labyrinth.game.getJumper().getColor());
		g.fillRect(x,y,11,11);
		g.setColor(Color.BLACK);
		g.drawRect(x,y,11,11);
		//catcher
		g.setColor(Labyrinth.game.getCatcher().getColor());
		x= Labyrinth.game.getCatcher().getWabe().positionX();
		y= Labyrinth.game.getCatcher().getWabe().positionY();
		g.fillOval(x,y,15,15);
		g.setColor(Color.BLACK);
		g.drawOval(x,y,15,15);
		}
}