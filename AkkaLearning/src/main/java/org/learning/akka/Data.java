package org.learning.akka;

public interface Data {

	public class Input{
		
		public Input(String name) {
			this.name = name;
		}

		public String name;
	}
	
	public class SupInput {
		public SupInput(String name) {
			this.name = name;
			this.val = 10;
		}
		
		public String name;
		public int val;
	}
	
	public class Output {
		public String name;
		public int val;
	}
}
