# Lists and Trees

## Overview

### Lists
In a standard list the index of an item is unknown. A self-organising list improves this by moving frquently accessed items to the beginning of a list. A self-organising list keeps and access counter for each item in the list, and whenever an item is checked to see if it exists within the list it's counter is incremented by 1. Once found, the self-organizing list then repeatedly checks whether that target item has a higher or equal access counter to its neighbour heading towards the front of the list, if the target item does, it bubbles (swaps) position with its neighbour. It may do this several times, potentially even bubbling all the way to the front of the list. BubbleArrayList and BubbleLinkedList are two separate self-organising list classes of a generic type E, implementing the java.util.List<E> interface. Both classes are created from scratch, not extending the ArrayList or LinkedList data structures.
  
#### BubbleArrayList
The BubbleArrayList implements a self-organizing list using an underlying array.
<img src="Lists And Trees/DSA-2019-A2-BubbleArrayList.png" height="400">

#### BubbleLinkedList
The BubbleArrayList implements a doubly linked structure of nodes.
//<img src="Lists And Trees/DSA-2019-A2-BubbleLinkedList.png" height="400">

#### DocumentReader
//<img src="Hotplate/DSA-2019-A1-LinkedRRSet.png" height="400">

#### BETreeGUI
This class builds a binary expression tree.

