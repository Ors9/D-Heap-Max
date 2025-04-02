import java.util.Scanner;

/**
 * Enum representing standardized error messages used in the D-ary heap system.
 * Each enum constant has an associated descriptive error message.
 */
enum DHeapError {
	// General Errors
	INVALID_D("d must be between 1 and " + DHeapMax.D_MAX_VAL),
	INVALID_NUMBER_RANGE("The range is invalid 4 digit whole number only "),
	INVALID_INPUT("Input must be a list of integers"), EMPTY_LINE("Input cannot be empty"),
	NON_INTEGER_INPUT("Only integer whole values are allowed "),

	// Heap Operation Errors
	HEAP_OVERFLOW("Heap Overflow: no more space in the heap"), HEAP_UNDERFLOW("Heap Underflow: the heap is empty"),
	INVALID_HEAP_SIZE("Heap size is invalid"),

	// Menu and UI Errors
	INVALID_MENU_CHOICE("Invalid choice! You must choose whole number between 1-6!"), EMPTY_HEAP("The Heap is empty");

	private final String message;

	/**
	 * Constructor for error enum.
	 * 
	 * @param message The error message string to be associated with the error.
	 */
	DHeapError(String message) {
		this.message = message;
	}

	/**
	 * Returns the error message.
	 * 
	 * @return the string message.
	 */
	public String getMessage() {
		return message;
	}
}

/**
 * Utility class for validating user input and heap operations for a D-ary Max
 * Heap. Handles all error-checking logic.
 */
public class DHeapValidator {
	// Range limits for valid numeric input
	public static final int MIN_VALID_NUMBER = -9999;
	public static final int MAX_VALID_NUMBER = 9999;

	/**
	 * Prompts user to enter a valid value for 'd', continues until valid input.
	 * 
	 * @param scan Scanner object for reading input.
	 * @return A valid value of d.
	 */
	public static int promptValidD(Scanner scan) {
		int d = DHeapMax.ERROR_CODE;

		// Continue till i get valid input
		while (d == DHeapMax.ERROR_CODE) {
			System.out.println("Please enter value of d");

			// Try parsing and validating user input
			d = tryParseInt(scan.nextLine().trim());

			// If d isnt valid tell the user the error and tell him to insert new input
			if (!isValidD(d) && d != DHeapMax.ERROR_CODE) {
				System.out.println(DHeapError.INVALID_D.getMessage());
				d = DHeapMax.ERROR_CODE;
			}
		}

		return d;
	}

	/**
	 * Attempts to parse a string to an integer.
	 * 
	 * @param input The input string.
	 * @return Parsed integer or ERROR_CODE if parsing fails.
	 */
	public static int tryParseInt(String input) {
		try {
			return Integer.parseInt(input);

		} catch (NumberFormatException e) {
			System.out.println(DHeapError.NON_INTEGER_INPUT.getMessage());
			return DHeapMax.ERROR_CODE;
		}
	}

	/**
	 * Validates the user's menu operation selection. Loops until the input is a
	 * valid integer.
	 * 
	 * @param scan Scanner object for input.
	 * @return Valid integer input.
	 */
	public static int validateUserOperation(Scanner scan) {

		int userInt = DHeapMax.ERROR_CODE;

		// Loop till the user insert integer
		while (userInt == DHeapMax.ERROR_CODE) {
			System.out.println("Please enter Your Oparation!");
			userInt = tryParseInt(scan.nextLine().trim());
			if (userInt == DHeapMax.ERROR_CODE) {
				DHeapUserInterface.printUserOperation();
			}

		}

		return userInt;

	}

	/**
	 * Validates number inserted to the heap. Ensures the number is both a valid
	 * integer and in allowed range.
	 * 
	 * @param scan Scanner object for input.
	 * @return A valid number to insert.
	 */
	public static int checkInsertToHeapValid(Scanner scan) {
		int userInt = DHeapMax.ERROR_CODE;

		// Continue till the user insert valid input
		while (userInt == DHeapMax.ERROR_CODE) {
			System.out.println("Please enter Your number to insert!");
			userInt = tryParseInt(scan.nextLine().trim());
			// Check if number is with max 4DIGIT
			if (!isValidNumberRange(userInt) && userInt != DHeapMax.ERROR_CODE) {
				System.out.println(DHeapError.INVALID_NUMBER_RANGE.getMessage());
				userInt = DHeapMax.ERROR_CODE;
			}

		}
		return userInt;
	}

	/**
	 * Checks whether the number is within the valid range.
	 * 
	 * @param num The number to check.
	 * @return true if within range, false otherwise.
	 */
	public static boolean isValidNumberRange(int num) {
		return num >= MIN_VALID_NUMBER && num <= MAX_VALID_NUMBER;
	}

	/**
	 * Checks if extraction is possible (heap is not empty).
	 * 
	 * @param size Current heap size.
	 * @return true if can extract, false if heap is empty.
	 */
	public static boolean canExtract(int size) {
		return size > 0;
	}

	/**
	 * Validates value of d.
	 * 
	 * @param d Value to validate.
	 * @return true if in valid range.
	 */
	public static boolean isValidD(int d) {
		return d >= 1 && d <= DHeapMax.D_MAX_VAL;
	}

	/**
	 * Checks if there is space to insert a new value.
	 * 
	 * @param size Current heap size.
	 * @return true if there is space.
	 */
	public static boolean canInsert(int size) {
		return size < DHeapMax.HEAP_SIZE;
	}

	/**
	 * Checks if the heap is empty.
	 * 
	 * @param size Current heap size.
	 * @return true if heap is empty.
	 */
	public static boolean isEmptyHeap(int size) {
		return size == 0;
	}

	/**
	 * Checks if the given line is empty or null.
	 * 
	 * @param line Line to check.
	 * @return true if line is empty/null.
	 */
	public static boolean isEmptyLine(String line) {
		return (line == null || line.trim().isEmpty());

	}

	/**
	 * Checks if the given number of elements can be used to build a heap without
	 * exceeding the maximum allowed heap size.
	 *
	 * @param numberOfElements The number of elements to insert into the heap.
	 * @return true if the heap can be built with the given number of elements;
	 *         false otherwise.
	 */
	public static boolean canBuildHeap(int numberOfElements) {
		return numberOfElements <= DHeapMax.HEAP_SIZE;
	}

	/**
	 * Validates the entire input array used for building the heap. Checks that all
	 * values are valid integers and within range.
	 * 
	 * @param parts String array of input values.
	 * @return true if all values are valid.
	 */
	public static boolean checkHeapInput(String[] parts) {
		int userInt = 0;
		boolean result = true;

		// Check if there too many elements
		if (!canBuildHeap(parts.length)) {
			System.out.println("Too many elements. Max heap size is " + DHeapMax.HEAP_SIZE);
			return false;
		}

		// Iritate on all the String elements of the array
		for (int i = 0; i < parts.length; i++) {
			// Try parse each String element
			userInt = tryParseInt(parts[i]);

			// If parsing failed but not due to ERROR_CODE string
			if (userInt == DHeapMax.ERROR_CODE && !parts[i].equals(String.valueOf(DHeapMax.ERROR_CODE))) {
				System.out.println("The issue: " + parts[i] + " " + DHeapError.INVALID_INPUT.getMessage());
				result = false;
			}

			// Check range after parsing
			else if (!isValidNumberRange(userInt) && userInt != DHeapMax.ERROR_CODE) {
				System.out.println("The issue: " + parts[i] + " " + DHeapError.INVALID_NUMBER_RANGE.getMessage());
				result = false;
			}

		}
		return result;
	}

}
