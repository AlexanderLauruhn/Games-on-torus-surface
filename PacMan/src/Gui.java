import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class Gui extends JPanel {
	int d = 2;// distance to frame
	Color color = Color.GREEN;
	
	public void paintComponent(Graphics g) {
		int size=600/Main.size;
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(5));
		if (Main.size>40) {g2.setStroke(new BasicStroke(1));}//line width depending on game size
		else if (Main.size>35) {g2.setStroke(new BasicStroke(2));}
		else if (Main.size>25) {g2.setStroke(new BasicStroke(3));}
		else if (Main.size>20) {g2.setStroke(new BasicStroke(4));}
			g.setColor(Color.BLACK);
			g.fillRect(0,0,size*Main.size+4,size*Main.size+4);//black background
			g.setColor(color);// color of lines in labyrinth
			for (int i =0; i<Main.size; i++) {
				for (int j =0; j<Main.size; j++) {
					Position p= Main.main.labyrinth[i][j];	//draw labyrinth
					if (p.getValue()==0) {g.drawLine(i*size+2,j*size+2,i*size+2,(j+1)*size+2);}//closed on left side
					if (p.getValue()==1) {g.drawLine(i*size+2,j*size+2,(i+1)*size+2,j*size+2);}//closed on top 
					if (p.getValue()==2) {g.drawLine((i+1)*size+2,j*size+2,(i+1)*size+2,(j+1)*size+2);}//closed on the right side
					if (p.getValue()==3) {g.drawLine(i*size+2,(j+1)*size+2,(i+1)*size+2,(j+1)*size+2);} //closed on the bottom side
				}
			}
			for (Figure f: Main.diamonds) {// draw blue diamonds
				g.setColor(Color.BLUE);
				g.fillRect(f.getX()*size+size/3,f.getY()*size+size/3,size/3,size/3);
			}
			g.setColor(Color.YELLOW);//draw pacman itself 
			g.fillOval(Main.pacman.getX()*size+size/4,Main.pacman.getY()*size+size/4,3*size/5,3*size/5);
			//g.fillArc(Main.pacman.getX()*30+5,Main.pacman.getY()*30+5,20,20,30,300);
	}
}
		



		
	

