

public class Wabe {
	/**
	 * this class represents an hexagon with its neighbours and its position
	 */
	private int x, y, umgedreht,
	inhalt=(byte)(Math.random() * 6);// this value determines the shape of the quadrangle
	private Wabe links, rechts, obenUnten;
	public Wabe(int x, int y) {
		this.x=x;//x position
		this.y=y;// y position
		umgedreht=((x+y)%2)*42;
	}
		/**
		 * returns the int array used for painting the black area on an hexagon
		 * @return
		 */
	public int[] horizontal() {
		switch (inhalt) {
		case 0:   return new int[]{25+25*x,0+25*x,25+25*x};
		case 3:  return new int[]{25+25*x,50+25*x,25+25*x};
		case 4:  return new int[]{0+25*x,50+25*x,38+25*x};
		case 5:  return new int[]{0+25*x,50+25*x,12+25*x};
		case 2:  return new int[]{12+25*x,50+25*x,25+25*x};
		case 1:  return new int[]{38+25*x,0+25*x,25+25*x};
			default: return new int[]{};
		}
	}
	/**
	 * returns the int array used for painting the black area on an hexagon
	 * @return
	 */
	public int[] vertical() {
		switch (inhalt) {
		case 0,3:   return new int[]{0+42*y,umgedreht+42*y,42+42*y};
		case 4,5:  return new int[]{umgedreht+42*y,umgedreht+42*y,21+42*y};
		case 2,1:  return new int[]{21+42*y,umgedreht+42*y,42-umgedreht+42*y};
		default: return new int[]{};
		}
	}
	public void setLinks(Wabe links) {this.links=links;}
	public Wabe getLinks() {return links;}
	public void setRechts(Wabe rechts) {this.rechts=rechts;}
	public Wabe getRechts() {return rechts;}
	public void setObenUnten(Wabe obenUnten) {this.obenUnten=obenUnten;}
	public Wabe getObenUnten() {return obenUnten;}
	public void setInhalt(int inhalt) {this.inhalt=inhalt;}
	public int getInhalt() {return inhalt;}
	public int getX() {return x;}//no setter, because final
	public int getY() {return y;}
	/**
	 * returns the x position of a figure placed on a triangle
	 * @return
	 */
		
	public int positionX() {
		switch (inhalt) {
			case 0: return 25*x+27;
			case 1: return 25*x+28;
			case 2: return 25*x+13;
			case 3: return 25*x+10;
			default: return 25*x+19;
		}
	}
/**
 * returns the y position of a figure placed on a triangle
 * @return
 */
	
	public int positionY() {
		switch (inhalt) {
			case 0: return 42*y+7+((x+y)%2)*18;
			case 1: return 42*y+5+((x+y)%2)*22;
			case 2: return 42*y+5+((x+y)%2)*22;
			case 3: return 42*y+7+((x+y)%2)*18;
			case 4: return 42*y+21-((x+y)%2)*10;
			default: return 42*y+21-((x+y)%2)*10;
		}
	}

	/**
	 * this method calculates, if the path to another Wabe is blocked by a triangle
	 * @param a
	 * @param b
	 * @return
	 */
	public boolean isAccessible(int a, int b) {//a is the content of the other wabe, b represents the direction
		if(b==0 && (inhalt <2 || (a>1&&a<4) || (inhalt==2 && a==1) || (inhalt==5 && a==4) )) {return false;}//going left
		if(b==1 &&((inhalt >1 && inhalt<4) || a<2 || (inhalt ==4 && a==5) || (inhalt ==1 && a==2))) {return false;}//going right
		if(b==2 &&(inhalt >3 || a>3 ||(inhalt==0 && a==3) || (inhalt==3 && a==0))) {return false;}//going up

		else {return true;}
	}
}
