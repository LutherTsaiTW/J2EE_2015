package hw383_lambda;

public class OOP {

	public static void main(String[] args) {
		System.out.println("<< OOP(83) Lambda(Lambda »yªk) >>");

		Engine porsche = () -> System.out.println("360");
		Engine buick = () -> System.out.println("280");
		Engine toyota = () -> System.out.println("220");

		Engine e[] = { porsche, buick, toyota };
		for(Engine element : e){
			element.maxSpeed();
		}
	}

}


interface Engine {
	abstract void maxSpeed();
}