import java.awt.*;

public class Figure {
	/**
	 * This game has to figures: catcher and jumper, both placed on a Wabe
	 */
	private Wabe wabe;
	private Color color;
	public Figure(Wabe wabe, Color color) {
		this.wabe=wabe;
		this.setColor(color);
	}

	public Wabe getWabe() {return wabe;}
	public void setWabe(Wabe wabe) {this.wabe=wabe;}
	public Color getColor() {return color;}
	public void setColor(Color color) {this.color = color;}
}
