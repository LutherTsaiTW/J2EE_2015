package game.play;

import car.factory.*;
import engine.factory.*;
import constant.Constant;

public class Game implements Constant {
	public static void main(String args[]) {
		int runs;
		String winner = null;

		Motor m[] = { new HighPower(7.0F, 4.5F, 5.0F),
				new MediumPower(3.9F, 5.2F), new LowPower(3.0F) };
		Car c[] = { new Porsche(m[0]), new Buick(m[1]), new Toyota(m[2]) };

		int size = c.length;

		Engine e[] = { (Engine) c[0], (Engine) c[1], (Engine) c[2] };

		String symbols[] = { "P", "B", "T" };

		Runway runway = new Runway(size, background, miles);

		runway.drawLine();
		for (runs = 0; winner == null; runs++) {
			for (int i = 0; i < size; i++) {
				e[i].maxSpeed();
				if (c[i].getMileage() >= miles) {
					winner = symbols[i];
					c[i].setMileage(miles);
				}
				runway.setCar(i, symbols[i], c[i].getMileage());
				runway.drawRunway(i);
				runway.clearCar(i, c[i].getMileage());
				if (i == 1)
					runway.drawRun(runs);
				System.out.println();
			}
			runway.drawLine();

			try {
				Thread.sleep(delay);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}

		System.out.print("The Winner is " + winner + " after " + runs
				+ " runs.");
	}
}
