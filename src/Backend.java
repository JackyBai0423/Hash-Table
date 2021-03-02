import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Backend implements BackendInterface{

  private int size;
  private int capacity;
  private StringReader reader;
  private List<String> genres;
  private List<String> ratings;
  private List<MovieInterface> movies;
  private List<MovieInterface>[] data01; // hash according to genres
  private List<MovieInterface>[] data02 = new LinkedList[11]; // hash according to rating

//  public Backend(MovieDataReaderDummy reader){
//    capacity = 10;
//    size = 0;
//    genres = new LinkedList<>();
//    ratings = new LinkedList<>();
//    movies = new LinkedList<>();
//    data01 = new LinkedList[capacity];
//    readerDummy = reader;
//    List<MovieInterface> data;
//    try{
//      data = readerDummy.readDataSet(new FileReader("movies.csv"));
//    } catch (Exception e) {
//      data = new LinkedList<>();
//    }
//    for(MovieInterface i : data) {
//      if (data02[(int)Math.floor(i.getAvgVote())] == null) data02[(int)Math.floor(i.getAvgVote())] = new LinkedList<>();
//      data02[(int)Math.floor(i.getAvgVote())].add(i);
//    }
//    for(MovieInterface i : data) {
//      for(String str : i.getGenres()){
//        if(data01[str.hashCode()%capacity]==null) data01[str.hashCode()%capacity] = new LinkedList<>();
//        data01[str.hashCode()%capacity].add(i);
//        size++;
//        if ((double)size/(double)capacity >= 0.85) doubleSize(data02);
//      }
//    }
//  }

  public Backend(StringReader reader) {
    capacity = 10;
    size = 0;
    genres = new LinkedList<>();
    ratings = new LinkedList<>();
    movies = new LinkedList<>();
    data01 = new LinkedList[capacity];
    this.reader = reader;
  }

  @Override
  public void addGenre(String genre) {
    genres.add(genre);
    if(data01[genre.hashCode()%capacity]!=null)
    movies.addAll(data01[genre.hashCode()%capacity]);
  }

  @Override
  public void addAvgRating(String rating) {
    ratings.add(rating);
    if(data02[Integer.parseInt(rating)]!=null)
    movies.addAll(data02[Integer.parseInt(rating)]);
  }

  @Override
  public void removeGenre(String genre) {
    genres.remove(genre);
  }

  @Override
  public void removeAvgRating(String rating) {
    ratings.remove(rating);
  }

  @Override
  public List<String> getGenres() {
    return genres;
  }

  @Override
  public List<String> getAvgRatings() {
    return ratings;
  }

  @Override
  public int getNumberOfMovies()  {
    try {
      return readerDummy.readDataSet(new FileReader("movies.csv")).size();
    } catch (FileNotFoundException e) {
      return 0;
    } catch (IOException e1) {
      return 0;
    }
  }

  @Override
  public List<String> getAllGenres() {
    try{
      List<MovieInterface> dataSet = readerDummy.readDataSet(new FileReader("movies.csv"));
      List<String> genres = new LinkedList<>();
      List<String> result = new LinkedList<>();
      for (MovieInterface i : dataSet) {
        genres.addAll(i.getGenres());
      }
      for (String i : genres) {
        if(!result.contains(i)) result.add(i);
      }
      return  result;
    }catch (Exception e){
      System.out.println("Exception");
      return new LinkedList<>();
    }
  }

  @Override
  public List<MovieInterface> getThreeMovies(int startingIndex) {
    try{
    List<MovieInterface> dataSet = readerDummy.readDataSet(new FileReader("movies.csv"));
    List<MovieInterface> result = new LinkedList<>();
    dataSet.sort(new Comparator<MovieInterface>() {
      @Override
      public int compare(MovieInterface o1, MovieInterface o2) {
        if(o1.getAvgVote()>o2.getAvgVote()) return 1;
        else if (o1.getAvgVote() == o2.getAvgVote()) return 0;
        return -1;
      }
    });
    for(int i = 0; i < 3; i++) {
      if(dataSet.get(startingIndex+i) != null) result.add(dataSet.get(startingIndex+i));
    }
    return result;
    }catch (Exception e){
      return null;
    }
  }

  private void doubleSize(List<MovieInterface>[] data02){
    capacity = capacity * 2;
    LinkedList<MovieInterface>[] temp = new LinkedList[capacity];
    // rehash
    for(int i = 0; i < data02.length; i++) {
      if(data02[i] != null)
        for(MovieInterface j : data02[i]) {
          for(String str : j.getGenres()) {
            if (data01[str.hashCode() % capacity] == null) data01[str.hashCode() % capacity] = new LinkedList<>();
            data01[str.hashCode() % capacity].add(j);
          }
        }
    }
    // rehash completed
    this.data02 = temp;
  }

  public List<MovieInterface> getMovies(){
    return movies;
  }

}
