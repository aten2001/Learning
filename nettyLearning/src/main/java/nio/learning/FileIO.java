package nio.learning;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileIO {

	public static void read(String path) throws IOException{
		FileInputStream fin = new FileInputStream(path);
		FileChannel fc = fin.getChannel(); // create Channel
		ByteBuffer buffer = ByteBuffer.allocate( 1024 );
		fc.read( buffer );
	}
	
	public static void write(byte[] message, String path) throws IOException{
		FileOutputStream fout = new FileOutputStream(path);
		FileChannel fc = fout.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate( 1024 );
		for(int i=0; i <message.length; i++){
			buffer.put( message[i]);
		}
		buffer.flip();
		fc.write( buffer );
	}
	
	public static void copy(String src, String dest) throws IOException {
		FileChannel fin = new FileInputStream(src).getChannel();
		FileChannel fout = new FileInputStream(dest).getChannel();
		ByteBuffer buffer = ByteBuffer.allocate( 1024 );
		
		while( true ){
			buffer.clear();
			int r  = fin.read( buffer );
			if( r == -1 ) break;
			buffer.flip();
			fout.write( buffer );
		}
	}
}
