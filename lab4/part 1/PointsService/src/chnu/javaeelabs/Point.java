package chnu.javaeelabs;

public class Point {
	String name;
	int coord_x;
	int coord_y;
	int coord_z;
	int m;
	
	public Point() {	
	}

	public Point(String name, int coord_x, int coord_y, int coord_z,int m) {
		super();
		this.name = name;
		this.coord_x = coord_x;
		this.coord_y = coord_y;
		this.coord_z = coord_z;
		this.m = m;
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCoord_x() {
		return coord_x;
	}

	public void setCoord_x(int coord_x) {
		this.coord_x = coord_x;
	}

	public int getCoord_y() {
		return coord_y;
	}

	public void setCoord_y(int coord_y) {
		this.coord_y = coord_y;
	}

	public int getCoord_z() {
		return coord_z;
	}

	public void setCoord_z(int coord_z) {
		this.coord_z = coord_z;
	}

	@Override
	public String toString() {
		return "Point [name=" + name + ", coord_x=" + coord_x + ", coord_y=" + coord_y + ", coord_z=" + coord_z + ", m="
				+ m + "]";
	}
		
}
