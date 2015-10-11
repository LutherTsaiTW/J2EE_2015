package hw35_instances;

public class OOP {

	public static void main(String[] args) {
		System.out.println("<< OOP(5) Instance(����a��) >>");

		Porsche p = new Porsche();
		Buick b = new Buick();
		Toyota t = new Toyota();
		
		p.maxSpeed();
		b.maxSpeed();
		t.maxSpeed();
	}

}


abstract class Car{
	String name;
	abstract void maxSpeed();
}


class Porsche extends Car{
	void maxSpeed(){
		System.out.println("360");
	}
}


class Buick extends Car{
	void maxSpeed(){
		System.out.println("280");
	}
}


class Toyota extends Car{
	void maxSpeed(){
		System.out.println("220");
	}
}