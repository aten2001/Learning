package org.rxjava.learning;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

public class ObservableLearning1 {
	
	private static void basicUsage(){
		// emits hellow world and completes
				Observable<String> myObservable = Observable.create(new OnSubscribe<String>() {

					@Override
					public void call(Subscriber<? super String> sub) {
						sub.onNext("Hello World!");
						sub.onCompleted();
					}
				});

				Observable<String> oberver = Observable.just("Data ","Data2");
				// simpler init that emmit these data and completes
				Subscriber<String> subscriber = new Subscriber<String>() {

					@Override
					public void onCompleted() {
						// TODO Auto-generated method stub

					}

					@Override
					public void onError(Throwable arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onNext(String arg0) {
						System.out.println(arg0);

					}
				};
				
				Action1<String> nextAction = new Action1<String>() {

					@Override
					public void call(String arg0) {
						System.out.println(arg0);
					}
				};

				myObservable.subscribe(subscriber); // bind observable to subscriber
				System.out.println("here");
				oberver.subscribe(subscriber); //will not run as one subscriber can be bound to only one observable
				oberver.subscribe(nextAction); // has over loaded methods ot pass next, error and compelte actions
				
				/// java8 lambda constructs
				
				Observable.just("Hello World")
				.subscribe(s -> System.out.println(s));
				
				// map  is another observable that can transform data emitted from Observable
				Observable.just("Hello World")
				.map(s -> s +" suffix")
				.subscribe(s -> System.out.println(s));
				
				Observable.just("Hello World")
				.map(new Func1<String, Integer>() {

					@Override
					public Integer call(String arg0) {
						return arg0.hashCode();
					}
				})
				.subscribe(s -> System.out.println(s));

				Observable.just("Hello World")
				.map(s -> s.hashCode())
				.subscribe( i -> System.out.println(i));
				
				
	}
	
	private static void simplerApproach(){
		
	}
	public static void main(String[] args){
		basicUsage();
	}
}
