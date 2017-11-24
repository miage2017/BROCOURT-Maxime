package tp2_part1;

public class Thread_exo1 implements Runnable{
	private String name;
	
	public Thread_exo1(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		System.out.println("Le thread demarre " + this.name);
		if (this.name == "Decompte") {
			for (int i = 1000; i > 0; i--) {
				System.out.println(this.name + " " + i);
			}
		} else {
			for (int i = 0; i <= 1000; i++) {
				System.out.println(this.name + " " + i);
			}
		}
	}

	public static void main(String[] args) {
		//Create Thread
		Thread_exo1 compteurInc = new Thread_exo1("Compteur");
		Thread_exo1 compteurDec = new Thread_exo1("Decompte");

		//Create Thread base in Executable Object
		Thread Inc = new Thread(compteurInc);
		Thread Dec = new Thread(compteurDec);
		
		//Start Thread
		Inc.start();
		Dec.start();
	}
}
