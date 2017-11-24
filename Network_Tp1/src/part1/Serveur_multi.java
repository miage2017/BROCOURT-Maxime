package part1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Serveur_multi {
	ServerSocket serv;
	
	public Serveur_multi(int port) {
		try {
			this.serv = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void Service_Client(Socket activeSocket){
		try {
		InputStreamReader isr = new InputStreamReader(activeSocket.getInputStream(), "UTF-8");
		BufferedReader flux_entrant = new BufferedReader(isr);
		
			String nouveau_message = null;
			while((nouveau_message = flux_entrant.readLine()) != null) {
				if (nouveau_message.equalsIgnoreCase("finish")) {
					activeSocket.close();
					run();
					return;
				}
			System.out.println(nouveau_message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		try {
			Socket connection = this.serv.accept();
			this.serv.setSoTimeout(5000);
			Service_Client(connection);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Serveur_multi serv = new Serveur_multi(12000);
		serv.run();
	}

}
