package tp2_part4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dormeur implements Runnable{
	String nom = null;
	Thread antecedent = null;
	
	public Dormeur(String nom, List<Thread> list_antecedents) {
		this.nom = nom;
		for (Thread t : list_antecedents) {
			this.antecedent = t;
		}
	}
	
	public synchronized void dormir() {
		try {
			Thread.sleep((int) (1e3 * Math.random()));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		System.out.format("%s, Commence !\n", nom);
		if (antecedent != null)
			try {
				antecedent.join();
				System.out.format("%s: %s a fini !!\n", nom, antecedent.getName());
			} catch (InterruptedException e) {
				System.out.format("Issue with %s inte while waiting\n", nom);
			}
		System.out.format("%s dit: Super je peux enfin demarrer! !\n", nom);
		dormir(); 
		System.out.format("%s dit: Super j'ai FINI!!! !\n", nom);
	}
	
	public static void main(String[] args) {
		List<Thread> mon_antecedent = new ArrayList();
		
		Dormeur OC = new Dormeur("exe C", mon_antecedent);
		Thread TC = new Thread(OC, "Dracaufeu");

		mon_antecedent = Arrays.asList(TC);
		Dormeur OB = new Dormeur("exe B", mon_antecedent);
		Thread TB = new Thread(OB, "Tortank");
		
		mon_antecedent = Arrays.asList(TB);
		Dormeur OA = new Dormeur("exe A", mon_antecedent);
		Thread TA = new Thread(OA, "Florizarre");
		
		TA.start();
		TB.start();
		TC.start();
		
		System.out.format("Main :Fini ici  !\n");
	}
}
