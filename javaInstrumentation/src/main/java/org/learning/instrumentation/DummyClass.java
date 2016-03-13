package org.learning.instrumentation;

public class DummyClass {
	
	public void sampleMethod() throws InterruptedException {
		long randomDuration = (long) (500 + Math.random() * 700);
		System.out.println("BODY - "+randomDuration);
		Thread.sleep(randomDuration);
	}
}
