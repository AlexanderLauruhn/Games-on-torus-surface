public class Element {
	//an  Element consists mainly of two int arrays, which are the coordinate values of the shape
	private int[] horizontal;
	private int [] vertical;


	public Element() {
		createShapes((int)(Math.random()*10));
	}

	//based of an random or given number, the int arrays are filled as followed:
	public void createShapes(int number) {
		switch (number) {
		case 0:
			horizontal = new int[] {0,1,2,3};
			vertical = new int[] {0,0,0,0};
			break;
		case 1:
			horizontal = new int[] {0,1,1,2};
			vertical = new int[] {0,0,1,0};
			break;
		case 2:
			horizontal = new int[] {0};
			vertical = new int[] {0};
			break;
		case 3:
			horizontal = new int[] {0,1};
			vertical = new int[] {0,0};
			break;
		case 4:
			horizontal = new int[] {0,0,1,2,2};
			vertical = new int[] {0,1,0,0,1};
			break;
		case 5:
			horizontal = new int[] {0,0,1,2,3};
			vertical = new int[] {0,1,0,0,0};
			break;
		case 6:
			horizontal = new int[] {1,0,1,1,2};
			vertical = new int[] {1,1,0,2,1};
			break;
		case 7:
			horizontal = new int[] {0,0,1};
			vertical = new int[] {0,1,1};
			break;
		case 8:
			horizontal = new int[] {0,0,1,1};
			vertical = new int[] {0,1,1,2};
			break;
		case 9:
			horizontal = new int[] {0,0,0,1,2};
			vertical = new int[] {0,1,2,2,2,};	
			break;
	}
}
	public int[] getHorizontal() {return horizontal;}
	public void setHorizontal(int[] horizontal){this.horizontal=horizontal;}
	public int[] getVertical() {return vertical;}
	public void setVertical(int[] vertical){this.vertical=vertical;}
	
	public void mirrorH() {
		int max=Blokus.max(horizontal);
		for (int i=0; i<horizontal.length; i++) {
			horizontal[i]=max-horizontal[i];
		}
	}
	public void mirrorV() {
		int max=0;
		for (int i:vertical) {
			if (i>max) {max=i;}
		}
		for (int i=0; i<vertical.length; i++) {
			vertical[i]=max-vertical[i];
		}
	}
	public void turnAround() {
		int[] copy=horizontal;
		horizontal=vertical;
		vertical=copy;
		mirrorV();
	}
	
}
