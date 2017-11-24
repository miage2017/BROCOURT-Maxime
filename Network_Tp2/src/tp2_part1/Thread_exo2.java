package tp2_part1;

public class Thread_exo2 implements Runnable {
	int pas_de_comptage = 0;
	String nom = null;
	int maxv = 0;

	public Thread_exo2(String nom, int increment, int max) {
		this.pas_de_comptage = increment;
		this.nom = nom;
		this.maxv = max;
	}
		
	/* Test_ordre0 compte de 0 a maxv moins 1 et affiche son comptage Ã  lÂ´ecran*/
	public void run() {
		System.out.format("Ici le  thread %s, je debute!\n", nom);
		int myValue=0;
		for (int i = 0; i < maxv; i++) {
			waithere(1000);
			System.out.format("[%s] dit  %d\n", nom, myValue);
			myValue += pas_de_comptage;
		}
	}

	public static void waithere(int num) {
		try {
			int delay = (int) (Math.random() * num);
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.err.format("utilisation  java %s   [nbthreads]   [maxvalue]\n Il me faut deux parametres  ",Thread_exo2.class.getCanonicalName());
			System.exit(-1);
		}

		int nb_threads = Integer.parseInt(args[0]);
		int max_value = Integer.parseInt(args[1]);

		Thread Mes_Jobs[] = new Thread[nb_threads];
		for (int currentThread = 0; currentThread < nb_threads; currentThread++) {
			String jobname = String.format("Job_%d", currentThread);
			Thread_exo2 ti_Job = new Thread_exo2(jobname, currentThread+1,max_value);
			
			System.out.format("Creating thread %s\n", jobname);
			Mes_Jobs[currentThread] = new Thread(ti_Job);
			Mes_Jobs[currentThread].start();
		}

		System.out.format("Main :Fini ici  !\n");
	}
}
