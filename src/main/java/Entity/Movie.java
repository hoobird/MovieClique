package Entity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;

public class Movie {
    static ArrayList<Movie> movieList = new ArrayList<>();
    static int count = 0;

    int movieID;
    String name;
    String imgFile;
    String description;
    String genre;

    // For jackson
    public Movie() {
        super();
    }

    public Movie(String name, String posterImgSrc, String description, String genre) throws IOException {
        // Check for the latest added ID since static cannot be serialized or deserialized
        if (!movieList.isEmpty()) {
            for (Movie mov : movieList) {
                if (mov.getMovieID() > count) {
                    count = mov.getMovieID();
                }
            }
        }
        this.movieID = ++count;
        this.name = name;
        this.imgFile = posterImgSrc;
        this.description = description;
        this.genre = genre;
        movieList.add(0, this);
        // Try saving movieList every time it is added to
        writeMovieFile();
    }

    public static void initialise() throws IOException {
        new Movie("Forever Alone", "Poster1.png", "Forever Alone Description", "Comedy");
        new Movie("Vroom Vroom", "Poster2.png", "Vroom Vroom Description", "Action");
        new Movie("Scary Boy", "Poster3.png", "Scary Boy Description", "Horror");
        new Movie("Samurai Warrior - Final Battle", "Poster4.png", "Samurai  Warrior Description", "Historical");
        new Movie("Till death do us part", "Poster5.png", "Till death do us part Description", "Horror");
        new Movie("Always be alone", "Poster6.png", "Always be alone Description", "Comedy");
        new Movie("The Light in the Night Sky", "Poster7.png", "The Light in the Night Sky Description", "Drama");
        new Movie("World Of Mine", "Poster8.png", "World Of Mine Description", "Drama");
        new Movie("Bentley", "Poster9.png", "Bentley Description", "Horror");
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Movie.count = count;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public static ArrayList<Movie> getMovieList() {
        System.out.println("Movie Count: " + count);
        return movieList;
    }

    public static void setMovieList(ArrayList<Movie> movieList) {
        Movie.movieList = movieList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgFile() {
        return imgFile;
    }

    public void setImgFile(String posterImgSrc) {
        this.imgFile = posterImgSrc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public static Movie getMovie(String movieName) {
        for (Movie movie : movieList) {
            if (movie.getName().equals(movieName)) {
                return movie;
            }
        }
        return null;
    }

    public static Movie getMovie(int movieID) {
        for (Movie movie : movieList) {
            if (movie.getMovieID() == movieID) {
                return movie;
            }
        }
        return null;
    }

    public static void readMovieFile() throws StreamReadException, DatabindException, IOException {
        File file = new File("movieList.json");
        System.out.println("movieFile found!");
        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
                .allowIfSubType("java.util.ArrayList")
                .allowIfBaseType("Entity.Movie")
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);
        Movie.setMovieList(objectMapper.readValue(file, new TypeReference<ArrayList<Movie>>() {
        }));
    }

    public static void writeMovieFile() throws IOException {
        File file = new File("movieList.json");
        if (!file.exists()) {
            file.createNewFile();
        }
        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
                .allowIfSubType("java.util.ArrayList")
                /*.allowIfBaseType("Entity.Movie")*/
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, movieList);
        //System.out.println(file.getAbsolutePath());
    }
}
