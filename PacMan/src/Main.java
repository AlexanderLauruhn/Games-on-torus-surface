import java.awt.Color;
import java.util.ArrayList;

import javax.swing.*;

public class Main extends JFrame {
	KeyHandler key = new KeyHandler();
	static int size = 25;// size of labyrinth
	static int randomMax=2;//2 is difficult, 4 is easy
	Position[][] labyrinth = new Position[size][size];
	static Gui gui;
	static Figure pacman;
	static ArrayList<Figure> diamonds; //the figures, the pacman can catch
	static Main main=new Main();
	
	public Main() {
		pacman=new Figure();
		diamonds = new ArrayList<Figure>();
		for (int i =0; i<8; i++) {diamonds.add(new Figure());}// 8 diamonds to catch
		setPositions();
		addKeyListener(key);
		setFocusable(true);
		gui = new Gui();
		gui.setBounds(0,0,604,604);
		gui.setOpaque(false);
		add(gui);
		add(new JLabel());
	}
	
	static int border (int a, int b) {//return a positive value below b
		while (a<0) {a+=b;}
		while (a>=b) {a-=b;}
		return a;
		}
	 /**
	  * arrange a full labyrinth where every position is accessible
	  */
	public void setLabyrinth() {
		for (int i =0; i<size; i++) {// at first, all positions are not visited
			for (int j =0; j<size; j++) {
				labyrinth[i][j].setVisited(false);
			}
		}
		labyrinth[0][0].visit();//recursive visiting beginning anywhere (at 0,0)
		boolean finish = true;
		for (int i =0; i<size; i++) {
			for (int j =0; j<size; j++) {
				if (!labyrinth[i][j].isVisited()){//change area, if positions was not visited
					labyrinth[i][j].left.setValue((int)(Math.random()*Main.randomMax));
					labyrinth[i][j].right.setValue((int)(Math.random()*Main.randomMax));
					labyrinth[i][j].top.setValue((int)(Math.random()*Main.randomMax));
					labyrinth[i][j].bottom.setValue((int)(Math.random()*Main.randomMax));
					labyrinth[i][j].setValue((int)(Math.random()*Main.randomMax));
					finish=false;
				}
			}
		}
		if (!finish) {setLabyrinth();}//repeat, if not finish
	}
	/**
	 * setup of a game with a new labyrinth
	 */
	public void setPositions() {
		for (int i =0; i<size; i++) {
			for (int j =0; j<size; j++) {
				labyrinth[i][j]=new Position();
			}
		}
		for (int i =0; i<size; i++) {
			for (int j =0; j<size; j++) {
				labyrinth[i][j].left=labyrinth[border(i-1,size)][j];
				labyrinth[i][j].right=labyrinth[border(i+1,size)][j];
				labyrinth[i][j].bottom=labyrinth[i][border(j+1,size)];
				labyrinth[i][j].top=labyrinth[i][border(j-1,size)];
			}
		}
			setLabyrinth();	
	}
	/**
	 * setup of a game with everything (labyrinth, pacman, diamonds) changing
	 */
	public static void createNewLabyrinth() {
		main.setPositions();
		pacman = new Figure();
		diamonds.clear();
		for (int i =0; i<8; i++) {diamonds.add(new Figure());}
		gui.color=new Color((int)(Math.random()*200)+50,(int)(Math.random()*200)+50,(int)(Math.random()*200)+50);
	}
	
	public static void main(String[] args) {
		main.setSize(617,641);
		main.setVisible(true);
	}
}

