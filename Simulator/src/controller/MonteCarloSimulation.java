package controller;

import java.util.ArrayList;
import java.util.List;

import model.Coord;
import model.RandomAngleGenerator;
import model.Replication;

public class MonteCarloSimulation {

	private int steps;

	private int currentStep;
	private int currentReplication;
	private RandomAngleGenerator angleGenerator;
	private List<Replication> replications;
	private List<Integer> angles;
	
	public MonteCarloSimulation(int steps) {
		this.steps = steps;
		init();
	}

	private void init() {
		angleGenerator = new RandomAngleGenerator();
		replications = new ArrayList<>();
		angles = new ArrayList<>();
	}

	public void run(int numberOfReplications) {
		currentReplication = 1;

		while(currentReplication <= numberOfReplications) {
			Replication replication = new Replication(steps);
			currentStep = 1;
			while(currentStep <= steps) {
				int angle = angleGenerator.generateAngle();
				replication.walk(angle);
				angles.add(new Integer(angle));
				currentStep++;
			}
			replications.add(replication);
			currentReplication++;
		}
	}

	public void printReplications() {
	    int rep = 1;
	    double estimated = getEstimatedDistance();
	    double distance = 0.0d;
	    double x = 0.0d;
	    double y = 0.0d;
	    int step = 0;

	    for (Replication replication : replications) {
	        System.out.println();
	        System.out.println("-----------------------------------------");
	        System.out.println("Replication: " + rep);
	        System.out.println("-----------------------------------------");
	        System.out.println("  Step  Angle    x        y     sqrt(n)     Dn");
	        step = 0;
	        for (Coord coord : replication.getCoordinates()) {
	            x = coord.getX();
	            y = coord.getY();
	            distance = Math.sqrt(x*x + y*y);
	            System.out.format("%4d    %3d  %7.3f  %7.3f  %7.3f  %7.3f%n", step+1, angles.get(step), x, y, Math.sqrt(step+1), distance);
	            step++;
	        }
	        System.out.println();
	        System.out.format("Estimated distance: %6.3f%n", estimated);
	        System.out.format("Computed distance.: %6.3f%n", distance);
	        System.out.format("Difference........: %6.3f%n", distance - estimated);
	        rep++;
	    }
	    System.out.println("-----------------------------------------");
	}

	public int getStep() {
		return currentStep;
	}

	public int getCurrentReplication() {
		return currentReplication;
	}

	public double getEstimatedDistance() {
		return Math.sqrt(steps);
	}

	public double getComputedDistance() {
		return replications.get(replications.size()-1).getDistance();
	}

    public List<Coord> getXYData() {
        return replications.get(0).getCoordinates();
    }
}
