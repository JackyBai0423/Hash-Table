import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Frontend {
  public static void run(Backend backend) {
//    Backend backend = new Backend(new MovieDataReaderDummy());
    File dataset = new File("movies.csv");
    for(int i = 0; i <= 10; i++){
      backend.addAvgRating(String.valueOf(i));
    }
    Scanner scan = new Scanner(System.in);
    String mode = "b";

    while (true){
      System.out.println("Please select your preferred mode:\nNow you are at Base mode\ng: Genre mode\nr: Rating mode");
      System.out.println("3 top movies:");
      for (MovieInterface i : backend.getThreeMovies(0)){
        System.out.println(i.getTitle());
      }
      System.out.println("Enter g to enter genre mode, enter r to enter rating mode, enter x to exit");
      String input = scan.next();
      if(!input.equals("g") && !input.equals("r") && !input.equals("x")) System.out.println("Invalid input");
      else {
        mode = input;
        if(mode.equals("x")) {
          mode = "b";
        }
      }

      switch (mode) {
        case "g":{
          LinkedList<String> result = new LinkedList<>();
          while (true) {
            System.out.println("Please select the genre you want:(Enter the number before the genre, separate them by \" \")");
            int index = 1;
            for(String i : backend.getAllGenres()) {
              System.out.print(index+": ");
              System.out.println(i);
              index++;
            }
            String in = scan.next();
            if(in.equals("x")) {
              mode = "b";
              break;
            }
            LinkedList<String> temp = new LinkedList<>();
            temp.addAll(Arrays.asList(in.split(" ")));
            int genreSize = backend.getAllGenres().size();
            for(String i : temp) {
              try{
                if(Integer.parseInt(i)<1 || Integer.parseInt(i)>genreSize) temp.remove(i);
              }catch (NumberFormatException e) {
                temp.remove(i);
              }
            }
            for(String i : temp) {
              if(result.contains(i)) {
                result.remove(i);
                temp.remove(i);
              }
            }
            result.addAll(temp);
            for(String i : result) {
              backend.addGenre(i);
            }
//          for (MovieInterface i : backend.getMovies()){
//            System.out.println(i.getTitle());
//          }
          }
          break;
        }
        case "r":{
          LinkedList<String> result = new LinkedList<>();
          result.addAll(Arrays.asList(new String[]{"1","2","3","4","5","6","7","8","9","10"}));
          while (true) {
            System.out.println("Please select the rating you want:(Enter the number of the rating, separate them by \" \")");
            for(int i = 0; i <=10 ;i++) {
              System.out.println(i);

            }
            String in = scan.next();
            if(in.equals("x")) {
              mode = "b";
              break;
            }
            LinkedList<String> temp = new LinkedList<>();
            temp.addAll(Arrays.asList(in.split(" ")));
            for(String i : temp) {
              try{
                if(Integer.parseInt(i)<1 || Integer.parseInt(i)>10) temp.remove(i);
              }catch (NumberFormatException e) {
                temp.remove(i);
              }
            }
            for(String i : temp) {
              if(result.contains(i)) {
                result.remove(i);
                temp.remove(i);
              }
            }
            result.addAll(temp);
            for(String i : result) {
              backend.addAvgRating(i);
            }
//          for (MovieInterface i : backend.getMovies()){
//            System.out.println(i.getTitle());
//          }
          }
        }
      }
    }



  }
}
