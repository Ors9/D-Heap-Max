
/**
 * DHeapMax - A D-ary Max Heap implementation.
 * 
 * This class implements a max heap where each node has at most 'd' children. It
 * supports insertion, extraction of the maximum element, heapify operations,
 * and dynamic changes in the heap's structure.
 * 
 * The heap is represented as an array, where: - The root node is at index 0. -
 * The parent-child relationship follows: - parent(i) = (i - 1) / d - child(i,
 * k) = d * i + k + 1
 * 
 * The heap property ensures that each parent node is greater than or equal to
 * its children.
 */
public class DHeapMax {

	public static final int HEAP_SIZE = 1000; // Maximum heap capacity
	public static final int ERROR_CODE = -99999; // Special value indicating an error
	public static final int D_MAX_VAL = HEAP_SIZE - 1;// Maximum value of d
	private int[] heap;// Array representation of the heap
	private int size; // Number of elements in the heap
	private int d; // Number of children per node (D-ary)

	/**
	 * Constructs a D-ary Max Heap with a given value of 'd'.
	 * 
	 * @param d The number of children per node (must be greater than 0).
	 * @throws IllegalArgumentException If d <= 0.
	 */
	public DHeapMax(int d) {
		setD(d);
		setSize(0);
		this.heap = new int[HEAP_SIZE];
	}
	


	/** Set D */
	private void setD(int d) {
		if (DHeapValidator.isValidD(d)) {
			this.d = d;
		} else {
			System.out.println(DHeapError.INVALID_NUMBER_RANGE.getMessage());
		}

	}

	/** Returns the current value of 'd'. */
	public int getD() {
		return this.d;
	}

	/** Returns the current size of the heap. */
	public int getSize() {
		return this.size;
	}

	/**
	 * Updates the size of the heap (used for testing). Ensures the size remains
	 * within valid bounds.
	 */
	public void setSize(int newSize) {
		if (newSize < 0 || newSize > HEAP_SIZE) {
			throw new IllegalArgumentException(DHeapError.INVALID_HEAP_SIZE.getMessage());

		}
		this.size = newSize;
	}

	/**
	 * Changes the value of 'd' dynamically and rebuilds the heap. This method
	 * reconstructs the heap to maintain the correct d-ary structure after changing
	 * the number of children per node.
	 * 
	 * The current heap elements are converted into a temporary string array before
	 * rebuilding to ensure consistency.
	 * 
	 * @param newD The new value of 'd'.
	 * @throws IllegalArgumentException If newD is out of valid range.
	 */
	public void changeD(int newD) {

		// Validate newD before proceeding
		if (!DHeapValidator.isValidD(newD)) {
			System.out.println(DHeapError.INVALID_D.getMessage());
			return; // Stop execution if newD is invalid
		}

		this.d = newD; // Only change if valid

		// Convert current heap elements to a String[] array
		String[] heapElements = new String[size];
		for (int i = 0; i < size; i++) {
			heapElements[i] = String.valueOf(heap[i]); // Convert int to String
		}

		buildDHeap(heapElements); // Rebuild heap to maintain the new structure
	}

	/**
	 * Extracts the maximum element (root) from the heap.
	 * 
	 * @return The maximum element or ERROR_CODE if the heap is empty.
	 */
	public int exctractDMax() {
		int max = heap[0]; // Store the max element
		heap[0] = heap[size - 1]; // Replace root with last element
		setSize(size - 1);
		heapifyDown(0); // Restore heap property
		return max;

	}

	/**
	 * Prints the heap structure in a hierarchical format.
	 * 
	 * The heap is printed level by level, with \n separating different
	 * depth levels in the heap. The calculation of `nextlvl` determines when a new
	 * level starts based on the power of `d`.
	 */
	public void printHeap() {
	    if (DHeapValidator.isEmptyHeap(this.size)) {
	        System.out.println(DHeapError.EMPTY_HEAP.getMessage());
	        return;
	    }
	    int nodesThisLevel = 1;// expected nodes in current level (1 for lvl 0)
	    int count = 0;   // counter for nodes printed on the current level

	    System.out.println("d-ary Heap: ");
	    for (int i = 0; i < size; i++) {
	    	System.out.print(heap[i] + " ");
	        count++;
	        // When done print all the nodes require in this lvl
	        if (count == nodesThisLevel) {
	            System.out.println(); // move to next line
	            count = 0;           // reset counter
	            nodesThisLevel *= d; // next level should have d times more nodes

	        }
	    }
	    System.out.println();
	}


