// --== CS400 File Header Information ==--
// Name: Jacky Bai
// Email: bai59@wisc.edu
// Team: red
// Group: AC
// TA: Mu
// Lecturer: Florian Heimerl
// Notes to Grader: None
import java.io.*;

/**
 * This class contains a set of tests for the back end of the Movie Mapper project.
 */
public class TestBackend {
	
	public static void main(String[] args) {
		(new TestBackend()).runTests();
	}


	public void runTests() {
		// add all tests to this method
		if (this.testInitialNumberOfMovies()) {
			System.out.println("Test initial number of movies: PASSED");
		} else {
			System.out.println("Test initial number of movies: FAILED");
		}
		if (this.testGetAllGenres()) {
			System.out.println("Test get all genres: PASSED");
		} else {
			System.out.println("Test get all genres: FAILED");
		}
		if (this.testGetThreeMoviesInitial()) {
			System.out.println("Test get three movies sorted by rating: PASSED");
		} else {
			System.out.println("Test get three movies sorted by rating: FAILED");
		}
		if(this.testRemoveAverageRating()) {
			System.out.println("Test remove rating: PASSED");
		} else {
			System.out.println("Test remove rating: FAILED");
		}
		if(this.testRemoveAddGenre()) {
			System.out.println("Test remove and add genre: PASSED");
		} else {
			System.out.println("Test remove and add genre: FAILED");
		}
	}
	
	/**
	 * This test instantiates the back end with three movies and tests whether the
	 * initial selection is empty (getNumberOfMovies() returns 0). It passes when
	 * 0 is returned and fails in all other cases, including when an exception is
	 * thrown.
	 * @return true if the test passed, false if it failed
	 */
	public boolean testInitialNumberOfMovies() {
		try {
			// instantiate once BackendInterface is implemented
			BackendInterface backendToTest = new Backend(new BufferedReader(new FileReader("src/movies.csv")));
			if (backendToTest.getNumberOfMovies() == 231) {
				// test passed
				return true;
			} else {
				// test failed
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// test failed
			return false;
		}

	}
	
	/**
	 * This test instantiates the back end with three movies and tests whether
	 * the getAllGenres method return the correct set of genres for those three
	 * movies.
	 * @return true if the test passed, false if it failed
	 */
	public boolean testGetAllGenres() {
		try {
			// instantiate once BackendInterface is implemented
			BackendInterface backendToTest = new Backend(new BufferedReader(new FileReader("src/movies.csv")));
			if (backendToTest.getAllGenres().contains("Horror")
					&& backendToTest.getAllGenres().contains("Action")
					&& backendToTest.getAllGenres().contains("Comedy")
					&& backendToTest.getAllGenres().contains("Musical")
					&& backendToTest.getAllGenres().contains("Romance")) {
				// test passed
				return true;
			} else {
				// test failed
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// test failed
			return false;
		}
	}
	
	/**
	 * This test instantiates the back end with three movies and tests whether the
	 * initial list returned by getThreeMovies starting with the first movie (0)
	 * is empty. It passes when 0 is returned and fails in all other cases, including
	 * when an exception is thrown.
	 * @return true if the test passed, false if its failed
	 */
	public boolean testGetThreeMoviesInitial() {
		try {
			// instantiate once BackendInterface is implemented
			BackendInterface backendToTest = new Backend(new BufferedReader(new FileReader("src/movies.csv")));
			if (backendToTest.getThreeMovies(0).size() == 3) {
				// test passed
				return true;
			} else {
				// test failed
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// test failed
			return false;
		}
	}

	/**
	 * test the functionality of remove average rating.
	 * @return true or false
	 */
	public boolean testRemoveAverageRating(){
		try {
			// instantiate once BackendInterface is implemented
			BackendInterface backendToTest = new Backend(new BufferedReader(new FileReader("src/movies.csv")));
			backendToTest.removeAvgRating("3");
			if(!backendToTest.getAvgRatings().contains("3")) return true;
			else return false;
		} catch (Exception e) {
			e.printStackTrace();
			// test failed
			return false;
		}
	}

	/**
	 * test the functionality of remove and add genre
	 * @return true or false.
	 */
	public boolean testRemoveAddGenre(){
		try {
			// instantiate once BackendInterface is implemented\
			BackendInterface backendToTest = new Backend(new BufferedReader(new FileReader("src/movies.csv")));
			backendToTest.addGenre("Horror");
			if(backendToTest.getAllGenres().contains("Horror")) return true;
			backendToTest.removeGenre("Horror");
			if(!backendToTest.getAllGenres().contains("Horror")) return true;
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			// test failed
			return false;
		}
	}

}
