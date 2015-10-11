package engine.factory;

import java.util.Random;

public class LowPower implements Motor {
	float volume;

	public LowPower(float volume) {
		this.volume = volume;
	}

	public float power() {
		float power = (float) Math.log( volume );
		Random r = new Random();
		return (float) r.nextFloat() + (float) r.nextGaussian() * power;
	}
}
