package hw393_IoC;

import hw393_IoC.OOP;
import hw393_IoC.CarFactory;

public class AP {

	public static void main(String[] args) {
		System.out.println("<<OOP(9-2) Inversion of Control(±±¨î¤ÏÂà)>>");
		
		//String cars[] = { "Porsche", "Buick", "Toyota" };
		String cars[] = { "Porsche2", "Buick2", "Toyota2" };
		
		int size = cars.length;
		
		Engine e[] = new Engine[size];
		
		CarFactory cf = new CarFactory();
		
		for (int i = 0; i < size; i++) {
			e[i] = cf.create(cars[i]);
		}
		
		OOP oop = new OOP(e);
		oop.run();
	}

}

