import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MovieDataReader implements MovieDataReaderInterface{
  BufferedReader reader;
  List<MovieInterface> movies = new LinkedList<>();
  @Override
  public List<MovieInterface> readDataSet(Reader inputFileReader) throws FileNotFoundException, IOException {
    this.reader = new BufferedReader(inputFileReader);
    try{
      this.reader.readLine();
      String[] result;
      char c;
      while((c = (char) reader.read()) != -1){
        String line = c + reader.readLine();
        if(line == null) break;
        result = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        if(result.length!=13) break;
        if(result[3].toCharArray()[0]=='\"') result[3] = result[3].substring(1, result[3].toCharArray().length-1);
        movies.add(new Movie(result[0], Integer.parseInt(result[2]), Arrays.asList(result[3].split(", ")), result[7], result[11], Float.parseFloat(result[12])));
      }
    } catch (IOException e) {

    } finally {
      try{
        if(this.reader!=null) this.reader.close();
      }catch (IOException e){
        e.printStackTrace();
      }
    }
    return movies;
  }
}
