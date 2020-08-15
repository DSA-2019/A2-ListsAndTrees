# Lists and Trees

## Overview

### Lists
In a standard list the index of an item is unknown. A self-organising list improves this by moving frquently accessed items to the beginning of a list. A self-organising list keeps and access counter for each item in the list, and whenever an item is checked to see if it exists within the list it's counter is incremented by 1. Once found, the self-organizing list then repeatedly checks whether that target item has a higher or equal access counter to its neighbour heading towards the front of the list, if the target item does, it bubbles (swaps) position with its neighbour. It may do this several times, potentially even bubbling all the way to the front of the list. BubbleArrayList and BubbleLinkedList are two separate self-organising list classes of a generic type E, implementing the java.util.List<E> interface. Both classes are created from scratch, not extending the ArrayList or LinkedList data structures.
  
#### BubbleArrayList
The BubbleArrayList implements a self-organizing list using an underlying array.

<img src="Lists And Trees/DSA-2019-A2-BubbleArrayList.png" height="400">

#### BubbleLinkedList
The BubbleArrayList implements a doubly linked structure of nodes.

<img src="Lists And Trees/DSA-2019-A2-BubbleLinkedList.png" height="400">

#### DocumentReader
The DocumentReader class has a simple main method which instantiates a self-organizing list using the BubbleArrayList, designed to hold unique strings for word frequency analysis. The class reads the attached text file using streams, extracting each word individually. It should check to see if that word is not already contained in the self-organizing list, before adding it as a new word. The program should recognise both uppercase and lowercase versions of the same word as a sinlge version. The class then prints out a list of words showing the number of occurrences for each word with the most commonly accessed words at the front of the list.

<img src="Lists And Trees/DSA-2019-A2-DocumentReader.png" height="400">

### Trees

#### BETreeGUI

An expression tree is a binary tree representing mathematical expressions built from postfix notation and converting to infix notation. Each internal node in the tree can be an operator (mathematical operations), whereas leaf nodes are operands (numbers or values). An operator can calculate an expression from the output of its left child and right child. A boolean expression tree is a expression tree for boolean logic, comprised of operands {true (T) or false (F)} as well as operators {OR (|), AND (&), NOT(!), XOR(^)}.

##### Blank GUI
<img src="Lists And Trees/DSA-2019-A2-BlankBETree.png" height="400">

This class builds a binary expression tree by altering the given partially complete class BETreeGUI. The class can draw a boolean expression tree, drawing the tree from the root node (if not null) down, from evaluating the post fix boolean expression input into the GUI text field. Once the tree is built the GUI should also has a button to evaluate the tree, outputting the infix notation formula and the resulting boolean output.

##### 3 Node Tree
<img src="Lists And Trees/DSA-2019-A2-3NodeTree.png" height="400"> <img src="Lists And Trees/DSA-2019-A2-3NodeTreeEvaluated.png" height="400">

##### 11 Node Tree
<img src="Lists And Trees/DSA-2019-A2-11NodeTree.png" height="400"> <img src="Lists And Trees/DSA-2019-A2-11NodeTreeEvaluated.png" height="400">

