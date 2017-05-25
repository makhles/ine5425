package controller;

import java.util.ArrayList;
import java.util.List;

import model.RandomAngleGenerator;
import model.Replication;

public class MonteCarloSimulation {

	private int steps;
	private int numberOfReplications;

	private int step;
	private int currentReplication;
	private RandomAngleGenerator angleGenerator;
	private List<Replication> replications;
	
	private double estimatedDistance;
	private double computedDistance;

	public MonteCarloSimulation(int steps, int numberOfReplications) {
		this.steps = steps;
		this.numberOfReplications = numberOfReplications;
		init();
	}

	private void init() {
		estimatedDistance = 0.0;
		computedDistance = 0.0;
		angleGenerator = new RandomAngleGenerator();
		replications = new ArrayList<>(numberOfReplications);
	}

	public void start() {
		computeEstimatedDistance();
		currentReplication = 1;

		while(currentReplication <= numberOfReplications) {
			Replication replication = new Replication(steps);
			step = 1;
			while(step <= steps) {
				int angle = angleGenerator.generateAngle();
				double angleInRadians = angle * Math.PI / 180.0; 
				double x = Math.cos(angleInRadians);
				double y = Math.sin(angleInRadians);
				replication.addCoordinate(x, y);
				computedDistance += Math.sqrt(x*x + y*y);
				step++;
			}
			replications.add(replication);
			currentReplication++;
		}
	}

	private void computeEstimatedDistance() {
		estimatedDistance = Math.sqrt(steps);
	}

	public int getStep() {
		return step;
	}

	public int getCurrentReplication() {
		return currentReplication;
	}

	public double getEstimatedDistance() {
		return estimatedDistance;
	}

	public double getComputedDistance() {
		return computedDistance;
	}
}
