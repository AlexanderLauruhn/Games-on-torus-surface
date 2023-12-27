
import javax.swing.*;
import java.awt.*;


import java.awt.event.ActionListener;

/**
 * this class forms the gui with its JFrame and buttons
 */

public class Labyrinth extends JFrame {
	static byte width = 23;// horizontal number of Waben and Buttons
	static byte length = 14;// vertical number of Waben and Buttons
	static JLabel label=new JLabel("0 Points");
	static int points=0;
	static FormButton[][] button = new FormButton[width][length];
	static Wabe[][] waben = new Wabe[width][length];
	static Labyrinth game = new Labyrinth();
	static Gui gui;
	static JLayeredPane layer;
	private Figure catcher;// the figure which moves by keys
	private KeyHandler key = new KeyHandler();
	private ActionListener act = new ActionHandler();
	private int zonkNumber=10;
	private Figure[] zonk = new Figure[zonkNumber];


	public Labyrinth() {
		label.setBounds(40,0,200,30);
		add(label);
		act = new ActionHandler();
		layer = new JLayeredPane();
		layer.setBounds(0, 30, 660, 660);
		addKeyListener(key);
		int[] a = {0, 1, 2};
		for (byte i = 0; i < width; i++) {
			for (byte j = 0; j < length; j++) {
				int[] b = {(i + j) % 2, (i + j + 1) % 2, (i + j) % 2};//to get triangels up and down in change
				button[i][j] = new FormButton(a, b);
				button[i][j].addActionListener(act);
				button[i][j].setBounds(25 * i, 42 * j+30, 50, 42);
				layer.add(button[i][j], JLayeredPane.DEFAULT_LAYER);
				waben[i][j] = new Wabe(i, j);
			}
		}
		for (byte i = 0; i < width; i++) {
			for (byte j = 0; j < length; j++) {
				waben[i][j].setLinks(waben[border((byte) (i - 1), width)][j]);
				waben[i][j].setRechts(waben[border((byte) (i + 1), width)][j]);
				waben[i][j].setObenUnten(waben[i][border((byte) (j + ((i + j) % 2) * 2 - 1), length)]);//up and down in change
			}
		}
		catcher = new Figure(waben[5][5], new Color(250, 0,0),10);
		for (int i = 0; i < zonk.length; i++) {
			zonk[i] = new Figure(waben[(int) (Math.random() * length)][(int) (Math.random() * length)], new Color(0,0,250),14);
		}
		gui = new Gui();
		gui.setOpaque(false);
		gui.setBounds(0, 30, 600, 640);
		layer.add(gui, JLayeredPane.DRAG_LAYER);
		add(layer);
		setSize(615, 666);
		setVisible(true);
	}

	/**
	 * returns a positive value lower than b, used to get the board as a ball or torus surface
	 * @param a
	 * @param b
	 * @return
	 */
	static int border(int a, int b) {
		if (b < 1) {b = 1;}
		while (a < 0) {a += b;}
		while (a >= b) {a -= b;}
		return a;
	}

	public static void main(String[] args) {}

	public Figure getCatcher() {return catcher;}

	public void setCatcher(Figure catcher) {this.catcher = catcher;}

	public int getZonkNumber(){return zonkNumber;}
	public Figure getZonk(int i) {
		try {return zonk[i];}
		catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			return null;
		}

	}
}
