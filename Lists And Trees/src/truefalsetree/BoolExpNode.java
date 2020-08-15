/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truefalsetree;

/**
 *
 * @author gracesubianto 16946441
 */
public abstract class BoolExpNode 
{
    //Initialized variables
    protected char symbol;
    public BoolExpNode leftChild;
    public BoolExpNode rightChild;
    
    /**
     * constructor for class
     * @param symbol 
     */
    public BoolExpNode(char symbol)
    {
        this.symbol = symbol;
        this.leftChild = null;
        this.rightChild = null;
    }
    
    /**
     * abstract evaluate method to be over ridden in subclasses
     * @return 
     */
    public abstract boolean evaluate();

    /**
     * toString method to display the symbol
     * @return 
     */
    public String toString()
    {
        return Character.toString(symbol);
    }  
}
