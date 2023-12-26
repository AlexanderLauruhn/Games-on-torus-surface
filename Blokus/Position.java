
public class Position {
	private int x, y;
	private boolean occupied=false;// if there is already an Element
	private boolean accessible=false;// if an element can set here
	private boolean sideContact=false;// if a close neighbour is occupied
	private boolean cornerContact=false;// if a corner neighbour is occupied
	
	public Position(int x, int y) {
		this.x=x;
		this.y=y;
	}
	public int getX() {return x;}
	public int getY() {return y;}
	public boolean isOccupied() {return occupied;}
	public void setOccupied(boolean occupied) {this.occupied=occupied;}
	public boolean isAccessible() {return accessible;}
	public void setAccessible(boolean accessible) {this.accessible=accessible;}
	public boolean isSideContact() {return sideContact;}
	public void setSideContact(boolean sideContact){this.sideContact=sideContact;}
	public boolean isCornerContact() {return cornerContact;}
	public void setCornerContact(boolean cornerContact){this.cornerContact=cornerContact;}
}