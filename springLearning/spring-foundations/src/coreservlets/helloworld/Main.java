package coreservlets.helloworld;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
  public static void main(String[] args) {
    BeanFactory beanFactory =
      new ClassPathXmlApplicationContext(
        "/coreservlets/helloworld/applicationContext.xml");

    HelloWorld helloWorld = (HelloWorld)beanFactory.getBean("helloWorld");

    helloWorld.execute();
  }
}
