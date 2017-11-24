package tp2_part1;

public class ExtendsThread extends Thread{
	    String nom="Toto";
	    int maxv= 10 ;
	    
	    public ExtendsThread(String  nom) {
	    	this.nom=nom;
		}

	    public void run() {
	    	System.out.format("Ici le  thread %s, je debute!\n", nom);
			for (int i = 0; i < maxv; i++) {
			    System.out.format("[%s] dit  je suis la %d\n",nom,i); 	
		    }
		}
	    
	    public static void main(String[] args)  throws  Exception {
			String jobname = String.format("Job_%d", 0); 
			ExtendsThread   objet_executable   = new ExtendsThread( jobname);
			
			System.out.format("Creating thread %s\n", jobname);
			
			objet_executable.start();
			System.out.format("Main :Fini ici  !\n");
	    }
}
