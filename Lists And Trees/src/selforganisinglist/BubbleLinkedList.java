/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selforganisinglist;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 *
 * @author gracesubianto 16946441
 */
public class BubbleLinkedList<E> implements List<E> 
{

    //Initialised variables
    private Node<E> begin;
    private Node<E> end;
    private int numElements;

    /**
     * LinkedList class constructor
     */
    public BubbleLinkedList() 
    {
        super();
        this.begin = null;
        this.end = null;
        this.numElements = 1;
    }

    /**
     * Protected inner class which represents a node in the linked list
     */
    protected class Node<E> 
    {
        
        public E element;
        public Node<E> next;
        public Node<E> previous;
        public int accessCounter;
        
        public Node() {
            this.next = null;
            this.previous = null;
            this.element = null;
            this.accessCounter = 1;
        }

        public Node(E element, Node<E> next, Node<E> previous) {

            this.next = next;
            this.previous = previous;
            this.element = element;
            this.accessCounter = 1;
        }

        //getters and setters for easy access
        public void setNextNode(Node nextNode) {
            this.next = nextNode;
        }
        public void setPreviousNode(Node previousNode) {
            this.previous = previousNode;
        }
        public Node getNextNode() {
            return this.next;
        }
        public Node getPreviousNode() {
            return this.previous;
        }
        public int getAccessCounter() {
            return this.accessCounter;
        }
    }

    /**
     * Bubble sort logic method
     * @param currentNode
     * @return currentNode
     */
    public Node<E> bubbleSort(Node currentNode) 
    {
        int swapped;
        Node<E> pointMe1;
        Node<E> pointMe2 = null;

        if (currentNode == null) {
            return null;
        }
        do {
            swapped = 0;
            pointMe1 = currentNode;
            while (pointMe1.next != pointMe1 && pointMe1.next != null) {
                if (pointMe1.accessCounter > pointMe1.next.accessCounter) {
                    int temp = pointMe1.accessCounter;
                    pointMe1.accessCounter = pointMe1.next.accessCounter;
                    pointMe1.next.accessCounter = temp;
                    swapped = 1;
                }
                pointMe1 = pointMe1.next;
            }
            pointMe2 = pointMe1;
        } while (swapped != 0);
        return currentNode;
    }

    /**
     * size method to determine how many elements are in the list
     *
     * @return numElements
     */
    @Override
    public int size() {
        System.out.println("List Size: " + numElements);
        return numElements;
    }

    /**
     * isEmpty method to check if the list is empty
     *
     * @return true if empty, false if not
     */
    @Override
    public boolean isEmpty() {
        if (begin == null) {
            return true;
        } else {
            return false;
        }
    }

        /**
     * Checks to see if object is present in list
     *
     * @param o
     * @return true if present, false if not
     */
    @Override
    public boolean contains(Object o) {
        Node<E> currentNode = begin;

        for (int i = 0; i < numElements; i++) {
            if (currentNode != null) {
                if (currentNode.element == (E) o) {

                    currentNode.accessCounter++;
                    System.out.println("Element " + o + " has been incremented");
                    Node<E> getElement = currentNode;
                    getElement.element = currentNode.element;
                    currentNode = bubbleSort(currentNode);
                    return true;
                }
                currentNode = currentNode.getNextNode();
            }
        }
        System.out.println("Element " + o + " was not found in the list");
        return false;
    }

