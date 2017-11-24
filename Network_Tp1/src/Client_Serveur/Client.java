package Client_Serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	private Socket connexion;
	
	public Client(InetAddress host, int port) {
		try {
			this.connexion = new Socket(host, port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void Service_Client(Socket activeSocket, String nomHost){
		try {
			PrintWriter messBienvenue = new PrintWriter(activeSocket.getOutputStream());
			messBienvenue.println("Bonjour je suis :" + nomHost);
			messBienvenue.flush();
			
			BufferedReader read = new BufferedReader (new InputStreamReader (activeSocket.getInputStream()));
			String messRead = read.readLine();
			System.out.println(messRead);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void transferData(Socket activeSocket) {
		InputStreamReader isr;
		try {
			isr = new InputStreamReader(activeSocket.getInputStream(), "UTF-8");
			BufferedReader flux_entrant = new BufferedReader(isr);
			
			String nouveau_message = null;
			while((nouveau_message = flux_entrant.readLine()) != null) {
				if (nouveau_message.equalsIgnoreCase("finish")) {
					activeSocket.close();
					return;
				}
			System.out.println(nouveau_message);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		System.out.println("Connexion en attente");
		Service_Client(this.connexion, "Maxime");
		//transferData(this.connexion);
		try {
			connexion.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Client c;
		try {
			c = new Client(InetAddress.getLocalHost() , 2009);
			c.run();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
