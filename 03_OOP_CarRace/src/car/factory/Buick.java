package car.factory;

import engine.factory.*;


public class Buick extends Car implements Engine {

	private Motor motor;

	public Buick(Motor motor) {
		this.motor = motor;
	}

	public void maxSpeed() {
		float power = motor.power();
		mileage += Math.round( power );
		
		if (mileage < 0)
			mileage = 0;
	}
}