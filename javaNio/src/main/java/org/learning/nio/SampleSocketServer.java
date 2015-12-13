package org.learning.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketTimeoutException;

public class SampleSocketServer {

	public static void main(String[] args) throws IOException, InterruptedException{
		ServerSocket server = new ServerSocket();
		server.setSoTimeout(200); //timeout of acces in 200ms
		server.setPerformancePreferences(2, 3, 1); //priority on latency then low conn time and then high bandwidth
		server.setReuseAddress(true); //enable reuse of address and port during timed_wait of a prev socket
//		server.setReceiveBufferSize(size);
		server.bind(new InetSocketAddress(8080));
		while(true){
			try{
				java.net.Socket sock = server.accept();
				InetAddress addr = sock.getInetAddress();
				System.out.println("Connection made to " + addr.getHostName() + " (" + addr.getHostAddress()
						+ ")");
				Thread.sleep(5000);
				sock.close();
			}catch(SocketTimeoutException t){
				System.out.println("timed out ");
			}
			
		}
	}

}
