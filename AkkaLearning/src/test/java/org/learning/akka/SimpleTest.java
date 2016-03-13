package org.learning.akka;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.util.Timeout;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;


public class SimpleTest 
{
	
	@Test
	public void test() throws Exception{
		Config config = ConfigFactory.parseFile(new File("src/test/resources/akka.conf"));
		ActorSystem system  = ActorSystem.create("test", config);
		ActorRef lb = system.actorOf(Props.create(LB.class),"LB");
		ActorRef sup = system.actorOf(Props.create(Supervisor.class),"Supervisor");
		Timeout timeout = new Timeout(Duration.create(1, TimeUnit.MINUTES));
		Future<Object> res = Patterns.ask(lb, new Data.Input("test"), timeout);
		Future<Object> second = Patterns.ask(lb, new Data.Input("test"), timeout);
		Future<Object> thrid = Patterns.ask(lb, new Data.Input("test"), timeout);
		Future<Object> fourth = Patterns.ask(lb, new Data.Input("test"), timeout);
		Future<Object> fifth = Patterns.ask(lb, new Data.Input("test"), timeout);
		Future<Object> sixth = Patterns.ask(lb, new Data.Input("test"), timeout);
		Thread.sleep(60000);
	}
	
}
