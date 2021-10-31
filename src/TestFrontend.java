// --== CS400 File Header Information ==--
// Name: Boqi Zhao
// Email: bzhao78@wisc.edu
// Team: blue
// Role: frontend
// TA: Xi
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
import java.io.*;

/**
 * This class contains a set of tests for the front end of the Movie Mapper project.
 */
public class TestFrontend {

  public static void main(String[] args) {
    (new TestFrontend()).runTests();
  }

  /**
   * This method calls all of the test methods in the class and outputs pass / fail
   * for each test.
   */
  public void runTests() {
    System.out.print("Test enter 'x' to exit (WARNING: if 'x' does not exit app, test won't exit and run indefinitely!): ");
    if (this.enterXToExit()) {
      System.out.println("PASSED");
    } else {
      System.out.println("FAILED");
    }
    System.out.print("Test frontend initially lists no movie (WARNING: if 'x' does not exit app, test won't exit and run indefinitely!): ");
    if (this.testFrontendInitialOutputNoMovie()) {
      System.out.println("PASSED");
    } else {
      System.out.println("FAILED");
    }
    System.out.print("Test 'g' load genre selection screen (WARNING: if pressing 'x' twice from the genre selection " +
            "screen does not exit app, test won't exit and run indefinitely!): ");
    if (this.testFrontendGForGenres()) {
      System.out.println("PASSED");
    } else {
      System.out.println("FAILED");
    }
    System.out.println("Test 'r' load rating selection screen (WARNING: if press 'x' twice from the rating selection screen does not exit, " +
            "test won't exit and run indefinitely" );
    if (this.testFrontendGForRating()) {
      System.out.println("PASSED");
    } else{
      System.out.println("FAILED");
    }
    System.out.println("Test whether the chosen rating can be removed");
    if (this.testFrontendGForRemoveRating()){
      System.out.println("PASSED");
    } else{
      System.out.println("FAILED");
    }
  }

  /**
   * This test runs the front end and redirects its output to a string. It then
   * passes in 'x' as a command. When the front end exists, the tests succeeds.
   * If 'x' does not exist the app, the test will not terminate (it won't fail
   * explicitely in this case). The test fails explicitely if the front end is
   * not instantiated or if an exception occurs.
   *
   * @return true if the test passed, false if it failed
   */
  public boolean enterXToExit() {
    PrintStream standardOut = System.out;
    InputStream standardIn = System.in;
    try {
      // set the input stream to our input (with an x to test of the program exists)
      String input = "x";
      InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
      System.setIn(inputStreamSimulator);
      ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
      // set the output to the stream captor to read the output of the front end
      System.setOut(new PrintStream(outputStreamCaptor));
      // instantiate when front end is implemented
      Object frontend = new Frontend();
      ((Frontend)frontend).run(new Backend(new BufferedReader(new FileReader("src/movies.csv"))));
      // set the output back to standard out for running the test
      System.setOut(standardOut);
      // same for standard in
      System.setIn(standardIn);
      if (frontend == null) {
        // test fails
        return false;
      } else {
        // test passed
        return true;
      }
    } catch (Exception e) {
      // make sure stdin and stdout are set correctly after we get exception in test
      System.setOut(standardOut);
      System.setIn(standardIn);
      e.printStackTrace();
      // test failed
      return false;
    }
  }

  /**
   * This test runs the front end and redirects its output to a string. It then
   * passes in 'x' as a command to exit the app. The test succeeds if the front
   * end does not contain any of the three movies (the movie titles are not in
   * the string captured from the front end) by default. It fails if any of
   * those three titles are present in the string or an exception occurs.
   *
   * @return true if the test passed, false if it failed
   */
  public boolean testFrontendInitialOutputNoMovie() {
    PrintStream standardOut = System.out;
    InputStream standardIn = System.in;
    try {
      // set the input stream to our input (with an x to test of the program exists)
      String input = "x";
      InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
      System.setIn(inputStreamSimulator);
      ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
      // set the output to the stream captor to read the output of the front end
      System.setOut(new PrintStream(outputStreamCaptor));
      // instantiate when front end is implemented
      Object frontend = new Frontend();
      ((Frontend)frontend).run(new Backend(new BufferedReader(new FileReader("src/movies.csv"))));
      // set the output back to standard out for running the test
      System.setOut(standardOut);
      // same for standard in
      System.setIn(standardIn);
      String appOutput = outputStreamCaptor.toString();
      if (frontend == null || appOutput.contains("The Source of Shadows")
              || appOutput.contains("The Insurrection")
              || appOutput.contains("Valley Girl")) {
        // test failed
        return false;
      } else {
        // test passed
        return true;
      }
    } catch (Exception e) {
      // make sure stdin and stdout are set correctly after we get exception in test
      System.setOut(standardOut);
      System.setIn(standardIn);
      e.printStackTrace();
      // test failed
      return false;
    }
  }

