package model;

import java.util.ArrayList;
import java.util.List;

public class Replication {

	private List<Coord> coordinates; 

	public Replication(int steps) {
		coordinates = new ArrayList<>(steps);
	}
	
	public void addCoordinate(double x, double y) {
		coordinates.add(new Coord(x,y));		
	}

	public List<Coord> getCoordinates() {
		return coordinates;
	}
}
