package hw393_IoC;


public class OOP {

	private Engine e[];
	
	OOP (Engine e[])
	{
		this.e = e;
	}
	
	public void run() {
		int size = e.length;
		for (int i = 0; i < size; i++) {
			e[i].maxSpeed();
		}
	}
}
