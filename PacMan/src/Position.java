
public class Position {
	private int value;
	private boolean visited = false;
	public Position left, right, top, bottom;// the neighbours
	
	public Position() {
		value =(int)(Math.random()*Main.randomMax);// random value determining the border
	}
	
	public int getValue() {return value;}
	public void setValue(int value) {this.value=value;}
	public boolean isVisited() {return visited;}
	public void setVisited(boolean visited) {this.visited=visited;}
	/**
	 * recursive function visiting every accessible position in the labyrinth
	 */
	public void visit () {
		setVisited(true);
		if (moveUp() && !top.isVisited()) {top.visit();}
		if (moveDown() && !bottom.isVisited()) {bottom.visit();}
		if (moveLeft() && !left.isVisited()) {left.visit();}
		if (moveRight() && !right.isVisited()) {right.visit();}	
	}
	public boolean moveUp() {return (value!=1 && top.getValue()!=3);}//true if top is accessible
	public boolean moveDown() {return (value!=3 && bottom.getValue()!=1);}// true if bottom is accessible
	public boolean moveLeft() {return (value!=0 && left.getValue()!=2);} // true if left if accessible
	public boolean moveRight() {return (value!=2 && right.getValue()!=0);}//true if right is accessible;}
}
