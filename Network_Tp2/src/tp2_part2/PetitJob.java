package tp2_part2;

public class PetitJob implements Runnable{
    private ObjetEntier notre_entier ;
    private int increment;
    
    PetitJob(ObjetEntier notre_entier , int increment) {
    	this.increment=increment; 
    	this.notre_entier = notre_entier;
    }
    
    public void run() {
    	for (int i = 0; i < 10e4; i++)
	    {
    		notre_entier.add(increment);
	    }  
    	System.out.format("Thread faisant %d - termine, compteur= %s\n" , increment,notre_entier);
    }
    
	public static void main(String[] args) {
		ObjetEntier Compteur = new ObjetEntier(); 
		
		PetitJob objex1= new PetitJob(Compteur, 1);
		PetitJob objex2= new PetitJob(Compteur, -1);
		
		Thread tache1 = new Thread(objex1 ,"incrementeur");
		Thread tache2 = new Thread(objex2, "decrementeur");

		try {
			tache1.start();
			tache1.join();
			tache2.start();
			tache2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.format("Le compteur vaut %s\n" , Compteur);
		System.out.format("Le compteur vaut %s\n" , Compteur);
	}
}
