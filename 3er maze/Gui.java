
import javax.swing.*;
import java.awt.*;
public class Gui extends JPanel {
	int x,y,z;
		
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(65,65,65));
		//draw the dark shapes quadrangle on the hexagons
		for (int i = 0; i< Labyrinth.width; i++) {
			for (int j = 0; j< Labyrinth.length; j++) {
				int[] v = Labyrinth.waben[i][j].horizontal();
				int[] h = Labyrinth.waben[i][j].vertical();
				g.setColor(new Color(115,65,65));
				g.fillPolygon(v,h,3);
				g.setColor(Color.BLACK);//black shape border
				g.drawPolygon(v,h,3);}
		}

		z=Labyrinth.game.getZonk(0).getDiameter();
		for(int i=0; i<Labyrinth.game.getZonkNumber(); i++){
			x=Labyrinth.game.getZonk(i).getWabe().positionX();
			y=Labyrinth.game.getZonk(i).getWabe().positionY();
			g.setColor(Labyrinth.game.getZonk(i).getColor());
			g.fillOval(x,y,z,z);
			g.setColor(Color.BLACK);
			g.drawOval(x,y,z,z);
		}
		//catcher
		g.setColor(Labyrinth.game.getCatcher().getColor());
		x= Labyrinth.game.getCatcher().getWabe().positionX();
		y= Labyrinth.game.getCatcher().getWabe().positionY();
		z=Labyrinth.game.getCatcher().getDiameter();
		g.fillOval(x+2,y+2,z,z);
		g.setColor(Color.BLACK);
		g.drawOval(x+2,y+2,z,z);
	}
}