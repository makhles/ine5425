package model;

import java.util.Random;

public class RandomAngleGenerator {

	private Random random;

	public RandomAngleGenerator() {
		random = new Random();
	}

	public int generateAngle() {
		return random.nextInt(360);
	}
}
