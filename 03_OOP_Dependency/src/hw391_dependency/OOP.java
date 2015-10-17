package hw391_dependency;


public class OOP {

	public static void main(String[] args) {
		System.out.println("<<OOP(9-1) Dependency(¨Ì¿à)>>");
		
		//Engine e[] = {new Porsche(), new Buick(), new Toyota()};
		
		Engine e[] = {new Porsche2("Porsche"), new Buick2("Buick"), new Toyota2("Toyota")};
		
		int size = e.length;
		for (int i = 0; i < size; i++) {
			e[i].maxSpeed();
		}
	}

}

abstract class Car {
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
	public Porsche2(String name) {
		this.name = name;
	}
	
	public void maxSpeed() {
		System.out.println(name + " = 360");
	}
}


class Buick2 extends Car implements Engine{
	public Buick2(String name) {
		this.name = name;
	}
	
	public void maxSpeed() {
		System.out.println(name + " = 280");
	}
}


class Toyota2 extends Car implements Engine{
	public Toyota2(String name) {
		this.name = name;
	}
	
	public void maxSpeed() {
		System.out.println(name + " = 220");
	}
}