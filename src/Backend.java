// --== CS400 File Header Information ==--
// Name: Jacky Bai
// Email: bai59@wisc.edu
// Team: red
// Group: AC
// TA: Mu
// Lecturer: Florian Heimerl
// Notes to Grader: None
import java.io.BufferedReader;
import java.util.*;

/**
 * This is the class that contains the function of backend.
 *
 * @author Jacky Bai
 */
public class Backend implements BackendInterface{

  private BufferedReader reader; // reader
  private List<String> genres; // genres selected
  private List<String> ratings; // rating selected
  private List<MovieInterface> movies; // all the movies
  private HashTableMap<String, MovieInterface> data01; // hash according to genres
  private HashTableMap<Integer, MovieInterface> data02; // hash according to rating

  /**
   * Constructor of Backend
   * @param reader reader
   */
  public Backend(BufferedReader reader) {
    genres = new LinkedList<>();
    ratings = new LinkedList<>();
    movies = new LinkedList<>();
    data01 = new HashTableMap<>();
    data02 = new HashTableMap<>(); // rating from 0-10
    this.reader = reader;
    try{
      movies = new MovieDataReader().readDataSet(this.reader);
    } catch (Exception e){
      e.printStackTrace();
    }
    // hash
    for(MovieInterface i : movies) {
      for(String str : i.getGenres()){
        data01.put(str, i);
      }
    }
    // hash
    for(MovieInterface i : movies) {
      data02.put((int) Math.floor(i.getAvgVote()),i);
    }
  }

  /**
   * add a genre
   * @param genre genre to add
   */
  @Override
  public void addGenre(String genre) {
    genres.add(genre);

  }

  /**
   * add average rating
   * @param rating rating to add
   */
  @Override
  public void addAvgRating(String rating) {
    ratings.add(rating);
  }

  /**
   * remove genre
   * @param genre genre to remove
   */
  @Override
  public void removeGenre(String genre) {
    genres.remove(genre);
  }

  /**
   * remove rating
   * @param rating rating to remove.
   */
  @Override
  public void removeAvgRating(String rating) {
    ratings.remove(rating);
  }

  /**
   * get genres selected
   * @return genres selected
   */
  @Override
  public List<String> getGenres() {
    return genres;
  }

  /**
   * get average rating selected
   * @return  genres selected
   */
  @Override
  public List<String> getAvgRatings() {
    return ratings;
  }

  /**
   * get number of movies
   * @return number of movies
   */
  @Override
  public int getNumberOfMovies()  {
    return movies.size();
  }

  /**
   * get all genres
   * @return all genres
   */
  @Override
  public List<String> getAllGenres() {
    List<String> genres = new ArrayList<String>();
    List<String> results = new ArrayList<>();
    for(MovieInterface i : movies){
      genres.addAll(i.getGenres());
    }
    for(String i:genres){
      if(!results.contains(i)) results.add(i);
    }
    return results;
  }

  /**
   * get top 3 movies
   * @param startingIndex index to start from
   * @return list of 3 movies
   */
  @Override
  public List<MovieInterface> getThreeMovies(int startingIndex) {
    try{
    List<MovieInterface> result = new LinkedList<>();
    movies.sort(new Comparator<MovieInterface>() {
      @Override
      public int compare(MovieInterface o1, MovieInterface o2) {
        if(o1.getAvgVote()>o2.getAvgVote()) return -1;
        else if (o1.getAvgVote() == o2.getAvgVote()) return 0;
        return 1;
      }
    });
    for(int i = 0; i < 3; i++) {
      if(movies.get(startingIndex+i) != null) result.add(movies.get(startingIndex+i));
    }
    return result;
    }catch (Exception e){
      return new LinkedList<>();
    }
  }

  /**
   * get all movies that fit selected properties.
   * @param mode
   * @return all movies that fit selected properties.
   */
  public List<MovieInterface> getMovies(String mode){
    List<MovieInterface> result = new LinkedList<>();
    if (mode.equals("g")){
      for(String i : genres){
        result.addAll(data01.get(i));
      }
    }
    else if (mode.equals("r")){
      for(String i : ratings){
        result.addAll(data02.get(Integer.parseInt(i)));
      }
    }
    return result;
  }

}
