import java.util.List;

public class Movie implements MovieInterface{

  private String title;
  private Integer year;
  private List<String> genres;
  private String director;
  private String description;
  private Float avgVote;

  public Movie(String title, Integer year, List<String> genres, String director, String description, Float avgVote){
    this.title = title;
    this.year = year;
    this.genres = genres;
    this.director = director;
    this.description = description;
    this.avgVote = avgVote;
  }
  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public Integer getYear() {
    return year;
  }

  @Override
  public List<String> getGenres() {
    return genres;
  }

  @Override
  public String getDirector() {
    return director;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public Float getAvgVote() {
    return avgVote;
  }

  @Override
  public int compareTo(MovieInterface otherMovie) {
    if(this.getAvgVote()>otherMovie.getAvgVote()) return -1;
    else if (this.getAvgVote() == otherMovie.getAvgVote()) return 0;
    return 1;
  }
}
