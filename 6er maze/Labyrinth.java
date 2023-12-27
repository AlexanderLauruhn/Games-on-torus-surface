import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


/**
 * this class forms the gui with its JFrame and buttons
 */

public class Labyrinth extends JFrame {
	static byte width=23;// horizontal number of Waben and Buttons
	static byte length=20;// vertical number of Waben and Buttons
	static FormButton[][] button = new FormButton[width][length];
	static Wabe[][] waben=new Wabe[width][length];//one wabe for each hexagon button
	static Labyrinth game=new Labyrinth();
	static Gui gui;
	static JLayeredPane layer;// to set the gui over the button
	private Figure catcher;// the figure which moves by keys
	private Figure jumper;// figure moved by algorithm
	private KeyHandler key = new KeyHandler();
	private ActionListener act = new ActionHandler();


	public Labyrinth() {
		act = new ActionHandler();
		int[] a= {2,2,1,0,0,1};// both a and b used to make hexagon buttons
		int[] b= {3,1,0,1,3,4};
		int shift=0;// used to set the buttons of odd lines to the fitting position
		layer = new JLayeredPane();
		layer.setBounds(0,30,670,670);
		addKeyListener(key);
		for (byte i=0; i<width; i++) {
			for (byte j=0; j<length; j++) {
				if(j%2==1) {shift=25;}
				button[i][j]=new FormButton(a,b);
				button[i][j].addActionListener(act);
				button[i][j].setBounds(50*i+shift+5,31*j,50,42);
				layer.add(button[i][j],JLayeredPane.DEFAULT_LAYER);
				waben[i][j] = new Wabe(i,j);
				shift=0;}
			}
		setCatcher(new Figure(waben[length/4][width/4], Color.GREEN));
		setJumper(new Figure(waben[length/4*3][width/4*3], Color.BLUE));
		for (byte i=0; i<width; i++) {
			for (byte j=0; j<length; j++) {
				waben[i][j].setLeft(waben[border((byte)(i-1),width)][j]);
				waben[i][j].setRight(waben[border((byte)(i+1),width)][j]);
				if (j%2==0) {
					waben[i][j].setTopLeft(waben[border(i-1,width)][border(j-1,length)]);
					waben[i][j].setTopRight(waben[i][border(j-1,length)]);
					waben[i][j].setBottomLeft(waben[border(i-1,width)][border(j+1,length)]);
					waben[i][j].setBottomRight(waben[i][border(j+1,length)]);
				}
				if (j%2==1) {
					waben[i][j].setTopRight(waben[border(i+1,width)][border((byte)(j-1),length)]);
					waben[i][j].setTopLeft(waben[i][border(j-1,length)]);
					waben[i][j].setBottomRight(waben[border(i+1,width)][border(j+1,length)]);
					waben[i][j].setBottomLeft(waben[i][border(j+1,length)]);
				}
			}
		}
		gui = new Gui();
		gui.setOpaque(false);
		gui.setBounds(0,0,1200,660);
		layer.add(gui,JLayeredPane.DRAG_LAYER);
		add(layer);
		setSize(1215,676);
		setVisible(true);
		setSafetyValues();
		}

	/**
	 * returns a positive value lower than b; used to move above like on a torus
	 * @param a
	 * @param b
	 * @return
	 */
	static int border(int a, int b) {
		if (b<1) {b=1;};
		while (a<0) {a+=b;}
		while (a>=b) {a-=b;}
		return a;
		}

	public static void main(String[] args) {}

	public Figure getCatcher() {
		return catcher;
	}

	public void setCatcher(Figure catcher) {
		this.catcher = catcher;
	}

	public Figure getJumper() {
		return jumper;
	}

	public void setJumper(Figure jumper) {
		this.jumper = jumper;
	}

