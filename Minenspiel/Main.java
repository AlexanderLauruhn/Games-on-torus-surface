import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionListener;

public class Main extends JFrame {
	static JButton[][] button;
	static JButton newGame=new JButton("New Game");
	static Main game=new Main();
	ActionListener act = new ActionHandler();
	Board board;
	static int size;
	JRadioButton difficult=new JRadioButton("difficult");/*Abbildung eines einzelnen Baums*/
	JRadioButton medium=new JRadioButton("medium");/*Abbildung mehrerer Bäume*/
	JRadioButton easy=new JRadioButton("easy");/*Abbildung mehrerer Bäume*/
	ButtonGroup setting=new ButtonGroup();
	public Main() {
		size=14;
		button=new JButton[size][size];
		difficult.setBounds(150,0,80,30);
		setting.add(difficult);
		add (difficult);
		medium.setBounds(230,0,80,30);
		setting.add(medium);
		add (medium);
		easy.setBounds(320,0,70,30);
		setting.add(easy);
		add(easy);
		board=new Board();
		act = new ActionHandler();		
		for (byte i=0; i<size; i++) {
			for (byte j=0; j<size; j++) {
				button[i][j]=new JButton();
				button[i][j].setBounds((600/size)*j,(600/size)*i+30,600/size,600/size);
				button[i][j].addActionListener(act);
				Main.button[i][j].setBackground(new Color(255, (int)(Math.random()*50+180), (int)(Math.random()*50+180)));
				add(button[i][j]);
			}
		}
		newGame.setBounds(30,0,100,30);
		newGame.addActionListener(act);
		newGame.setBackground(Color.WHITE);
		newGame.setToolTipText("start a new Game");
		add(newGame);
		add(new JLabel());
	}

		public static void main(String[] args) {
			game.setTitle("Minenspiel");
			game.setSize(603,654);
			game.setVisible(true);
			}}

