package org.rxjava.learning;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;

public class SyncAsyncObservable {

	//TODO figure out why java8 lambda expression didnt work here
//	private static Observable<String> blockingObservable(){
//		return Observable.create( aSubsciber -> {
//			for(int i=0; (i<50 && !aSubsciber.isUnsubscribed()); i++){
//				aSubsciber.onNext("value_"+i);
//			}
//			if(!aSubsciber.isUnsubscribed()){
//				aSubsciber.onCompleted();
//			}
//		});
//	}
	
	public static interface BeautifulGirl{
		
		public String getName();
		
	}
	
	public BeautifulGirl getMeMyWife(){
		return  (() -> "neeraj");
	}
	
	private static Observable<String> blockingObservable2(){
		return Observable.create(new OnSubscribe<String>() {

			@Override
			public void call(Subscriber<? super String> aSubsciber) {
				for(int i=0; (i<50 && !aSubsciber.isUnsubscribed()); i++){
					aSubsciber.onNext("value_"+i);
				}
				if(!aSubsciber.isUnsubscribed()){
					aSubsciber.onCompleted();
				}
			}
		});
	}
	
	private static Observable<String> asyncObservable(){
		return Observable.create(new OnSubscribe<String>() {

			@Override
			public void call(Subscriber<? super String> aSubsciber) {
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						for(int i=0; (i<50 && !aSubsciber.isUnsubscribed()); i++){
							aSubsciber.onNext("value_"+i);
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						if(!aSubsciber.isUnsubscribed()){
							aSubsciber.onCompleted();
						}
					}
				}).start();
			}
		});
	}
	public static void main(String[]  args){
		Observable<String> ob =blockingObservable2();
//		Observable<String> ob = asyncObservable();
		ob.subscribe(it -> System.out.println(it));
		ob.subscribe(it -> System.out.println("2"+it));
		;
//		asyncObservable().subscribe(it -> System.out.println(it));
		
//		asyncObservable().skip(10).take(5)
//		.map(val -> val + "_mapped")
//		.subscribe(it -> System.out.println(it));
	}
	
}
