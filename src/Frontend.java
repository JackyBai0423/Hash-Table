// --== CS400 File Header Information ==--
// Name: Boqi Zhao
// Email: bzhao78@wisc.edu
// Team: blue
// Role: frontend
// TA: Xi
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Frontend{
  public static void main(String[] args) {
    try{
      run(new Backend(new BufferedReader(new FileReader("src/movies.csv"))));
    } catch (IOException e){

    }
  }

  public static void run(Backend backend) {
    {
      int i = 0;
      while (i <= 10) {
        backend.addAvgRating(String.valueOf(i));
        i++;
      }
    }
    Scanner scan = new Scanner(System.in);
    String mode = "b";

    do {
      System.out.println("Please select your preferred mode" +
              ":\nNow you are at Base mode\ng: Genre mode\nr: Rating mode");
      System.out.println("today's three top movies:");
      for (MovieInterface i : backend.getThreeMovies(0)) {
        System.out.println(i.getTitle());
      }
      System.out.println("Enter g to enter the genre mode, enter r to enter the rating mode, enter x to exit");
      String input = "";
      if (!scan.hasNextLine()) {
      } else {
        input = scan.next();
      }
      if (input.equals("g") || input.equals("r") || input.equals("x")) {
        mode = input;
        if (mode.equals("x")) {
//          mode = "b";
          break;
        }
      } else {
        System.out.println("Please give a correct input");
      }

      switch (mode) {
        case "g": {
          LinkedList<String> result = new LinkedList<>();
          do {
            System.out.println("Please select the genre:" +
                    "(Enter the number before the genre, and then they will be separated by \" \")");
            int index = 1;
            for (String i : backend.getAllGenres()) {
              System.out.print(index + ": ");
              System.out.println(i);
              index++;
            }
            String put_in = scan.next();
            if (!put_in.equals("x")) {
              LinkedList<String> temp = new LinkedList<>();
              temp.addAll(Arrays.asList(put_in.split(" ")));
              int genreSize = backend.getAllGenres().size();
              for (int j = 0, tempSize = temp.size(); j < tempSize; j++) {
       
                String i = temp.get(j);
                try {
                  if (Integer.parseInt(i) < 1 || Integer.parseInt(i) > genreSize) temp.remove(i);
                } catch (NumberFormatException e) {
                  temp.remove(i);
                }
              }
              for (int j = 0, tempSize = temp.size(); j < tempSize; j++) {
                String i = temp.get(j);
                if (!result.contains(i)) {
                  continue;
                }
                result.remove(i);
                temp.remove(i);
                backend.removeGenre(backend.getAllGenres().get(Integer.parseInt(i)));
              }
              result.addAll(temp);
              for (int j = 0; j < result.size(); j++) {
                String i = result.get(j);
                backend.addGenre(i);
              }

            } else {
              mode = "b";
              break;
            }

          } while (true);
          break;
        }


        case "r": {
          LinkedList<String> result = new LinkedList<>();
          result.addAll(Arrays.asList(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
          while (true) {
            System.out.println("Please select the rating:" +
                    "(Enter the number of the rating, and then they will be separated by \" \")");
            for (int i = 0; i <= 10; i++) {
              System.out.println(i);

            }
            String put_in = scan.next();
            if (!put_in.equals("x")) {
              LinkedList<String> temp = new LinkedList<>();
              temp.addAll(Arrays.asList(put_in.split(" ")));
              for (int j = 0, tempSize = temp.size(); j < tempSize; j++) {
                String i = temp.get(j);
                try {
                  if (Integer.parseInt(i) < 1 || Integer.parseInt(i) > 10) temp.remove(i);
                } catch (NumberFormatException e) {
                  temp.remove(i);
                }
              }
              for (int j = 0, tempSize = temp.size(); j < tempSize; j++) {
                String i = temp.get(j);
                if (!result.contains(i)) {
                  continue;
                }
                result.remove(i);
                temp.remove(i);
                backend.removeAvgRating(i);
              }
              result.addAll(temp);
              for (int j = 0, resultSize = result.size(); j < resultSize; j++) {
                String i = result.get(j);
                backend.addAvgRating(i);
              }
            } else {
              mode = "b";
              break;
            }

          }
        }
      }
    } while (true);



  }
}
