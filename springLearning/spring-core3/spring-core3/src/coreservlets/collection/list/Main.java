package coreservlets.collection.list;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
  public static void main(String[] args) { 
    
    new ClassPathXmlApplicationContext(
      "/coreservlets/collection/list"
      + "/applicationContext.xml");
    
  }  
}
