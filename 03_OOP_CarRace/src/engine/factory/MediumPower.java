package engine.factory;

import java.util.Random;

public class MediumPower implements Motor {
	float volume;
	float material;
	
	public MediumPower(float volume, float material) {
		this.volume = volume;
		this.material = material;
	}

	public float power() {
		float power = (float) Math.sqrt( volume ) + (float) Math.log( material );
		Random r = new Random();
		return (float) r.nextFloat() + (float) r.nextGaussian() * power;
	}
}
