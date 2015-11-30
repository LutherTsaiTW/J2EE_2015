package test3.exceptions;

public class LowerThanPriceException extends Exception{
	public LowerThanPriceException() {
		super("error.lowerthanprice");
	}
}
