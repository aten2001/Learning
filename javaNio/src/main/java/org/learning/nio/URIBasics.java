package org.learning.nio;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class URIBasics {

	
	public static void fromFile() throws URISyntaxException, MalformedURLException{
		File file  = new File("/src/test/resources/file.txt");
		System.out.println(file.toURI().toString());
		
		URI uri = new URI("file://src/test/resources/file.txt");
		URL url = uri.toURL();
		uri = url.toURI();
	}
	
	
	public static void main(String[] args){
		fromFile();
	}
	
	
}
