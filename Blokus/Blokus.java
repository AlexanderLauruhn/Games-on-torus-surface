import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class Blokus extends JFrame {
	static Gui gui;
	static JButton[][] button=new JButton[20][20];
	static JButton newGame =new JButton("New Game");
	static JButton giveUp =new JButton("Give Up");
	static JLayeredPane layer; 
	static Blokus spiel=new Blokus();
	JLabel label = new JLabel("Player: 0  Opponent: 0");
	KeyHandler key = new KeyHandler();
	static ActionListener act = new ActionHandler();
	Board player=new Board();
	Board opponent = new Board();
	int size=29;
	int distance=(30-size)*20+50;
	int number=20;
	static boolean givenUp=false;

	public Blokus() {
		act = new ActionHandler();
		layer = new JLayeredPane();
		layer.setBounds(0,30,number*size,number*size);
		gui = new Gui();
		gui.setBounds(0,0,number*size,number*size+distance);
		gui.setOpaque(false);
		layer.add(gui,JLayeredPane.DRAG_LAYER);
		addKeyListener(key);
		setFocusable(true);
		newGame.setBounds(430,20,98,25);//Button for new game
		Blokus.newGame.setVisible(false);
		newGame.addActionListener(act);
		giveUp.setBounds(430,20,98,25);//Button to give up
		giveUp.addActionListener(act);
		add(newGame);
		add(giveUp);
		for (byte i=0; i<number; i++) {
			for (byte j=0; j<number; j++) {
				button[i][j]=new JButton();
				button[i][j].setBounds(size*j,size*i+distance,size,size);
				button[i][j].addActionListener(act);
				layer.add(button[i][j],JLayeredPane.DEFAULT_LAYER);
			}
		}
		player.position[10][10].setOccupied(true);//fix even position
		opponent.position[(int)(Math.random()*9)*2+1][(int)(Math.random()*9)*2+1].setOccupied(true);//only odd position
		label.setBounds(270,20,200,30);
		add(label);
		add(layer);
		setTitle("Blokus");
		setSize(number*size+15,number*size+distance+35);
}

		public static void main(String[] args) {
			spiel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			spiel.setVisible(true);
			setupAccessiblePosition(spiel.player);
		}
		
		public static void setupAccessiblePosition(Board board) {
			searchSidePositions(board);
			searchCornerPosition (board);
			searchAccessiblePosition(board);
		}
		static public void searchSidePositions(Board board) {
			for (int i=0; i<20; i++) {
				for (int j=0; j<20; j++) {
					board.position[i][j].setAccessible(false);
					board.position[i][j].setSideContact(false);
					board.position[i][j].setCornerContact(false);
					if(board.position[border(i+1,20)][border(j,20)].isOccupied()||
							board.position[border(i-1,20)][border(j,20)].isOccupied()||
							board.position[border(i,20)][border(j+1,20)].isOccupied()||
							board.position[border(i,20)][border(j-1,20)].isOccupied()) {
						board.position[i][j].setSideContact(true);
					}
				}
			}
		}
		public static void searchCornerPosition(Board board) {
			for (int i=0; i<20; i++) {
				for (int j=0; j<20; j++) {
					if(!board.position[i][j].isSideContact() &&
						!board.position[i][j].isOccupied()&&(
						board.position[border(i+1,20)][border(j+1,20)].isOccupied()||
						board.position[border(i-1,20)][border(j+1,20)].isOccupied()||
						board.position[border(i+1,20)][border(j-1,20)].isOccupied()||
						board.position[border(i-1,20)][border(j-1,20)].isOccupied())) {
						board.position[i][j].setCornerContact(true);
					}
				}
			}
		}
		public static void searchAccessiblePosition(Board board) {
			Element e= board.getElement();
			for (int i=0; i<20; i++) {
				for (int j=0; j<20; j++) {
					boolean contactToCorner=false;
					boolean contactToSide=false;
					boolean unavailable=false;
					for (int g=0; g<e.getHorizontal().length; g++) {
						int h=e.getHorizontal()[g];
						int v=e.getVertical()[g];
						if(board.position[border(i+h,20)][border(j+v,20)].isCornerContact()) {contactToCorner=true;}
						if(board.position[border(i+h,20)][border(j+v,20)].isSideContact()) {contactToSide=true;}
						if(spiel.player.position[border(i+h,20)][border(j+v,20)].isOccupied()) {unavailable=true;}
						if(spiel.opponent.position[border(i+h,20)][border(j+v,20)].isOccupied()) {unavailable=true;}
					}
					if(contactToCorner && !contactToSide && !unavailable) {
						board.position[border(i+e.getHorizontal()[0],spiel.number)][border(j+e.getVertical()[0], spiel.number)].setAccessible(true);
						}
					
				}
			}
		}		
		static int border (int a, int b) {//gibt a zur�ck mit a<b und a>-1, damit alles �ber den Rand gehen kann
			while (b<1) {b+=a;};
			while (a<0) {a+=b;}
			while (a>=b) {a-=b;}
			return a;
			}
		static int max(int[] a) {
			int max=0;
			for (int value: a) {if(value>max) max=value;	}
			return max;
		}
		static void newGame() {
			givenUp=false;
			spiel.opponent.increasePoint(-spiel.opponent.getPoint());
			spiel.player.increasePoint(-spiel.player.getPoint());
			spiel.label.setText("Player: 0    Opponent: 0");
			for (int i=0; i<spiel.number; i++) {
				for (int j=0; j<spiel.number; j++) {
					spiel.opponent.getPosition(i,j).setOccupied(false);
					spiel.player.getPosition(i,j).setOccupied(false);
				}
			}
			spiel.player.position[10][10].setOccupied(true);//fix even position
			spiel.opponent.position[(int)(Math.random()*9)*2+1][(int)(Math.random()*9)*2+1].setOccupied(true);
			Gui.green=255; Gui.blue=255; Gui.red=255;
			spiel.player.setElement();
			spiel.opponent.setElement();
			setupAccessiblePosition(spiel.opponent);
	    	setupAccessiblePosition(spiel.player);
			gui.repaint();
		}
}

