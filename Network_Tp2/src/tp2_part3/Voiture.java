package tp2_part3;

import java.util.Random;

public class Voiture implements Runnable{
	private String id;
	private Parking cartPark;
	private Random r;
	
	public Voiture(String id, Parking park) {
		this.id = id;
		this.cartPark = park;
		r = new Random();
	}

	@Override
	public void run() {
		Thread.currentThread().setName(this.id);
		cartPark.enter();
		try {
			Thread.sleep(r.nextInt(10) * 100);
		} catch (InterruptedException e) {
			return;
		}
		cartPark.leave();
	}
}
