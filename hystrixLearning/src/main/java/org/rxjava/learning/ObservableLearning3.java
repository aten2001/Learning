package org.rxjava.learning;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.Scheduler.Worker;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.functions.Func2;

public class ObservableLearning3 {

	private static Observable<List<String>> getResult(String query){
		return Observable.just(new ArrayList<String>(){
			{
				add("Data 1");
				add("Data2");
			}
		},new ArrayList<String>(){
			{
				add("Test1");
			}
		},new ArrayList<String>(){
			{
				add("New");
				add("New");
			}
		});
	}

	private static void errorHandling(){
		Observable<List<String>> urls  = getResult("test");
		urls.map( lst -> potentialException(lst))
		//below method catches error and allows continuation of
		// in absence of this .. error thrown is caught in onError and rest of flow is lost
		//		.onErrorResumeNext(new Func1<Throwable, Observable<? extends String>>() {
		//
		//			@Override
		//			public Observable<? extends String> call(Throwable arg0) {
		//				System.out.println("Got error");
		//				return Observable.just("Fallback");
		//			}
		//			
		//		})
		//		.onExceptionResumeNext(Observable.just("fb")) //same as above but only for exception not other error
		//		.onErrorReturn(new Func1<Throwable, String>() { // incase where you do want to you observable in return.. in memroy fallback
		//
		//			@Override
		//			public String call(Throwable arg0) {
		//				
		//				return "error return";
		//			}
		//		})
		//		.retry() //retry again hoping it would succed
		//		.retry(new Func2<Integer, Throwable, Boolean>() {
		//			
		//			@Override
		//			public Boolean call(Integer count, Throwable cause) {
		//				return count < 5;// return true for 5 attempts.. then return false to retry
		//			}
		//		})
		//		.retry(5) // retry 5 times

		.subscribe( elm -> System.out.println(elm),
				err -> err.printStackTrace(), //called on first error encountered.. does not proceed post that
				() -> System.out.println("completed"));
	}


	private static void retryExample(){
		Observable.create((Subscriber<? super String> s) -> {
			System.out.println("subscribing");
			s.onError(new RuntimeException("always fails"));
		}).retryWhen(attempts -> {
			return attempts.zipWith(Observable.range(1, 3), (n, i) -> i) //zips notification n with index i from Observable.range
					// function (n,i) -> i is output of zipWith which only returns count
					.flatMap(i -> {    System.out.println("delay retry by " + i + " second(s)"); // replaces Observable of of integer from zipWith to Observable.timer which make delayed retry triggers 
					return Observable.timer(i, TimeUnit.SECONDS);
					});
		}).toBlocking().forEach(System.out::println);


		private static String potentialException(List<String> vals){
			if(vals.size() == 1){
				throw new RuntimeException("error");
			}
			else{
				return vals.get(0);
			}
		}
		public static void main(String[] args){
			//		errorHandling();
			retryExample();
		}

		private static void mulithreading(){
			Observable<List<String>> urls = getResult("test");

			//		urls.subscribeOn( new Scheduler() {
			//			
			//			@Override
			//			public Worker createWorker() {
			//				return new Worker() {
			//					
			//					@Override
			//					public void unsubscribe() {
			//						// TODO Auto-generated method stub
			//						
			//					}
			//					
			//					@Override
			//					public boolean isUnsubscribed() {
			//						// TODO Auto-generated method stub
			//						return false;
			//					}
			//					
			//					@Override
			//					public Subscription schedule(Action0 arg0, long arg1, TimeUnit arg2) {
			//						// TODO Auto-generated method stub
			//						return null;
			//					}
			//					
			//					@Override
			//					public Subscription schedule(Action0 arg0) {
			//						// TODO Auto-generated method stub
			//						return null;
			//					}
			//				};
			//			}
			//		}).observeOn(scheduler)
		}

	}
