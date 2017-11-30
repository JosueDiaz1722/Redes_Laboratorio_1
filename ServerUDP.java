package codigo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerUDP {

		private static int PORT=9091;
		/**
		 * Runs the server
		 */
		public static void main(String[] args) throws IOException {
			DatagramSocket serverSocket = new DatagramSocket(PORT);
			System.err.println("Server listening on port "+PORT+" using UDP conection\n");
			long initialTime = System.currentTimeMillis();
			System.out.println("Tiempo Inicial: "+initialTime+"\n");
			
			try{
				while (true){
					//Receive packet 
					byte bufferReceive[] = new byte[128];
						DatagramPacket receivePacket = new DatagramPacket(bufferReceive, bufferReceive.length);
						serverSocket.receive(receivePacket);
						InetAddress clientAdress= receivePacket.getAddress(); //obtiene el IP atravez del packet del Client
						int clientPort= receivePacket.getPort(); //Se ve que puerto aleatorio se creo en el Cliente
						System.out.println("Client port: "+clientPort+"\n");
						
						//send packet
						String msg="Holi josue";
						byte bufferSend[] = msg.getBytes();
						DatagramPacket senPacket = new DatagramPacket(bufferSend, bufferSend.length,clientAdress,clientPort);
						serverSocket.send(senPacket);
						/*long endTime = System.currentTimeMillis();
						 * System.out.println("Tiempo Final: "+endTime+"\n");
						 * long sendTime=endTime-initialTime;
						 * System.out.println("Envia el paquete en: "+sendTime+" ms\n");
						 * serverSocket.send(sendPacket);
						 * 
						 */
				}
			}
			finally{
				serverSocket.close();
			}
	}

}