	/**
	 * set the color of a button according to the safety score of the corresponding wabe
	 */
	public void setColors(){
		for (byte i=0; i<width; i++) {
			for (byte j=0; j<length; j++) {
				if (KeyHandler.color % 2 == 0) {
					if (waben[i][j].getSafety() == 1) {button[i][j].setColor(255, 0, 0);}
					else if (waben[i][j].getSafety() == 2) {button[i][j].setColor(255, 150, 0);}
					else if (waben[i][j].getSafety() == 3) {button[i][j].setColor(255, 255, 0);}
					else if (waben[i][j].getSafety() == 4) {button[i][j].setColor(150, 255, 0);}
					else if (waben[i][j].getSafety() == 5) {button[i][j].setColor(0, 255, 0);}
					else if (waben[i][j].getSafety() == 6) {button[i][j].setColor(0, 255, 150);}
					else if (waben[i][j].getSafety() == 7) {button[i][j].setColor(0, 150, 255);}
					else if (waben[i][j].getSafety() == 8) {button[i][j].setColor(0, 0, 255);}
					else if (waben[i][j].getSafety() == 9) {button[i][j].setColor(150, 0, 255);}
					else if (waben[i][j].getSafety() == 10) {button[i][j].setColor(250, 0, 150);}
					else {button[i][j].setColor(255, 255, 255);}
				}
				else{button[i][j].setColor(255,(int)(Math.random()*100+50),(int)(Math.random()*100+50));}
			}
		}
	}

	/**
	 * set the safety score accoring to the position of the catcher and the arrangement of waben
	 */
	public void setSafetyValues(){
		for (byte i=0; i<width; i++) {
			for (byte j=0; j<length; j++) {
				waben[i][j].setSafety(0);
			}
		}
		catcher.getWabe().setSafety(1);
		setValues(1);
		boolean finish=false;
		int a=1;
		while(!finish) {
			finish=true;
			for (byte i = 0; i < width; i++) {
				for (byte j = 0; j < length; j++) {
					if (waben[i][j].getSafety() == 0) {finish = false;}
				}
			}
			increaseSafety(a);
			a++;
			setValues(a);
		}
		setColors();
	}

	/**
	 * find all waben, which are connected with each other, these waben get the same safety value
	 * @param b
	 */
	public void setValues(int b){
		boolean finish=false;
		while (!finish){
			finish=true;
			for (byte i=0; i<width; i++) {
				for (byte j=0; j<length; j++) {
			 	Wabe a = waben[i][j];
				if (a.getSafety()==b && a.getLeft().getSafety()==0  &&  a.isAccessible(a.getLeft(),0)){
					a.getLeft().setSafety(b);
					finish=false;}
				if (a.getSafety()==b && a.getTopLeft().getSafety()==0 && a.isAccessible(a.getTopLeft(),1)){
					a.getTopLeft().setSafety(b);
					finish=false;}
				if (a.getSafety()==b  && a.getTopRight().getSafety()==0 && a.isAccessible(a.getTopRight(),2)){
					a.getTopRight().setSafety(b);
					finish=false;}
				if (a.getSafety()==b && a.getRight().getSafety()==0 && a.isAccessible(a.getRight(),3)){
					a.getRight().setSafety(b);
					finish=false;}
				if (a.getSafety()==b && a.getBottomRight().getSafety()==0 &&a.isAccessible(a.getBottomRight(),4)) {
					a.getBottomRight().setSafety(b);
					finish=false;}
				if (a.getSafety() == b && a.getBottomLeft().getSafety()==0 &&a.isAccessible(a.getBottomLeft(), 5)) {
					a.getBottomLeft().setSafety(b);
					finish=false;}
				}
			}
		}
	}

	/**
	 * used to increase the safety value if you need to turn a button to get there
	 * @param b
	 */
	public void increaseSafety(int b){
		for (byte i=0; i<width; i++) {
			for (byte j = 0; j < length; j++) {
				Wabe a = waben[i][j];
				if (a.getSafety() == 0 && (a.getLeft().getSafety() == b ||
						a.getTopLeft().getSafety() == b ||
						a.getTopRight().getSafety() == b ||
						a.getRight().getSafety() == b ||
						a.getBottomRight().getSafety() == b ||
						a.getBottomLeft().getSafety() == b)) {
					a.setSafety(b + 1);
				}
			}
		}
	}

}




