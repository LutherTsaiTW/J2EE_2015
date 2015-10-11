package engine.factory;

import java.util.Random;

public class HighPower implements Motor {
	float volume;
	float material;
	float technology;

	public HighPower(float volume, float material, float technology) {
		this.volume = volume;
		this.material = material;
		this.technology = technology;
	}

	public float power() {
		float power = (float) Math.sqrt( volume ) + (float) Math.log( material ) + (float) Math.sqrt( technology );
		Random r = new Random();
		return (float) r.nextFloat() + (float) r.nextGaussian() * power;
	}
}
