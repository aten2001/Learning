package org.learning.nio;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class SocketLearning {

	public static void main(String[] args){


		try{
			//establish socket connection with host and port
			java.net.Socket socket = new java.net.Socket("www.google.com",80);

			System.out.println("Connected to " + socket.getInetAddress() + " on port "
					+ socket.getPort() + " from port " + socket.getLocalPort() + " of "
					+ socket.getLocalAddress());

			// Built wrapper aroudn output stream and input stream of socket to read and write on it
			OutputStream os = socket.getOutputStream();
			boolean autoFlush = true;
			PrintWriter out = new PrintWriter(os,autoFlush); //auto flush to enable flushing for println


			//NOTE ALL kinda of IO Stream could be used here
			/// OBject input and ObjectOutput stream to to Java RMI
			// DataINputStream DataOutput stream to sen raw bytes
			BufferedReader  in = new BufferedReader(
					new InputStreamReader(
							socket.getInputStream()));

			//make a git call
			out.println("GET / HTTP/1.1"); //even
			out.println("Host: www.google.com:80");
			out.println("Connection: Close");
			out.println();


			//read response
			boolean loop =  true;
			StringBuilder build = new StringBuilder();
			while(loop){
				if(in.ready()){
					int i = 0;
					while(i != -1){
						i = in.read();
						build.append((char)i);
					}
					loop = false;
				}
			}
			System.out.println(build.toString());
			socket.close();

		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void sampleSMTP() throws UnknownHostException, IOException{
		String host = "host";
		int port = 25;
		String from = "from@from.net";
		String toAddr = "to@to.net";


		java.net.Socket servSocket = new java.net.Socket(host, port);
		DataOutputStream os = new DataOutputStream(servSocket.getOutputStream());
		DataInputStream is = new DataInputStream(servSocket.getInputStream());

		if (servSocket != null && os != null && is != null) {
			os.writeBytes("HELO\r\n");
			os.writeBytes("MAIL From:" + from + " \r\n");
			os.writeBytes("RCPT To:" + toAddr + "\r\n");
			os.writeBytes("DATA\r\n");
			os.writeBytes("X-Mailer: Java\r\n");
			os.writeBytes("DATE: " + DateFormat.getDateInstance(DateFormat.FULL, Locale.US).format(new Date()) + "\r\n");
			os.writeBytes("From:" + from + "\r\n");
			os.writeBytes("To:" + toAddr + "\r\n");
		}

		os.writeBytes("Subject:\r\n");
		os.writeBytes("body\r\n");
		os.writeBytes("\r\n.\r\n");
		os.writeBytes("QUIT\r\n");
		String responseline;
		while ((responseline = is.readUTF()) != null) { 
			if (responseline.indexOf("Ok") != -1){
				servSocket.close();
				break;
			}
		}

	}



}
