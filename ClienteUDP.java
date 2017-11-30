package codigo;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.swing.JOptionPane;

public class ClienteUDP {
	private static int SERVER_PORT =9091;
	/**
	 * Runs the client as an application. First it displays a dialog 
	 * box asking for the IP address or hotsname of a ahost running
	 * the data server, then connects to it and displays the data that 
	 * the server sends
	 */
	public static void main(String[] args) throws IOException {
		String serverAddress = JOptionPane.showInputDialog("Enter Ip Addres of a machine that is running the data"
				+ " service on port "+SERVER_PORT+";");
		//Send packet (Request)
		DatagramSocket clientSocket  = new DatagramSocket();
		byte bufferSend[] = serverAddress.getBytes(); //transforma Ip a bytes (String-bytes)
		DatagramPacket sendPacket = new DatagramPacket(bufferSend, bufferSend.length,InetAddress.getByName(serverAddress),SERVER_PORT);
		clientSocket.send(sendPacket);
		//InetAddress solo transforma la IP a InetAddress
		
		//Receive packet
		byte bufferReceive[] = new byte[128];
		DatagramPacket receivePacket = new DatagramPacket(bufferReceive, bufferReceive.length);
		clientSocket.receive(receivePacket); //este packet esta en bytes
		
		//Transform bytes to String
		InputStream myInputStream = new ByteArrayInputStream(receivePacket.getData());
		BufferedReader input = new BufferedReader(new InputStreamReader(myInputStream));
		String answer = input.readLine();
		
		//Display mensaje
		JOptionPane.showMessageDialog(null,answer);
		clientSocket.close();
		System.exit(0);
	}
}
