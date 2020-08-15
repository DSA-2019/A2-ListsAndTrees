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
public class BoolOperandNode extends BoolExpNode
{
    /**
     * class constructor
     * @param symbol 
     */
    public BoolOperandNode(char symbol) 
    {
        super(symbol);
    }

    /**
     * evaluates operands T and F
     * @return true if T, false if F
     */
    @Override
    public boolean evaluate() 
    {
        if(symbol == 'T')
        {
            return true;
        }
        else if (symbol == 'F')
        {
            return false;
        }
        return false;
    }
}