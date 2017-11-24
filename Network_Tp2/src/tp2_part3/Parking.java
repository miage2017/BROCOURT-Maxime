package tp2_part3;

import java.util.Random;

public class Parking{
	int capacity;
	
	public Parking(int cap) {
		this.capacity = cap;
	}
	
	public synchronized void enter() {
		System.out.println(Thread.currentThread().getName() + " arrive");
		while (this.capacity <= 0 ) {
			System.out.println(Thread.currentThread().getName() + " attend...");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		if (this.capacity > 0) {
			this.capacity = this.capacity -1 ;
			System.out.println(Thread.currentThread().getName() + " rentre");
			System.out.println("Il reste " + this.capacity + " places");
		}
		
	}
	
	public void leave() {
		System.out.println(Thread.currentThread().getName() + " repart");
		this.capacity = this.capacity +1;
		System.out.println("Il reste " + this.capacity + " places");
	}
	
	public static void main(String args[]) {
		Parking park = new Parking(2);
		Random r = new Random();
		
		for (int i = 0; i < 30; i++) {
			try {
				Thread.sleep(r.nextInt(5) * 10);
			} catch(InterruptedException e) {}
			new Thread(new Voiture("Voiture" + i, park)).start();
		}
	}
}
