package Client_Serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Serveur {
	ServerSocket serv;
	
	public Serveur(int port) {
		try {
			this.serv = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String answerClient(String mess, Socket serv_sock) {
		String[] resultat = mess.split(":|\\.");
		
		return resultat[1];
	}
	
	public void readMessClient(Socket servSocket) {
		BufferedReader read;
		try {
			read = new BufferedReader (new InputStreamReader (servSocket.getInputStream()));
			String messRead = read.readLine();
			System.out.println(messRead);
			
			String answer = answerClient(messRead, servSocket);
			System.out.println("Bonjour " + answer);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void run() {
		try {
			System.out.println("Attente de connexion d'un client");
			Socket serv_socket = this.serv.accept();
			System.out.println("Un client c'est connecté");
			
			//Accept message of Client and answer
			readMessClient(serv_socket);
			
			//Accused Reception
			PrintWriter out = new PrintWriter(serv_socket.getOutputStream());
	        out.println("Vous êtes connecté !");
	        out.flush();
	        
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Serveur serv = new Serveur(2009);
		serv.run();
	}
}
