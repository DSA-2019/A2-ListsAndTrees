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
public class BoolOperatorNode extends BoolExpNode
{
    /**
     * class constructor
     * @param symbol 
     */
    public BoolOperatorNode(char symbol) 
    {
        super(symbol);
    }    
    
    /**
     * Logic gate methods
     * @return true or false depending on operators and the result of the recursive evaluations
     */
    @Override
    //0 = false
    //1 = true
    public boolean evaluate() 
    {
        boolean result = false;
        // || Logical OR "A or B"
        if(symbol == '|')
        { 
            if (!leftChild.evaluate() && !rightChild.evaluate()) //F|F = F
            {
                result = false;
            }
            else if (!leftChild.evaluate() && rightChild.evaluate()) //F|T = T
            {
                result = true;
            }
            else if (leftChild.evaluate() && !rightChild.evaluate()) //T|F = T
            {
                result = true;
            }
            else if (leftChild.evaluate() && rightChild.evaluate()) //T|T = T
            {
                result = true;
            }       
        }
        // && Logical AND "A and B"
        else if (symbol == '&')
        {
            if (leftChild.evaluate() && rightChild.evaluate()) //T&T = T
            {
                result = true;
            }
            else if (leftChild.evaluate() && !rightChild.evaluate()) //T&F = F
            {
                result = false;
            }
            else if (!leftChild.evaluate() && rightChild.evaluate()) //F&T = F
            {
                result = false;
            }
            else if (!leftChild.evaluate() && !rightChild.evaluate()) //F&F = F
            {
                result = false;
            } 
        }
        // !  Logical NOT "Invert State"
        else if (symbol == '!')
        {
            if (rightChild.evaluate()) //!T = F
            {
                result = false;
            }
            else if (!rightChild.evaluate()) //!F = T
            {
                result = true;
            }
            
        }
        // ^  Logical XOR "A or B but not both"
        //a true output results if one, and only one, of the inputs to the gate is true. 
        //If both inputs are false (0/LOW) or both are true, a false output results
        else if (symbol == '^')
        {
            if (leftChild.evaluate() && rightChild.evaluate()) //T^T = F
            {
                result = false;
            }
            else if (leftChild.evaluate() && !rightChild.evaluate()) //T^F = T
            {
                result = true;
            }
            else if (!leftChild.evaluate() && rightChild.evaluate()) //F^T = T
            {
                result = true;
            }
            else if (!leftChild.evaluate() && !rightChild.evaluate()) //F^F = F
            {
                result = false;
            }   
        }
        return result;  
    }
}
