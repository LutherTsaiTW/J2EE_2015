package test3.exceptions;

public class CannotBidOwnCar extends Exception{
	public CannotBidOwnCar() {
		super("error.cannotBidOwnCar");
	}
}