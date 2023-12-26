import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;


public class ActionHandler implements ActionListener{
	byte tryTurn=0;// this value is used, if the opponent has to rotate the element to find an accessible position

	/**
	 * if button with accessible position pressed, the element is drawn to the board and gui
	 */
	public void actionPerformed (ActionEvent e) {
		JButton src = (JButton) e.getSource();
		Element element = Blokus.spiel.player.getElement();
		int shiftH=element.getHorizontal()[0];//if the first value in the array is not 0, the shape has to shift to the correct position
		int shiftV=element.getVertical()[0];
		for (byte i = 0; i < 20; i++) {
			for (byte j = 0; j < 20; j++) {
				if (src==Blokus.button[i][j] && Blokus.spiel.player.getPosition(j,i).isAccessible() &&!Blokus.givenUp) {
					for(int g=0; g<element.getHorizontal().length; g++) {
		    		  int k=element.getHorizontal()[g];
		    		  int l=element.getVertical()[g];
		    		  Blokus.spiel.player.getPosition(Blokus.border(j+k-shiftH,20), Blokus.border(i+l-shiftV,20)).setOccupied(true);
		    	  }
				  Blokus.spiel.player.increasePoint(element.getHorizontal().length); 
		    	  Blokus.spiel.player.setElement();
		    	  Blokus.setupAccessiblePosition(Blokus.spiel.opponent);
		    	  Blokus.setupAccessiblePosition(Blokus.spiel.player);
		    	  
		    	  opponentsTurn();
				}  
			}  
			if (e.getSource() ==Blokus.newGame) {
				Blokus.newGame.setVisible(false);
				Blokus.giveUp.setVisible(true);
				tryTurn=0;
				Blokus.newGame();}
			if (e.getSource() ==Blokus.giveUp) {
				giveUp();}
			while (Blokus.givenUp && tryTurn<8) {
				opponentsTurn();
			}
		}
	
		Blokus.gui.repaint();
		Blokus.spiel.requestFocus();
	}
	/**
	 * when the play had its turn, the opponent puts an element to the board
	 */
	public void opponentsTurn() {
		Element e=Blokus.spiel.opponent.getElement();
		if (tryTurn==0) {
			int random=(int)(Math.random()*4);
			for (int i=0; i<random; i++) {e.turnAround();}
		}
		if(tryTurn==0 && Math.random()<0.5) {e.turnAround();}
		Blokus.setupAccessiblePosition(Blokus.spiel.opponent);
		ArrayList<Position> positions = new ArrayList<Position>();
		for (byte i = 0; i < 20; i++) {
			for (byte j = 0; j < 20; j++) {
				if (Blokus.spiel.opponent.getPosition(j,i).isAccessible()) {
					positions.add(Blokus.spiel.opponent.getPosition(j,i));
				}
			}
		}
		
		if (positions.size()!=0) {
			Blokus.spiel.opponent.increasePoint(e.getHorizontal().length);
			tryTurn=0;
			Position p=positions.get((int)(Math.random()*(positions.size()-1)));
			int shiftH=e.getHorizontal()[0];//if the first value in the array is not 0, the shape has to shift to the correct position
			int shiftV=e.getVertical()[0];
			for(int g=0; g<e.getHorizontal().length; g++) {
				int k=e.getHorizontal()[g];
				int l=e.getVertical()[g];
				Blokus.spiel.opponent.getPosition(Blokus.border(p.getX()+k-shiftH,20), Blokus.border(p.getY()+l-shiftV,20)).setOccupied(true);
		 		}
			Blokus.spiel.opponent.setElement();
			Blokus.setupAccessiblePosition(Blokus.spiel.opponent);
			Blokus.setupAccessiblePosition(Blokus.spiel.player);
			
		}
		
		else if (tryTurn<8){tryToWin();}
		else {Gui.blue=0;Gui.red=0;}
		Blokus.spiel.label.setText("Player "+Blokus.spiel.player.getPoint()+ "   Opponent: "+Blokus.spiel.opponent.getPoint());
	}
	/**
	 * when the opponent cannot find an accessible position, it can rotate the element
	 */
	public void tryToWin() {
		switch (tryTurn){
		case 0,1,2,3,5,6,7: Blokus.spiel.opponent.getElement().turnAround();
		case 4: Blokus.spiel.opponent.getElement().mirrorH();
		}
		tryTurn++;
		opponentsTurn();
	}
	
	public void giveUp() {
		Gui.green=100;
		Gui.blue=100;
		Gui.red=100;
		Blokus.newGame.setVisible(true);
		Blokus.giveUp.setVisible(false);
		Blokus.givenUp=true;
	}
}