	/**
	 * Sets a specific element in the heap array at a given index.
	 * 
	 * This method allows direct modification of an element in the heap. It ensures
	 * that the index is within valid bounds before updating the value.
	 * 
	 * @param index The index in the heap array to modify.
	 * @param value The new value to assign to the specified index.
	 */
	private void setHeapElement(int index, int value) {
		if (index >= 0 && index < HEAP_SIZE) {
			heap[index] = value;// Ensure index is within bounds
		}
	}

	/**
	 * Returns the index of the parent node.
	 * 
	 * @param i Index of the child node.
	 * @return Parent index.
	 */
	private int parent(int i) {
		return (i - 1) / this.d;
	}

	/**
	 * Returns the index of the k-th child of a node.
	 * 
	 * @param i Index of the parent node.
	 * @param k Child number (0-based).
	 * @return Index of the child or ERROR_CODE if out of bounds.
	 */
	private int child(int i, int k) {
		int index = this.d * i + k + 1;
		return (index < size) ? index : ERROR_CODE;
	}

	/**
	 * Restores the max-heap property by moving an element down the tree. Ensures
	 * that the node at index 'i' is greater than its children.
	 * 
	 * @param i Index of the node to heapify down.
	 */
	private void heapifyDown(int i) {

		int maxIndex = i; // Assume the current node is the largest

		// Find the largest child
		for (int k = 0; k < d; k++) {
			int childIndex = child(i, k);
			if (childIndex != ERROR_CODE && heap[childIndex] > heap[maxIndex]) {
				maxIndex = childIndex;
			}

		}

		// Swap if necessary and continue heapifying
		if (maxIndex != i) {
			swap(i, maxIndex);
			heapifyDown(maxIndex);
		}

	}

	/**
	 * Inserts a new key into the heap and restores the heap property.
	 * 
	 * The key is initially placed at the next available position in the heap, and
	 * the heap is restored by moving the key up the tree if necessary.
	 * 
	 * @param key The value to insert into the heap.
	 * @throws RuntimeException If the heap reaches its maximum capacity.
	 */
	public void insert(int key) {
		if (!DHeapValidator.canInsert(size)) {
			throw new IllegalArgumentException(DHeapError.HEAP_OVERFLOW.getMessage());
		}

		heap[size] = key; // Insert key at Last position
		heapifyUp(size);// Restore heap property
		setSize(size + 1);

	}

	/**
	 * Moves an element up the tree to restore the heap property.
	 * 
	 * @param i Index of the inserted element.
	 */
	private void heapifyUp(int i) {
		while (i > 0 && heap[parent(i)] < heap[i]) {
			swap(i, parent(i));// Swap
			i = parent(i); // Move up to the parent's index
		}
	}

	/**
	 * Converts an unordered array of string representations of numbers into a max
	 * heap.
	 * 
	 * This method: 1. Parses the given string values into integers. 2. Stores them
	 * in the heap array. 3. Restores the heap property using a bottom-up heapify
	 * process.
	 * 
	 * @param parts An array of string representations of integer values.
	 */
	public void buildDHeap(String[] parts) {
		// Parse the value to integers
		for (int i = 0; i < parts.length; i++) {
			if (i < HEAP_SIZE && parts != null) {
				setHeapElement(i, Integer.parseInt(parts[i]));
			}
		}

		// Update heap size
		setSize(parts.length);

		// Start heapifying from the last non-leaf node down to the root
		for (int i = parent(size - 1); i >= 0; i--) {
			heapifyDown(i);
		}
	}

	/** Swaps two elements in the heap array. */
	private void swap(int i, int j) {
		int temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
	}

}
