import java.awt.*;

public class Figure {
	/**
	 * This game has to figures: catcher and jumper, both placed on a Wabe
	 */
	private Wabe wabe;
	private Color color;
	private int diameter;
	public Figure(Wabe wabe, Color color, int diameter) {
		this.wabe=wabe;
		this.setColor(color);
		this.diameter=diameter;
	}

	public Wabe getWabe() {return wabe;}
	public void setWabe(Wabe wabe) {this.wabe=wabe;}
	public Color getColor() {return color;}
	public void setColor(Color color) {this.color = color;}
	public int getDiameter(){return diameter;}
}
