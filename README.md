# D-ary Max Heap (Java Implementation)

This project implements a **D-ary Max Heap** in Java. Unlike a binary heap (where each node has 2 children), this version supports any number `d` of children per node (as long as `d >= 1`). The heap supports core operations such as insertion, extraction of the max, and dynamic change of the heap's branching factor (`d`).

## 📁 Project Structure

- `DHeapMax.java` – Core heap logic (insert, extract, build, heapify, change `d`, etc.).
- `DHeapValidator.java` – Handles input validation, range checking, and error messages.
- `DHeapUserInterface.java` – Command-line user interface to interact with the heap.
- `DHeapMain.java` – Entry point (`main` method) that starts the interface.

##📋 Menu Operations

1 - buildHeap                  (Insert multiple values into the heap)
2 - changeD                    (Change the value of d dynamically)
3 - ExtractMax                 (Remove and print the largest value)
4 - Insert value to the heap   (Insert a single number)
5 - Print Heap                 (Display current heap structure)
6 - Exit Program               (Quit)


##💡General Example

Please enter value of d
> 3

The menu of operation are:
1 - buildHeap
2 - changeD
3 - ExtractMax
4 - Insert value to the heap
5 - Print Heap
6 - Exit Program
> 1
Please enter values to the heap:
> 9 12 3 7 5
d-ary Heap:
12
9 3 7
5

##Build Heap Example:
Please enter value of d
> 2

The menu of operation are:
1 - buildHeap
...
> 1
Please enter values to the heap:
> 10 30 20 5 3
d-ary Heap:
30
10 20
5 3


##Insert into Heap

> 4
Enter value to insert:
> 50
d-ary Heap:
50
10 30
5 3 20

##Change D
> 2
Please enter value of d
> 3

d-ary Heap:
50
30 20 10
5 3

##Extract Max
> 3
The max is exctracted and the value is 50

d-ary Heap:
30
10 20
5 3

