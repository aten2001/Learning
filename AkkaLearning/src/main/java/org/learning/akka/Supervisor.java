package org.learning.akka;

import akka.actor.UntypedActor;

public class Supervisor extends UntypedActor {

	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg instanceof Data.SupInput){
			Data.SupInput sup = (Data.SupInput) msg;
			System.out.println("Revcieved msg "+sup.name);
		}else{
			unhandled(msg);
		}
		
	}

}
