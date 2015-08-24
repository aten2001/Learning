package org.rxjava.learning;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;

public class AsyncFetchOfWikipediaPages {

	private Observable<String> getWikiArticles(String... article){
		return Observable.create(new OnSubscribe<String>() {
			
			@Override
			public void call(Subscriber<? super String> sub) {
				new Thread(() -> {
					for(String name : article){
						if(true == sub.isUnsubscribed()){
							return;
						}
						
						sub.onNext(new URL("http://en.wikipedia.org/wiki/"+name));
					}
				}).start();
				
			}
		})
	}
}
