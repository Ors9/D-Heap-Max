import java.util.Scanner;

/**
 * DHeapUserInterface - A user interface for interacting with a D-ary Max Heap.
 * 
 * This class provides a command-line interface for users to: - Build a heap
 * from user input. - Change the value of 'd' dynamically. - Extract the maximum
 * element from the heap. - Insert new values into the heap. - Print the heap
 * structure. - Exit the program.
 * 
 * The class interacts with the DHeapMax class to perform heap operations.
 */
public class DHeapUserInterface {
	private DHeapMax heap; // Instance of the D-ary max heap
	private Scanner scan;

	// Define constants for user choices
	private static final int BUILD_HEAP = 1;
	private static final int CHANGE_D = 2;
	private static final int EXTRACT_MAX = 3;
	private static final int INSERT = 4;
	private static final int PRINT_HEAP = 5;
	private static final int EXIT = 6;
	private static final int MIN_MENU_OPTION = 1;
	private static final int MAX_MENU_OPTION = 6;

	/**
	 * Constructs the user interface and initializes the heap.
	 * 
	 * The user is prompted to enter the initial value of 'd' before any operations
	 * are performed.
	 */
	public DHeapUserInterface() {
		this.scan = new Scanner(System.in);
		int d = DHeapValidator.promptValidD(scan);
		this.heap = new DHeapMax(d);

	}

	/**
	 * Main loop for user interaction.
	 * 
	 * This method continuously prompts the user for operations until they choose to
	 * exit.
	 */
	public void userDataInterface() {

		int userChoose = 0;
		printUserOperation();
		// Exit when the user choose exit
		while (userChoose != EXIT) {
			// validate the number to int
			userChoose = DHeapValidator.validateUserOperation(this.scan);// Display menu to the user
			switch (userChoose) {
			case BUILD_HEAP:
				userChooseBuildHeap();
				break;
			case CHANGE_D:
				userChooseChangeD();
				break;
			case EXTRACT_MAX:
				userChooseExtractMax();
				break;
			case INSERT:
				userChooseInsertToHeap();
				break;
			case PRINT_HEAP:
				break;// ill print anyway at end
			case EXIT:
				System.out.println("Exit program");
				return;// exit
			default:
				System.out.println(DHeapError.INVALID_MENU_CHOICE.getMessage());
				break;// user choose invalid number

			}
			if (userChoose >= MIN_MENU_OPTION && userChoose <= MAX_MENU_OPTION) {
				heap.printHeap();// Print heap after any operation
			}
			printUserOperation();
		}

		scan.close();
	}

	/**
	 * Prints the available user operations for heap management.
	 */
	public static void printUserOperation() {
		System.out.println("The menu of operation are:");
		System.out.println("1 - buildHeap");
		System.out.println("2 - changeD");
		System.out.println("3 - ExtractMax");
		System.out.println("4 - Insert value to the heap");
		System.out.println("5 - Print Heap");
		System.out.println("6 - Exit Program");
	}

	/**
	 * Prompts the user to input a list of values to build a D-ary Max Heap.
	 *
	 * This method repeatedly asks the user to enter a space-separated list of values,
	 * validates the input using helper methods from DHeapValidator, and if valid,
	 * builds the heap and updates its size accordingly.
	 *
	 * The loop continues until the user provides valid input.
	 * 
	 * Expected input format: space-separated integers (e.g., "5 10 3 8")
	 */
	private void userChooseBuildHeap() {

		boolean inputIsValid  = false;

		//Continue till we got valid input and build the heap
		while (!inputIsValid ) {
			System.out.println("Please enter values to the heap:");
			System.out.println("Example: 1 2 3 4 .... 23");
			String userInput  = scan.nextLine().trim();
			if (!DHeapValidator.isEmptyLine(userInput )) {
				String[] inputValues = userInput .split("\\s+");

				if (DHeapValidator.checkHeapInput(inputValues)) {

					heap.buildDHeap(inputValues);
					heap.setSize(inputValues.length); // Set the correct size
					inputIsValid  = true;
				} else {
					System.out.println("Heap was not built due to input errors.");
				}

			}

		}
	}

	/**
	 * Handles the operation of changing the value of 'd' dynamically.
	 * 
	 * The user is prompted to enter a new value for 'd', and the heap is rebuilt
	 * accordingly.
	 */
	private void userChooseChangeD() {
		heap.changeD(DHeapValidator.promptValidD(scan));

	}

	/**
	 * Handles the extraction of the maximum element from the heap.
	 * 
	 * The extracted max value is displayed to the user.
	 */
	private void userChooseExtractMax() {
		if (DHeapValidator.canExtract(heap.getSize())) {
			System.out.println("The max is exctracted and the value is " + heap.exctractDMax());
		}

	}

	/**
	 * Handles inserting a new value into the heap.
	 * 
	 * The user is prompted to enter a value, which is then inserted into the heap.
	 */
	private void userChooseInsertToHeap() {
		System.out.println("Enter value to insert:");
		int input = DHeapValidator.checkInsertToHeapValid(scan);
		if (input != DHeapMax.ERROR_CODE) {
			heap.insert(input);
		}

	}

}
