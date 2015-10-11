package car.factory;

import engine.factory.*;
public class Porsche extends Car implements Engine {

	private Motor motor;

	public Porsche(Motor power) {
		this.motor = power;
	}

	public void maxSpeed() {
		float power = motor.power();
		mileage += Math.round( power );
		
		if (mileage < 0)
			mileage = 0;
	}
}
