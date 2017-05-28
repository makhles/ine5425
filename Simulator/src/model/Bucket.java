package model;

public class Bucket {

	private Integer samples;
	private Double start;
	private Double end;

	public Bucket(Double start, Double end) {
		this.start = start;
		this.end = end;
		samples = 0;
	}

	public void addSample() {
		samples++;
	}

	public Double getMeanValue() {
		return (start + end) / 2;
	}

	public Integer getSamples() {
		return samples;
	}

	public Double getStart() {
		return start;
	}

	public Double getEnd() {
		return end;
	}
}
