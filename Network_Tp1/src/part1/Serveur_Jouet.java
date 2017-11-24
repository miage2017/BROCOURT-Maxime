package part1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur_Jouet {

	public static void main(String[] args) {
		try {
			ServerSocket socketGestion = new ServerSocket(12000);
			Socket connection = socketGestion.accept();
			
			InputStreamReader isr = new InputStreamReader(connection.getInputStream(), "UTF-8");
			BufferedReader flux_entrant = new BufferedReader(isr);
			
			String nouveau_message = null;
			while((nouveau_message = flux_entrant.readLine()) != null) {
				if (nouveau_message.equalsIgnoreCase("finish")) {
					socketGestion.close();
					return;
				}
				System.out.println(nouveau_message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
