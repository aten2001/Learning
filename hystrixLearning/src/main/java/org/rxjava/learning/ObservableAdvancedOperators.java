package org.rxjava.learning;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.functions.Action1;
import rx.functions.Func2;
import rx.Subscriber;

public class ObservableAdvancedOperators {
	
	private static Observable<String> users(Integer id){
		return Observable.create(new OnSubscribe<String>() {

			@Override
			public void call(Subscriber<? super String> aSubsciber) {
				for(int i=0; (i<50 && !aSubsciber.isUnsubscribed()); i++){
					aSubsciber.onNext("user_"+i);
				}
				if(!aSubsciber.isUnsubscribed()){
					aSubsciber.onCompleted();
				}
			}
		});
	}

	private static Observable<String> video(Integer id){
		return Observable.create(new OnSubscribe<String>() {

			@Override
			public void call(Subscriber<? super String> aSubsciber) {
				for(int i=0; (i<50 && !aSubsciber.isUnsubscribed()); i++){
					aSubsciber.onNext("videos_"+i);
				}
				if(!aSubsciber.isUnsubscribed()){
					aSubsciber.onCompleted();
				}
			}
		});
	}
	
	public static void main(String[] main){
		Map<Integer,Integer> userVideoIdMap = new HashMap<Integer, Integer>();
		userVideoIdMap.put(12, 12);
		for(Integer userId : userVideoIdMap.keySet()){
			Observable<String> users = users(userId);
			Observable<String> videos  = video(userVideoIdMap.get(userId));
			Observable.zip(users, videos, new Func2<String, String, String>() {

				@Override
				public String call(String user, String video) {
					return user+"_"+video;
				}
			}).subscribe( val -> System.out.println(val));
			
			Observable.merge(users,videos) //append video emittions post user emittions in case of sync
			.subscribe( val -> System.out.println(val));
			
			
			users.reduce((a, b) -> a + " : " + b) //reduce emmited events (Useful for aggregation)
			.subscribe(val -> System.out.println(val));
			
			
		}
	}
}
