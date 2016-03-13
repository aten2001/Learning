package coreservlets.collection.map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
  public static void main(String[] args) { 
    
    new ClassPathXmlApplicationContext(
      "/coreservlets/collection/map"
      + "/applicationContext.xml");
    
  }  
}