  /**
   * This test runs the front end and redirects its output to a string. It then
   * passes in 'g' as a command to go to the genre selection mode. It then exists
   * the app by pressing 'x' to go back to the main mode and another 'x' to exit.
   * The test succeeds if the genre selection screen contains all five genres
   * from the data. It fails if any of them are missing, the front end has not
   * been instantiated (is null), or there is an exception.
   *
   * @return true if the test passed, false if it failed
   */
  public boolean testFrontendGForGenres() {
    PrintStream standardOut = System.out;
    InputStream standardIn = System.in;
    try {
      // set the input stream to our input (with an g to test of the program lists genres)
      String input = "g" + System.lineSeparator() + "x" + System.lineSeparator() + "x";
      InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
      System.setIn(inputStreamSimulator);
      ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
      // set the output to the stream captor to read the output of the front end
      System.setOut(new PrintStream(outputStreamCaptor));
      // instantiate when front end is implemented
      Object frontend = new Frontend();
      ((Frontend)frontend).run(new Backend(new BufferedReader(new FileReader("src/movies.csv"))));
      // set the output back to standard out for running the test
      System.setOut(standardOut);
      // same for standard in
      System.setIn(standardIn);
      // add all tests to this method
      String appOutput = outputStreamCaptor.toString();
      if (frontend != null && appOutput.contains("Horror")
              && appOutput.contains("Action")
              && appOutput.contains("Comedy")
              && appOutput.contains("Musical")
              && appOutput.contains("Romance")) {
        // test passes if all genres from the data are displayed on the screen
        return true;
      } else {
        // test failed
        return false;
      }
    } catch (Exception e) {
      // make sure stdin and stdout are set correctly after we get exception in test
      System.setOut(standardOut);
      System.setIn(standardIn);
      e.printStackTrace();
      // test failed
      return false;
    }
  }


  public boolean testFrontendGForRating() {
    PrintStream standardOut = System.out;
    InputStream standardIn = System.in;
    try {
      // set the input stream to our input (with an g to test of the program lists genres)
      String input = "r" + System.lineSeparator() + "x" + System.lineSeparator() + "x";
      InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
      System.setIn(inputStreamSimulator);
      ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
      // set the output to the stream captor to read the output of the front end
      System.setOut(new PrintStream(outputStreamCaptor));
      // instantiate when front end is implemented
      Object frontend = new Frontend();
      ((Frontend) frontend).run(new Backend(new BufferedReader(new FileReader("src/movies.csv"))));
      // set the output back to standard out for running the test
      System.setOut(standardOut);
      // same for standard in
      System.setIn(standardIn);
      // add all tests to this method
      String appOutput = outputStreamCaptor.toString();
      if (frontend != null && appOutput.contains("3")
              && appOutput.contains("4")
              && appOutput.contains("5")
              && appOutput.contains("6")
              && appOutput.contains("7")
              && appOutput.contains("8")
              && appOutput.contains("9")
              && appOutput.contains("10")
              && appOutput.contains("1")
              && appOutput.contains("2")) {
        // test passes if all genres from the data are displayed on the screen
        return true;
      } else {
        // test failed
        return false;
      }
    } catch (Exception e) {
      // make sure stdin and stdout are set correctly after we get exception in test
      System.setOut(standardOut);
      System.setIn(standardIn);
      e.printStackTrace();
      // test failed
      return false;
    }
  }

  public boolean testFrontendGForRemoveRating() {
    PrintStream standardOut = System.out;
    InputStream standardIn = System.in;
    try {
      // set the input stream to our input (with an g to test of the program lists genres)
      String input = "r" + System.lineSeparator() + "1" + System.lineSeparator() + "2" + System.lineSeparator() +
              "x" + System.lineSeparator()+ "x";
      InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
      System.setIn(inputStreamSimulator);
      ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
      // set the output to the stream captor to read the output of the front end
      System.setOut(new PrintStream(outputStreamCaptor));
      // instantiate when front end is implemented
      Object frontend = new Frontend();
      ((Frontend) frontend).run(new Backend(new BufferedReader(new FileReader("src/movies.csv"))));
      // set the output back to standard out for running the test
      System.setOut(standardOut);
      // same for standard in
      System.setIn(standardIn);
      // add all tests to this method
      String appOutput = outputStreamCaptor.toString();
      System.out.println(appOutput);
      if (frontend != null && appOutput.contains("My Salinger Year")
              && appOutput.contains("The Quarry")
              && appOutput.contains("Inside the Rain")
              && !appOutput.contains("Agent Jade Black")) {
        // test passes if all genres from the data are displayed on the screen
        return true;
      } else {
        // test failed
        return false;
      }
    } catch (Exception e) {
      // make sure stdin and stdout are set correctly after we get exception in test
      System.setOut(standardOut);
      System.setIn(standardIn);
      e.printStackTrace();
      // test failed
      return false;
    }
  }

}