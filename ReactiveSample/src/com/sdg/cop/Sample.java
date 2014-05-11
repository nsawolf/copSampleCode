package com.sdg.cop;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

import java.util.Arrays;
import java.util.List;

public class Sample {
  public static void main(String[] args) throws InterruptedException {
    MovieServer server = new MovieServer();

    List<String> movieNames = Arrays.asList("Beautiful Mind", "A ew good men", "Forrest Gump");

    Observable<String> directors = server.getDirectors(movieNames);

    directors.subscribe(System.out::println, throwable -> System.out.print("oops: " + throwable.getMessage()));

    //This is the fluff that goes away
//    Action1<? super String> printDirector = new Action1<String>() {
//      @Override
//      public void call(String movieInfo) {
//        System.out.println(movieInfo + " --- " + Thread.currentThread());
//      }
//    };

//    Action1<Throwable> printError = new Action1<Throwable>() {
//      @Override
//      public void call(Throwable throwable) {
//        System.out.println("oops: " + throwable.getMessage());
//      }
//    };
  }
}