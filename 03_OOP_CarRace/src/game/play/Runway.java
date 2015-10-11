package game.play;

public class Runway {
	StringBuffer runways[];
	String line;
	String background;

	public Runway(int size, String background, int miles) {
		runways = new StringBuffer[size];
		this.background = background;

		for (int i = 0; i < size; i++) {
			runways[i] = new StringBuffer(miles);
			for (int j = 0; j < miles; j++) {
				runways[i].replace( j , j + 1 , background );
			}
			runways[i].replace(miles, miles + 1, "�");
		}

		line = new String();
		for (int i = 0; i < miles; i++) {
			line += "==";
		}
	}

	void setCar(int i, String symbol, int length) {
		runways[i].replace( length , length + 1 , symbol );
	}

	void clearCar(int i, int length) {
		runways[i].replace( length , length + 1, background );
	}

	void drawRunway(int i) {
		System.out.print( runways[i] );
	}

	void drawLine() {
		System.out.println( line );
	}
	
	void drawRun(int runs){
		System.out.print("    RUN:" + (runs + 1));
	}
}
