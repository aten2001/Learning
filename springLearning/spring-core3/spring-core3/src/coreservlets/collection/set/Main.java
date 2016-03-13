package coreservlets.collection.set;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
  public static void main(String[] args) { 
    
    new ClassPathXmlApplicationContext(
      "/coreservlets/collection/set"
      + "/applicationContext.xml");
    
  }  
}
