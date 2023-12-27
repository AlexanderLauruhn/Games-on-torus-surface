import java.awt.*;
import javax.swing.*;

public class FormButton extends JButton {
	private int[] a;//the x-coordinates
	private int[] b;// the y-coordinates
	private Color color = new Color(150,200,200);
	private Color borderColor = new Color(0,0,0);
	private boolean border=true;
	private Shape shape;

	public FormButton( int[] a, int[] b) {
	    this.a=a;
	    this.b=b;
    setFocusable(false);
    setContentAreaFilled(false);
    setBorderPainted(false);
  }

	/**
	 * paints and draws the button
	 * @param g
	 */
  protected void paintComponent(Graphics g) {
	  int maxA=1,maxB=1;
	  for (int i=0; i<a.length; i++) {//ensure that the coordinates are not negative
		  if (a[i]<0) {a[i]=0;}
		  if (b[i]<0) {b[i]=0;}
		  if (a[i]>maxA) {maxA=a[i];}//determine the maximum to scale the button into size
		  if (b[i]>maxB) {maxB=b[i];}
	  }
	  for (int i=0; i<a.length; i++) {
		  a[i]=a[i]*getSize().width/maxA;
		  b[i]=b[i]*getSize().height/maxB;
	  }
      g.setColor(color);
      g.fillPolygon(a,b,a.length);
	  if (border){
		  g.setColor(Color.BLACK);//black border
		  g.drawPolygon(a,b,a.length);}
  }

	/**
	 * determine if the shape is clicked
	 * @param x
	 * @param y
	 * @return
	 */
  public boolean contains(int x, int y) {
      shape = new Polygon(a,b,a.length);
      return shape.contains(x, y);
  }

	/**
	 * change color of an button
	 * @param a
	 * @param b
	 * @param c
	 */
	public void setColor(int a, int b, int c) {
	if(a<0)a*=-1;
	if(b<0)b*=-1;
	if(c<0)c*=-1;
	while(a>255) {a-=255;}
	while(b>255) {b-=255;}
	while(c>255) {c-=255;}
	color = new Color(a,b,c);
}

	/**
	 * change border color of an button
	 * @param a
	 * @param b
	 * @param c
	 */
	public void setBorderColor(int a, int b, int c) {
		if(a<0)a*=-1;
		if(b<0)b*=-1;
		if(c<0)c*=-1;
		while(a>255) {a-=255;}
		while(b>255) {b-=255;}
		while(c>255) {c-=255;}
		borderColor = new Color(a,b,c);
	}
	public void setBorder(boolean border){this.border=border;}
}