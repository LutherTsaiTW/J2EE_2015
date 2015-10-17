package hw382_anonymous;

public class OOP {

	public static void main(String[] args) {
		System.out.println("<< OOP(82) Anonymous(°Î¦W¨ç¦¡) >>");

		Engine porsche = new Engine() {
			public void maxSpeed() {
				System.out.println("360");
			}
		};
		Engine buick = new Engine() {
			public void maxSpeed() {
				System.out.println("280");
			}
		};
		Engine toyota = new Engine() {
			public void maxSpeed() {
				System.out.println("220");
			}
		};
		
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