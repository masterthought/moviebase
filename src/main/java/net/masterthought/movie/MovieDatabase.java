package net.masterthought.movie;

import com.freebase.json.JSON;
import net.masterthought.freebase.FreebaseUtil;
import net.masterthought.movie.obj.Director;
import net.masterthought.movie.obj.Movie;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static com.freebase.json.JSON.o;

public class MovieDatabase {

    private static final String filmType = "/film/film";
    private static final String directorType = "/film/director";
    public static final String imdbBaseUrl = "http://www.imdb.com/title/";
    public static final String imageBaseUrl = "http://img.freebase.com/api/trans/image_thumb";

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieDatabase.class);

    public static Director getDirectorOf(String movie) throws IOException, ParseException {
        try {
            Director director = new Director();
            director.setName(FreebaseUtil.get(filmType, movie).get("directed_by").get(0).get("name").string().replace("\"", ""));
            director.setMovies(getFilmsForDirector(director.getName()));
            director.setPictureUrl(getDirectorInfo(director.getName()).get("result").get(0).get("image").get("id").string().replace("\\", ""));
            return director;
        } catch (NullPointerException npe) {
            LOGGER.info("Director not found for: " + movie);
            return null;
        }
    }

    public static JSON getDirectorInfo(String director) throws IOException, ParseException {
        return FreebaseUtil.search(director);
    }

    public static List<String> getFilmsForDirector(String director) {
        try {
            JSON query = o(
                    "id", null,
                    "type", directorType,
                    "name", director,
                    "*", null
            );

            StringTokenizer tokenizer = new StringTokenizer(
                    FreebaseUtil.get(query).get("film").toString().replace("\"", "").replace("[", "")
                            .replace("]", "")
                    , ",");
            List<String> movies = new ArrayList<String>();

            while (tokenizer.hasMoreTokens()) {
                movies.add(tokenizer.nextToken());
            }
            return movies;
        } catch (NullPointerException npe) {
            System.out.println("No info found");
            return new ArrayList<String>();

        }

    }


    public static JSON getMovieInfo(String movie) {
        JSON query = o(
                "id", null,
                "type", "/film/film",
                "name", movie,
                "*", null
        );
        return FreebaseUtil.get(query);
    }

    public static Movie movieInfo(String movieName, Movie movie) throws IOException, ParseException {
        JSON movieJson = getMovieInfo(movieName);
        return createMovieObj(movieJson, movie);
    }

    public static Movie createMovieObj(JSON movieJson, Movie movie) throws IOException, ParseException {
        if (movieJson == null) {
            LOGGER.info("No info found for movie: " + movie.getName());
            return null;
        }
        if (movie == null) movie = new Movie();
        movie.setGenre(movieJson.get("genre").array());
        movie.setMovieFestivals(movieJson.get("film_festivals").array());
        movie.setEditedBy(movieJson.get("edited_by").array());
        movie.setImdbId(movieJson.get("imdb_id").get(0).string().replace("\"", ""));
        movie.setProductionCompanies(movieJson.get("production_companies").array());
        String pictureUrl = MovieDatabase.imageBaseUrl + movieJson.get("mid").get(0).toString().replace("\\", "");
        pictureUrl.replaceAll("\"","").replace("\\","");
        String s = pictureUrl.replaceAll("\"","").replace("\\","");
        System.out.println(s);
        movie.setPictureUrl(s);
        movie.setDirector(getDirectorOf(movie.getName()));
        return movie;
    }

    public static void main(String[] args) throws IOException, ParseException {
        Movie movie = movieInfo("Lal ladfasdfa dafdsafdsa", null);
        System.out.println(movie.getPictureUrl());
    }
}
