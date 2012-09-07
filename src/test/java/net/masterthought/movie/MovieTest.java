package net.masterthought.movie;

import junit.framework.Assert;
import net.masterthought.movie.obj.Movie;
import org.junit.Test;

public class MovieTest {

    @Test
    public void should_set_all_elements_of_movie(){
        Movie movie = new Movie();
        movie.setName("Blade Runner");
        Assert.assertEquals(movie.getDirector().getName(),"Ridley Scott");
    }

    @Test
    public void should_receive_null_for_non_existing_movie(){
        Movie movie = new Movie();
        movie.setName("Not a movie");
        Assert.assertNull(movie.getDirector());
    }
}
