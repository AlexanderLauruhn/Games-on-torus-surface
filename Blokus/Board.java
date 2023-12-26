
public class Board {
	Position[][] position = new Position[20][20];
	Element e=new Element();
	int point=0;
	public Board() {
		for (int i=0; i<20; i++) {
			for (int j=0; j<20; j++) {
				position[i][j]=new Position(i,j);
			}
		}
	}

	public Position getPosition(int x, int y) {return position[x][y];}
	public Element getElement() {return e;}
	public void setElement() {e= new Element();}
	public void increasePoint(int point) {this.point+=point;}
	public int getPoint() {return point;}
}
