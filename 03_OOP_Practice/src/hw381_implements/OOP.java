package hw381_implements;

public class OOP {

	public static void main(String[] args) {
		System.out.println("<< OOP(81) Implements(實體物件) >>");

		Engine porsche = new Porsche();
		Engine buick = new Buick();
		Engine toyota = new Toyota();
		
		Engine e[] = {porsche, buick, toyota};
		
		int size = e.length;
		for(int i = 0;i < size;i++){
			e[i].maxSpeed();
		}
	}

}


interface Engine {
	abstract void maxSpeed();
}


class Porsche implements Engine {
	public void maxSpeed() {
		System.out.println("360");
	}
}


class Buick implements Engine {
	public void maxSpeed() {
		System.out.println("280");
	}
}


class Toyota implements Engine {
	public void maxSpeed() {
		System.out.println("220");
	}
}