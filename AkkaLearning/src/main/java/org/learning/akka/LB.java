package org.learning.akka;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.ConsistentHashingRouter.ConsistentHashableEnvelope;
import akka.routing.FromConfig;


public class LB extends UntypedActor
{

	ActorRef router = getContext().actorOf(
			FromConfig.getInstance().props(Props.empty()),"router");

	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg instanceof Data.Input){
			Data.Input input = (Data.Input) msg;
			ConsistentHashableEnvelope nextData = new ConsistentHashableEnvelope(new Data.SupInput(input.name), input.name);
			router.tell(nextData, getSender());
			System.out.println("here "+input.name);
		}else{
			System.out.println("herer");
			unhandled(msg);
		}
		
	}
}
