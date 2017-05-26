package model;

import java.util.ArrayList;
import java.util.List;

public class Replication {

	private double x;
	private double y;
    private List<Coord> coordinates;

	public Replication(int steps) {
		coordinates = new ArrayList<>(steps);
		x = 0;
		y = 0;
	}

	public void walk(int angle) {
	    double angleInRadians = angle * Math.PI / 180.0d; 
	    x += Math.cos(angleInRadians);
	    y += Math.sin(angleInRadians);
	    coordinates.add(new Coord(x, y));
	}

    public List<Coord> getCoordinates() {
		return coordinates;
	}

    public double getDistance() {
        return Math.sqrt(x*x + y*y);
    }
}
