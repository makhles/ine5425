package controller;

import java.util.ArrayList;
import java.util.List;

import model.Bucket;
import model.Coord;
import model.RandomAngleGenerator;

public class MonteCarloSimulation {

	private RandomAngleGenerator angleGenerator;
	private List<Coord> walkedPositions;
	private List<Double> walkedDistances;
	private List<Bucket> frequencyDistribution;
	
	public MonteCarloSimulation(int steps) {
		angleGenerator = new RandomAngleGenerator();
	}

	public void executeOnce(int steps) {
		int degrees;
		double radians;
		double factor = Math.PI / 180.0d;
		double x = 0.0d;
		double y = 0.0d;
		walkedPositions = new ArrayList<>();
		walkedPositions.add(new Coord(x,y));
		walkedDistances = new ArrayList<>();
		walkedDistances.add(0.0d);

		for (int step = 1; step <= steps; step++) {
			degrees = angleGenerator.generateAngle();
			radians = degrees * factor; 
			x += Math.cos(radians);
			y += Math.sin(radians);
			walkedPositions.add(new Coord(x,y));
			walkedDistances.add(Math.sqrt(x*x + y*y));
		}
	}
	
	public void executeSeveralTimes(int steps, int replications) {
		int degrees;
		double radians;
		double estimated = Math.sqrt(steps);
		double factor = Math.PI / 180.0d;
		double x = 0.0d;
		double y = 0.0d;
		List<Double> differences = new ArrayList<>();

		for (int rep = 1; rep <= replications; rep++) {
			x = 0.0d;
			y = 0.0d;
			for (int step = 1; step <= steps; step++) {
				degrees = angleGenerator.generateAngle();
				radians = degrees * factor; 
				x += Math.cos(radians);
				y += Math.sin(radians);
			}
			differences.add(Math.abs(Math.sqrt(x*x + y*y) - estimated));
		}
		createHistogramDistribution(differences);
	}

	private void createHistogramDistribution(List<Double> samples) {
		int bucketIndex;
		int numberOfBuckets = (int) Math.ceil(Math.sqrt(samples.size()));
		double bucketSize;
		double max = 0.0d;
		double min = Double.MAX_VALUE;
		frequencyDistribution = new ArrayList<>();

		// Number of buckets is limited to 30 (requested)
		if (numberOfBuckets > 30) {
			numberOfBuckets = 30;
		}

		// Find the minimum and maximum values in the samples
		for (Double sample : samples) {
			if (sample > max) {
				max = sample;
			}
			if (sample < min) {
				min = sample;
			}
		}

		min = Math.floor(min);
		max = Math.ceil(max);
		bucketSize = (max - min) / (double) numberOfBuckets;

		double start = min;
		double end;
		for (int i = 0; i < numberOfBuckets; i++) {
			end = start + bucketSize;
			frequencyDistribution.add(new Bucket(start, end));
			start = end;
		}

		// Compute the frequency of samples in the buckets
		for (Double sample : samples) {
			bucketIndex = (int) Math.floor((sample - min) / bucketSize);
			frequencyDistribution.get(bucketIndex).addSample();
		}
	}

	public List<Coord> getWalkedPositions() {
		return walkedPositions;
	}

	public List<Double> getWalkedDistances() {
		return walkedDistances;
	}

	public List<Bucket> getFrequencyDistribution() {
		return frequencyDistribution;
	}
}
