package org.learning.nio;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;

public class SampleFileTransfer {

	
	public void server() throws IOException{
		ServerSocket servsock = new ServerSocket(123456);
	    File myFile = new File("src/main/resources/sample.txt");
	    while (true) {
	      java.net.Socket sock = servsock.accept();
	      byte[] mybytearray = new byte[(int) myFile.length()];
	      BufferedInputStream bis = new BufferedInputStream(new FileInputStream(myFile));
	      bis.read(mybytearray, 0, mybytearray.length);
	      OutputStream os = sock.getOutputStream();
	      os.write(mybytearray, 0, mybytearray.length);
	      os.flush();
	      sock.close();
	    }
	  }
	
	public void client() throws IOException{
		java.net.Socket sock = new java.net.Socket("127.0.0.1", 123456);
	    byte[] mybytearray = new byte[1024];
	    InputStream is = sock.getInputStream();
	    FileOutputStream fos = new FileOutputStream("src/main/resources/sample.txt");
	    BufferedOutputStream bos = new BufferedOutputStream(fos);
	    int bytesRead = is.read(mybytearray, 0, mybytearray.length);
	    bos.write(mybytearray, 0, bytesRead);
	    bos.close();
	    sock.close();
	}
}
