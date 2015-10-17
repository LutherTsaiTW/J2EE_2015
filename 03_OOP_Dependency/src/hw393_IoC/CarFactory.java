package hw393_IoC;


public class CarFactory {
	
	Engine create(String name){
		switch (name) {
		case "Porsche":
			return new Porsche();
		case "Buick":
			return new Buick();
		case "Toyota":
			return new Toyota();
		case "Porsche2":
			return new Porsche2(name);
		case "Buick2":
			return new Buick2(name);
		case "Toyota2":
			return new Toyota2(name);
		default:
			return null;
		}
	}
}

class Car {
	String name;
}

interface Engine {
	abstract void maxSpeed();
}


class Porsche extends Car implements Engine{
	public void maxSpeed() {
		System.out.println("360");
	}
}


class Buick extends Car implements Engine{
	public void maxSpeed() {
		System.out.println("280");
	}
}


class Toyota extends Car implements Engine{
	public void maxSpeed() {
		System.out.println("220");
	}
}

class Porsche2 extends Car implements Engine{
	Porsche2(String name) {
		this.name = name;
	}
	
	public void maxSpeed() {
		System.out.println(name + " = 360");
	}
}


class Buick2 extends Car implements Engine{
	Buick2(String name) {
		this.name = name;
	}
	
	public void maxSpeed() {
		System.out.println(name + " = 280");
	}
}


class Toyota2 extends Car implements Engine{
	Toyota2(String name) {
		this.name = name;
	}
	
	public void maxSpeed() {
		System.out.println(name + " = 220");
	}
}