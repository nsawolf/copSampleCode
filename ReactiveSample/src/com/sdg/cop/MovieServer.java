package com.sdg.cop;

import rx.Observable;
import rx.Subscriber;

import java.util.List;
import java.util.function.Consumer;

public class MovieServer {
	public Observable<String> getDirectors(final List<String> movieNames) {

		Observable.OnSubscribe<String> subscribe = subscriber -> pushMovieInfo(
				subscriber, movieNames);

		return Observable.create(subscribe);
	}

	private void pushMovieInfo(Subscriber<? super String> subscriber,
			List<String> movieNames) {

		Consumer<String> handleMovie = movieName -> {
			String director = MovieInfo.getMovieInfo(movieName).get("Director");
			if (director != null)
				subscriber.onNext(movieName + "---" + director);
			else
				subscriber.onError(new Throwable(movieName
						+ "? learn typing dude :)"));
			String directorInfo = (director == null) ? "? learn typing dude"
					: director;
		};

		movieNames.stream().forEach(handleMovie::accept);
		subscriber.onCompleted();
	}
}
