package org.learning.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class App 
{
	private static byte[] data = new byte[255];

	public static void main(String[] args) throws IOException {
		for (int i = 0; i < data.length; i++)
			data[i] = (byte) i;

		ServerSocketChannel server = ServerSocketChannel.open();
		server.configureBlocking(false); //non blocking

		server.socket().bind(new InetSocketAddress(9000)); // start on port 9000
		Selector selector = Selector.open();
		server.register(selector, SelectionKey.OP_ACCEPT);  //have selector register to be triggered for connection accept 

		while (true) {
			selector.select();
			Set<SelectionKey> readyKeys = selector.selectedKeys(); //get selection keys
			Iterator<SelectionKey> iterator = readyKeys.iterator();
			while (iterator.hasNext()) {
				SelectionKey key = (SelectionKey) iterator.next();
				iterator.remove();
				if (key.isAcceptable()) {
					SocketChannel client = server.accept();
					System.out.println("Accepted connection from " + client);
					client.configureBlocking(false);
					ByteBuffer source = ByteBuffer.wrap(data);
					SelectionKey key2 = client.register(selector, SelectionKey.OP_WRITE);
					key2.attach(source);
				} else if (key.isWritable()) {
					SocketChannel client = (SocketChannel) key.channel();
					ByteBuffer output = (ByteBuffer) key.attachment();
					if (!output.hasRemaining()) {
						output.rewind();
					}
					client.write(output);
				}
				key.channel().close();
			}
		}
	}
}
