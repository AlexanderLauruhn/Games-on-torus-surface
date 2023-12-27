


public class Wabe {
	/**
	 * this class represents an hexagon with it�s neighbours and it�s position
	 */
	private int x, y, safety;
	int content=(byte)(Math.random() * 6);// this value determines the shape of the quadrangle
	private Wabe topLeft, topRight, left, right, bottomLeft,bottomRight;
	public Wabe(int x, int y) {
		this.x=x;//x position
		this.y=y;// y position

	}
		/**
		 * returns the int array used for painting the black area on an hexagon
		 * @return
		 */
	public int[] vertical() {
		int v=y%2*25;
		switch (content) {
			case 0: return new int[]{30+v+50*x,5+v+50*x,5+v+50*x,30+v+50*x};
			case 1:  return new int[]{55+v+50*x,30+v+50*x,5+v+50*x,5+v+50*x};
			case 2:  return new int[]{55+v+50*x,55+v+50*x,30+v+50*x,5+v+50*x};
			case 3:  return new int[]{30+v+50*x,55+v+50*x,55+v+50*x,30+v+50*x};
			case 4:  return new int[]{5+v+50*x,30+v+50*x,55+v+50*x,55+v+50*x};
			default:  return new int[]{5+v+50*x,5+v+50*x,30+v+50*x,55+v+50*x};
		}
	}
	/**
	 * returns the int array used for painting the black area on an hexagon
	 * @return
	 */
	public int[] horizontal() {
		switch (content) {
			case 0: return new int[]{0+31*y,11+31*y,31+31*y,42+31*y};
			case 1:  return new int[]{11+31*y,0+31*y,11+31*y,31+31*y};
			case 2:  return new int[]{31+31*y,11+31*y,0+31*y,11+31*y};
			case 3:  return new int[]{42+31*y,31+31*y,11+31*y,0+31*y};
			case 4:  return new int[]{31+31*y,42+31*y,31+31*y,11+31*y};
			default:  return new int[]{11+31*y,31+31*y,42+31*y,31+31*y};
		}
	}

	public void setContent(int content) {this.content=content;}
	public int getContent() {return content;}

	/**
	 * set any Figure to the correct horizontal position inside a Wabe according to content value
	 * @return
	 */
	public int positionX() {
		int shift=0;
		if (content==0) {shift=26;}
		if (content==1) {shift=21;}
		if (content==2) {shift=13;}
		if (content==3) {shift=7;}
		if (content==4) {shift=12;}
		if (content==5) {shift=20;}
		return 50*x+shift+y%2*25+5;
		
	}
/**
 * set any Figure to the correct horizontal position inside a Wabe according to content value
 * @return
 */
	
	public int positionY() {
		int shift=0;
		if (content==0) {shift=15;}
		if (content==1) {shift=21;}
		if (content==2) {shift=20;}
		if (content==3) {shift=11;}
		if (content==4) {shift=7;}
		if (content==5) {shift=3;}
		return 31*y+shift;
	}

	/**
	 * this method calculates, if the path to another Wabe is blocked by a quadrangle
	 * @param wabe
	 * @param b
	 * @return
	 */
	public boolean isAccessible(Wabe wabe, int b) {//a ist der inhalt der anderen Wabe/ b stellt die Richtung dar
		int a = wabe.getContent();
		if(b==0 &&(content <2 ||content ==5 || (a>1&&a<5))) {return false;}
		else if(b==1 &&(content <3|| a>2)) {return false;}
		else if(b==2 &&((content <4 && content >0) || (a==0 || a>3))) {return false;}
		else if(b==3 &&((content >1 && content <5 ) || (a<2 || a==5))) {return false;}
		else if(b==4 &&(content >2 || a<3)) {return false;}
		else if(b==5 &&(content ==0 || content>3 ||(a>0 &&a<4))) {return false;}
		else {return true;}
	}


	public Wabe getTopLeft() {return topLeft;}
	public void setTopLeft(Wabe topLeft) {this.topLeft = topLeft;}
	public Wabe getTopRight() {return topRight;}
	public void setTopRight(Wabe topRight) {this.topRight = topRight;}
	public Wabe getLeft() {return left;}
	public void setLeft(Wabe left) {this.left = left;}
	public Wabe getRight() {return right;}
	public void setRight(Wabe right) {this.right = right;}
	public Wabe getBottomLeft() {return bottomLeft;}
	public void setBottomLeft(Wabe bottomLeft) {this.bottomLeft = bottomLeft;}
	public Wabe getBottomRight() {return bottomRight;}
	public void setBottomRight(Wabe bottomRight) {this.bottomRight = bottomRight;}
	public int getSafety(){return safety;}
	public void setSafety(int safety){this.safety=safety;}
}
