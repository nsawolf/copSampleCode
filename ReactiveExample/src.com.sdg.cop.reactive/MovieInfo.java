import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;

public class MovieInfo {
  public static Map<String, String> getMovieInfo(final String movieName) {
    try {
      String queryURL = "http://www.omdbapi.com/?t=" + movieName.replaceAll(" ", "%20");
      final URL url = new URL(queryURL);
      final BufferedReader reader =
          new BufferedReader(new InputStreamReader(url.openStream()));

      JsonParser jsonParser = JsonFactory.createJsonParser();
      String content = "";
      String line = "";
      while ((line = reader.readLine()) != null) {
        content += line;
      }
      return jsonParser..parseJson(content);
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}