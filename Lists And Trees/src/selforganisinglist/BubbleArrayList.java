/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selforganisinglist;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 *
 * @author gracesubianto 16946441
 */
public class BubbleArrayList<E> implements List<E> {

    //Initialized variables
    private E[] list;
    private int[] accessCounter;
    private int numElements;
    private final int initialListSize = 5;
    private final int initialCounterSize = 5;

    /**
     * Array class constructor
     */
    public BubbleArrayList() {
        super();
        this.list = (E[]) new Object[initialListSize];
        this.accessCounter = new int[initialCounterSize];
        this.numElements = 0;
    }

    /**
     * Bubble sort logic method
     * @return accessCounter
     */
    public int[] bubbleSort() {
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < accessCounter.length - 1; j++) {
                if (accessCounter[i] > accessCounter[j]) {
                    int counterSwap = accessCounter[i];
                    accessCounter[i] = accessCounter[j];
                    accessCounter[j] = counterSwap;

                    E elementSwap = list[i];
                    list[i] = list[j];
                    list[j] = elementSwap;
                }
            }
        }
        return accessCounter;
    }

    /**
     * size method to determine how many elements are in the array
     *
     * @return numElements
     */
    @Override
    public int size() {
        System.out.println("Array Size: " + numElements);
        return numElements;
    }

    /**
     * isEmpty method to check if the array is empty
     *
     * @return true if empty, false if not
     */
    @Override
    public boolean isEmpty() {
        if (numElements == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks to see if object is present in array
     *
     * @param o
     * @return true if present, false if not
     */
    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] == o) {
                accessCounter[i]++;
                System.out.println("Element " + o + " has been incremented");
                //System.out.println(Arrays.toString(accessCounter));
                return true;
            }

        }
        System.out.println("Element " + o + " was not found in the array");
        return false;
    }

    /**
     * Calls the ArrayIterator method
     *
     * @return the method call
     */
    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator<>(list, numElements);
    }

    /**
     * Creates a new array out of the existing elements
     *
     * @return the new array
     */
    @Override
    public Object[] toArray() {
        E[] newBubbleArray = (E[]) (new Object[numElements]);

        if (numElements == 0) {
            newBubbleArray = null;
            return newBubbleArray;
        }

        for (int i = 0; i < numElements; i++) {
            newBubbleArray[i] = list[i];
        }
        return newBubbleArray;
    }

    /**
     * gets an array which contains all the elements in ArrayList object in
     * proper sequence (from first to last element)
     *
     * @param <T>
     * @param a
     * @return array a
     */
    @Override
    public <T> T[] toArray(T[] a) {

        if (numElements == 0) {
            a = null;
            return a;
        }

        for (int i = 0; i < numElements; i++) {
            if (a.length > i) {
                a[i] = (T) list[i];
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
        if (numElements >= list.length) {
            expandCapacity();
//                    accessCounter[numElements]++;
//                    list[numElements++] = e;
//                    return true;
        }
        for (int i = 0; i < list.length; i++) {

            if ((list[i] != null) && (list[i].equals(e))) {
                list[i] = e;
                accessCounter[i]++;
                bubbleSort();
                return false;
            }
            if (list[i] == null) {
                list[i] = e;
                accessCounter[i]++;
                numElements++;
                return true;
            }

        }
        return false;
    }

    /**
     * removes element object from list
     *
     * @param o
     * @return true if removed, false if not
     */
    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] == o) {
                list[i] = null;
                accessCounter[i] = 0;
            }
        }
        System.out.println("Letter " + o + " is removed!");
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
        //contains all can just call contains for each element
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

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * gets the index of the elements
     *
     * @param index
     * @return index of the elements
     */
    @Override
    public E get(int index) {
        if (list[index] != null) {
            System.out.println(list[index]);
        }
        return list[index];
    }

    //DO NOT FILL
    @Override
    public E set(int index, E element) {
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

        if (list[index] != element && list[index] == null) {
            list[index] = element;
        } else {
            System.out.println("There is already an element at that index!");
        }
    }
    
    /**
     * removes element from a specific index
     *
     * @param index
     * @return null
     */
    @Override
    public E remove(int index) {
        for (int i = 0; i < list.length; i++) {
            if (list[index] != null) {
                list[index] = null;
                accessCounter[index] = 0;
            }
        }
        System.out.println("Letter at position " + index + " is removed!");
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
    public ListIterator<E> listIterator() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //return (ListIterator<E>) new ArrayIterator(int pointMe);
        //return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex)
    {
        List<E> subArray = new BubbleArrayList();
        int numCounter = 0;
        
        if(fromIndex < 0 || toIndex > numElements-1 || toIndex < fromIndex)
        {
            return subArray;
        }
        
        for(int i = 0; i < numElements; i++)
        {
            if(numCounter >= fromIndex && numCounter <= toIndex)
            {
                subArray.add(list[i]);
            }
            numCounter++;
        }
        return subArray;
    }

    /**
     * inner class which represents an iterator for an array taken from ArraySet
     * chapter file and modified
     *
     * @param <E>
     */
    private class ArrayIterator<E> implements Iterator<E> {

        private int numElements; // number of elements in array to use
        private E[] elements; // the array to use
        private int nextIndex; // index of next element for the iterator
        public int pointMe = 0;
        
        public ArrayIterator(int pointMe)
        {
            this.pointMe = pointMe;
        }

        // constructor which accepts an array of elements
        // and prepares an iterator which will iterate through the
        // first numElements elements of the array
        public ArrayIterator(E[] elements, int numElements) {
            if (numElements > elements.length) {
                numElements = elements.length;
            }
            this.numElements = numElements;
            this.elements = elements;
            nextIndex = 0; // start with index of first element in array
        }

        // returns whether there is still another element
        public boolean hasNext() {
            return (nextIndex < numElements);
        }

        // returns the next element or throws a NoSuchElementException
        // it there are no further elements
        public E next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return elements[nextIndex++];
        }
    }

    /**
     * toString method for printing out the array
     *
     * @return the string
     */
    public String toString() {
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) {
                System.out.print(list[i] + "(" + accessCounter[i] + ") ");
            }
        }
        System.out.println("\nArray is printed");
        System.out.println("");
        return "";
    }

    /**
     * modified from ArrayStack class helper method which doubles the current
     * size of the array
     */
    private void expandCapacity() {
        E[] largerList = (E[]) (new Object[list.length * 2]);
        // copy the elements array to the largerArray
        for (int i = 0; i < numElements; i++) {
            largerList[i] = list[i];
        }
        list = largerList; //save the new capacity

        int[] largerIndex = (new int[accessCounter.length * 2]);
        // copy the elements array to the largerArray
        for (int i = 0; i < accessCounter.length; i++) {
            largerIndex[i] = accessCounter[i];
        }
        accessCounter = largerIndex;
    }

    /**
     * main driver method to test out array functionalities
     *
     * @param args
     */
    public static void main(String[] args) {
        BubbleArrayList<String> bubblelist = new BubbleArrayList<>();
        bubblelist.add("A");
        bubblelist.add("B");
        bubblelist.add("C");
        bubblelist.add("D");
        bubblelist.add("E");
        bubblelist.add("F");
        bubblelist.add("G");
        //System.out.print("Original List: ");
        bubblelist.bubbleSort();
        bubblelist.toString();

        bubblelist.contains("B");
        bubblelist.contains("B");
        bubblelist.contains("E");
        bubblelist.contains("Y");
        bubblelist.bubbleSort();
        bubblelist.toString();

        bubblelist.remove("A");
        bubblelist.bubbleSort();
        bubblelist.toString();
    }

}