    /**
     * Calls the ArrayIterator method
     *
     * @return the method call
     */
    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator<E>(begin);
    }

    /**
     * Creates a new array out of the existing elements
     *
     * @return the new array
     */
    @Override
    public Object[] toArray() {
        Object[] newListArray = new Object[numElements];
        Node<E> currentNode = begin;
        int counterN = 0;

        while (currentNode != null) {
            newListArray[counterN] = currentNode.element;
            currentNode = currentNode.next;
            counterN++;
        }
        return newListArray;
    }

    /**
     * gets an array which contains all the elements in LinkedList object in
     * proper sequence (from first to last element)
     *
     * @param <T>
     * @param a
     * @return array a
     */
    @Override
    public <T> T[] toArray(T[] a) {
        Node<E> currentNode = begin;

        if (numElements == 0) {
            a = null;
        } else {
            for (int i = 0; i < numElements; i++) {
                if (a.length > i) {
                    a[i] = (T) currentNode.element;
                    currentNode = currentNode.next;
                }

            }
        }
        return a;
    }

    /**
     * adds element to list
     *
     * @param e
     * @return true if added, false if not
     */
    @Override
    public boolean add(E e) {
        Node<E> newNode = new Node<E>(e, null, null);
        Node<E> currentNode = begin;

        if (begin == null) {
            begin = newNode;
            end = begin;
            return true;
        }

        //Checks the beginning of the linkedList if greater than the new
        //element thats being added
        if (newNode.accessCounter > begin.accessCounter) {
            addAtStart(e);
            return true;
        }
        addAtEnd(e);

        for (int i = 0; i < numElements; i++) {
            if (currentNode.next != null && (currentNode.accessCounter > currentNode.next.accessCounter)) {
                Node<E> tempNode = currentNode.getNextNode();
                currentNode.setNextNode(newNode);
                newNode.setPreviousNode(currentNode);
                newNode.setNextNode(tempNode);
                tempNode.setPreviousNode(newNode);
                numElements++;
                newNode.accessCounter++;
            }
            currentNode = currentNode.getNextNode();
        }
        return true;
    }

    /**
     * method to add element to start of list
     * @param e 
     */
    public void addAtStart(E e) {
        Node<E> newNode = new Node<E>(e, null, null);
        if (begin == null) {
            begin = newNode;
            end = begin;
        } else {
            begin.setPreviousNode(newNode);
            newNode.setNextNode(begin);
            begin = newNode;
        }
        numElements++;
    }

    /**
     * method to add element to end of list
     * @param e 
     */
    public void addAtEnd(E e) {
        Node<E> newNode = new Node<E>(e, null, null);
        if (begin == null) {
            begin = newNode;
            end = begin;
        } else {
            newNode.setPreviousNode(end);
            end.setNextNode(newNode);
            end = newNode;
        }
        numElements++;
    }

    /**
     * removes element object from list
     *
     * @param o
     * @return true if removed, false if not
     */
    @Override
    public boolean remove(Object o) {
        //pointer for currentNode
        Node<E> currentNode = begin.getNextNode();
        for (int i = 0; i < numElements - 1; i++) //Iterates through the LinkedList
        {
            if (currentNode.element == o) {
                //saves the previousNode and the nextNode
                Node<E> previous = currentNode.getPreviousNode();
                Node<E> next = currentNode.getNextNode();

                //saves the removed Element
                Node<E> removedElement = currentNode;
                removedElement.element = currentNode.element;

                //assigns the the previousNode and nextNode
                previous.setNextNode(next);
                next.setPreviousNode(previous);

                //set currentNode to null
                currentNode.accessCounter = 0;
                currentNode.element = null;

                numElements--;
                return true;
            }
            currentNode = currentNode.getNextNode();
        }
        return false;

    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * clears the entire list
     */
    @Override
    public void clear() {
        begin = null;
        end = null;
        numElements = 0;
    }

    /**
     * gets the index of the elements
     * @param index
     * @return 
     */
    @Override
    public E get(int index) {
        Node<E> currentNode = begin;
        for (int i = 0; i < numElements; i++) {
            if (i == index) {
                Node<E> getElement = currentNode;
                getElement.element = currentNode.element;
                currentNode.accessCounter++;
                return currentNode.element;
            }
            currentNode = currentNode.getNextNode();
        }
        System.out.println("Doesn't exist!");
        return null;
    }

    //DO NOT FILL
    @Override
    public E set(int index, E element) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * adds element to a specific index
     *
     * @param index
     * @param element
     */
    @Override
    public void add(int index, E element) {
        Node<E> newNode = new Node<E>(element, null, null);
        if (index == 1) {
            addAtStart(element);
            return;
        }

        Node<E> currentNode = begin;
        for (int i = 0; i < numElements; i++) {
            if (i == index) {
                Node<E> tempNode = currentNode.getNextNode();
                currentNode.setNextNode(newNode);
                newNode.setPreviousNode(currentNode);
                newNode.setNextNode(tempNode);
                tempNode.setPreviousNode(newNode);
                numElements++;
            }
            currentNode = currentNode.getNextNode();
        }
        newNode.accessCounter++;
    }

    /**
     * removes element from a specific index
     *
     * @param index
     * @return null
     */
    @Override
    public E remove(int index) {
        //If element exist at the start
        if (index == 1) {
            if (numElements == 1) {
                Node<E> removedElement = begin;
                removedElement.element = begin.element;
                begin = null;
                end = null;
                numElements = 0;
                return removedElement.element;
            }

            Node<E> removedElement = begin;
            removedElement.element = begin.element;
            begin = this.begin.getNextNode();
            begin.setPreviousNode(null);
            numElements--;
            return removedElement.element;
        }

        //if element exist at the end
        if (index == numElements) {
            Node<E> removedElement = end;
            removedElement.element = end.element;
            end = end.getPreviousNode();
            end.setNextNode(null);
            numElements--;
            return removedElement.element;
        }

        //pointer for currentNode
        Node<E> currentNode = begin.getNextNode();
        for (int i = 0; i < numElements - 1; i++) //Iterates through the LinkedList
        {
            if (i == index) {
                //saves the previousNode and the nextNode
                Node<E> previous = currentNode.getPreviousNode();
                Node<E> next = currentNode.getNextNode();

                //saves the removed Element
                Node<E> removedElement = currentNode;
                removedElement.element = currentNode.element;

                //assigns the the previousNode and nextNode
                previous.setNextNode(next);
                next.setPreviousNode(previous);
                numElements--;
                return removedElement.element;
            }
            currentNode = currentNode.getNextNode();
        }
        return null;
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * inner class which represents an iterator for a singly linked list taken from LinkedSet
     * chapter file and modified
     *
     * @param <E>
     */
    private class LinkedListIterator<E> implements Iterator<E> {

        private Node<E> nextNode; // next node to use for the iterator
        private Node<E> previousNode; // previous node to use for the iterator

        // constructor which accepts a reference to first node in list
        // and prepares an iterator which will iterate through the
        // entire linked list
        public LinkedListIterator(Node<E> firstNode) {
            this.nextNode = firstNode; // start with first node in list
        }

        // returns true or false if element exist next to the currentNode
        public boolean hasNext() {
            return (this.nextNode != null);
        }

        //returns true of false if element exist previous to the currentNode
        public boolean hasPrevious() {
            return (this.previousNode != null);
        }

        // returns the next element or throws a NoSuchElementException
        // it there are no further elements
        public E next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E element = this.nextNode.element;
            this.nextNode = nextNode.next;
            return element;
        }

        public E previous() throws NoSuchElementException {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }

            E element = this.previousNode.element;
            this.previousNode = this.previousNode.next;
            return element;
        }
    }

    /**
     * toString method for printing out the list
     *
     * @return the string
     */
    public String toString() 
    {
        Node<E> currentNode = begin;
        for (int i = 0; i < numElements; i++) 
        {
            System.out.print(currentNode.element + "(" + currentNode.accessCounter + ") ");
            if (currentNode.next != null) {
                currentNode = currentNode.next;
            }
        }
        System.out.println("\nList is printed");
        System.out.println("");
        return "";
    }

    /**
     * main driver method to test out list functionalities
     *
     * @param args
     */
    public static void main(String[] args) {
        BubbleLinkedList<String> linkedlist = new BubbleLinkedList<>();
        linkedlist.add("A");
        linkedlist.add("B");
        linkedlist.add("C");
        linkedlist.add("D");
        linkedlist.add("E");
        linkedlist.add("F");
        linkedlist.add("G");
        System.out.println("Original List: ");
        //linkedlist.bubbleSort(currentNode);
        linkedlist.toString();

        linkedlist.contains("B");
        linkedlist.contains("A");
        linkedlist.contains("E");
        linkedlist.contains("Y");
        linkedlist.toString();

        System.out.println();
        System.out.println("Altered List: ");
        //System.out.println("List: " + linkedlist.size());
        linkedlist.toString();
    }
}
