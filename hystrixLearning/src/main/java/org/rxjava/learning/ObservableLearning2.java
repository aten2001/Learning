package org.rxjava.learning;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

public class ObservableLearning2 {

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
		});
	}
	
	public static void main(String[] args){
		Observable<List<String>> query = getResult("Test");
		query.subscribe(data -> {
			for(String d : data){ // ugly loop needed
				System.out.println(d);
			}
		});
		
		//using Observable.from
		query.subscribe(data -> {
			Observable.from(data)
			.subscribe(val -> System.out.println(val) );
		});
		
		//flat map.. flattens collection them runs map
		query.flatMap(new Func1<List<String>, Observable<String>>() {

			@Override
			public Observable<String> call(List<String> urls) {
				return Observable.from(urls);
			}
		})
		.subscribe(val -> System.out.println(val));
		
		//java8
		query.flatMap(urls -> Observable.from(urls))
		.subscribe(url -> System.out.println(url));
		
		System.out.println("!!!!!");
		query.map(new Func1<List<String>, Observable<String>>() {

			@Override
			public Observable<String> call(List<String> arg0) {
				return Observable.from(arg0);
			}
		}).subscribe(val -> System.out.println(val)); //does not work
		
		// map converts type to another type and returns within same Observable
		//flatmap flattens by converting type to Observable<newType> and returning newType to subscriber
		 
		
		query.flatMap(lst -> Observable.from(lst))
		.flatMap(v -> getFirstChar(v)) //this could be a network call executed in asyncronus thread
		.subscribe(title -> System.out.println(title));
		
		//above method culd be used to couple many api calls in process of building a flow
		
		query.flatMap(lst -> Observable.from(lst))
		.flatMap(v -> getFirstChar(v)) //this could be a network call executed in asyncronus thread
		.filter( title -> title != 'T') // remove T
		.take(1) //take only 1 of 2 D.. note if count > 2 it will still output only 2 D
		.doOnNext(title -> System.out.println(title+"_alternative action")) // alternative save fork between actual subscriber
		.subscribe(title -> System.out.println(title));
		
		//we can build customoperators as well 
		
		
	}
	
	private static Observable<Character> getFirstChar(String data){
		return Observable.just(data.charAt(0));
	}
}
