import java.awt.Color;

/**
 * boarad has the arrays of fields, in which mines are, and the numbers of neighbours mines
 * @author Alex
 *
 */
public class Board {
	private boolean[][] mine=new boolean[Main.size][Main.size];// set the mines in the game
	private int [][] safety=new int[Main.size][Main.size];//safety value indicates the number of mines in neighbour fields
	private boolean[][] shown= new boolean[Main.size][Main.size];// if position is already shown (color,safety, mine)
	static float ratio=0.2f; //ratio of mines compared to normal fields
	
	public Board() {
		
		 for (int i=0; i<Main.size; i++) {
			 for (int j=0; j<Main.size; j++) {
				 if (Math.random()<ratio) {mine[i][j]=true;}//20 % mines
			 }
		 }
		 for (int i=0; i<Main.size; i++) {
			 for (int j=0; j<Main.size; j++) {
				 if (mine[i][border(j+1,Main.size)]==true) {safety[i][j]++;}
				 if (mine[i][border(j-1,Main.size)]==true) {safety[i][j]++;}
				 if (mine[border(i+1,Main.size)][j]==true) {safety[i][j]++;}
				 if (mine[border(i-1,Main.size)][j]==true) {safety[i][j]++;}
				 if (mine[border(i+1,Main.size)][border(j+1,Main.size)]==true) {safety[i][j]++;}
				 if (mine[border(i+1,Main.size)][border(j-1,Main.size)]==true) {safety[i][j]++;}
				 if (mine[border(i-1,Main.size)][border(j+1,Main.size)]==true) {safety[i][j]++;}
				 if (mine[border(i-1,Main.size)][border(j-1,Main.size)]==true) {safety[i][j]++;}
				 
			 }
		 }
	 }
	/**
	 * returns a positive value below b; used to play over the border of the board
	 */
		public static int border (int a, int b) {//gibt a zurück mit a<b und a>-1, damit alles über den Rand gehen kann
			while (b<1) {b+=a;};
			while (a<0) {a+=b;}
			while (a>=b) {a-=b;}
			return a;
		}
		public void won () {
			boolean won= true;
			for (int i=0; i<Main.size; i++) {
				 for (int j=0; j<Main.size; j++) {
					 if (!shown[i][j] && !mine[i][j]) {won = false;}
				 }
			}
			if (won) {
				for (int i=0; i<Main.size; i++) {
					 for (int j=0; j<Main.size; j++) {
						 if (mine[i][j]) {
						 Main.button[i][j].setBackground(new Color(200,200,200));
						 Main.button[i][j].setText("X");
						 }
					 }
				}
			}
		}
		public int getSafety(int i, int j) {return safety[i][j];}
		public boolean getMine(int i, int j) {return mine[i][j];}
		public boolean getShown(int i, int j) {return shown[i][j];}
		public void setShown (boolean a, int i, int j) {shown[i][j]=a;}
		
	
}
