/**
 * DHeapMain - Entry point for the D-ary Max Heap program.
 *
 * This class initializes the user interface and starts the interactive loop for
 * performing heap operations.
 */
public class DHeapMain {

	/**
	 * Main method to run the heap program.
	 *
	 * @param args Command-line arguments (not used).
	 */
	public static void main(String[] args) {

		// Create and start the user interface for heap operations
		DHeapUserInterface userInterface = new DHeapUserInterface();
		userInterface.userDataInterface(); // Starts user interaction loop

	}
}
