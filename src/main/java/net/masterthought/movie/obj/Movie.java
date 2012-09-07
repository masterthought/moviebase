package net.masterthought.movie.obj;

import net.masterthought.movie.MovieDatabase;
import org.json.simple.parser.ParseException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name = null;
    private List<String> genre = new ArrayList<String>();
    private List<String> movieFestivals = new ArrayList<String>();
    private List<String> editedBy = new ArrayList<String>();
    private List<String> soundtrack = new ArrayList<String>();
    private List<String> productionCompanies = new ArrayList<String>();
    private String imdbId = null;

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    private String pictureUrl = null;

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    private Director director;

    public List<String> getGenre() {
        return genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        try {
            reset();
            MovieDatabase.movieInfo(name,this);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void reset() {
        this.imdbId = null;
        this.director = null;
        this.productionCompanies = new ArrayList<String>();
        this.soundtrack = null;
        this.movieFestivals = new ArrayList<String>();
        this.genre = new ArrayList<String>();
        this.editedBy = new ArrayList<String>();
    }


    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    public List<String> getMovieFestivals() {
        return movieFestivals;
    }

    public void setMovieFestivals(List<String> movieFestivals) {
        this.movieFestivals = movieFestivals;
    }

    public List<String> getEditedBy() {
        return editedBy;
    }

    public void setEditedBy(List<String> editedBy) {
        this.editedBy = editedBy;
    }

    public List<String> getSoundtrack() {
        return soundtrack;
    }

    public void setSoundtrack(List<String> soundtrack) {
        this.soundtrack = soundtrack;
    }

    public List<String> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<String> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = MovieDatabase.imdbBaseUrl + imdbId;
    }

}
