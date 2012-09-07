package net.masterthought.movie.obj;

import net.masterthought.movie.MovieDatabase;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class Director implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = MovieDatabase.imageBaseUrl + pictureUrl + "?maxheight=510&mode=fit&maxwidth=510";
    }

    private String pictureUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getMovies() {
        return movies;
    }

    public void setMovies(List<String> movies) {
        this.movies = movies;
    }

    private List<String> movies = new ArrayList<String>();

    public String toString(){
        return this.name;
    }
}
