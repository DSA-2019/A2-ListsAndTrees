/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truefalsetree;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 *
 * @author sehall 
 * modified by 
 * @author gracesubianto 16946441
 */
public class BETreeGUI extends JPanel implements ActionListener {

    private final JButton evaluateButton, buildTreeButton;

    private DrawPanel drawPanel;
    private BoolExpNode root;
    private int numberNodes = 0;

    private JTextField postFixField;
    public static int PANEL_H = 500;
    public static int PANEL_W = 900;
    private JLabel nodeCounterLabel;

    public BETreeGUI() {
        super(new BorderLayout());
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
        root = null;
        super.setPreferredSize(new Dimension(PANEL_W, PANEL_H + 30));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(PANEL_W, 30));
        drawPanel = new DrawPanel();

        evaluateButton = new JButton("Evaluate to infix");
        buildTreeButton = new JButton("Build Expression Tree");

        evaluateButton.addActionListener((ActionListener) this);
        buildTreeButton.addActionListener((ActionListener) this);

        postFixField = new JTextField(40);

        buttonPanel.add(postFixField);
        buttonPanel.add(buildTreeButton);
        buttonPanel.add(evaluateButton);

        super.add(drawPanel, BorderLayout.CENTER);
        super.add(buttonPanel, BorderLayout.SOUTH);

        nodeCounterLabel = new JLabel("Number of Nodes: " + numberNodes);
        super.add(nodeCounterLabel, BorderLayout.NORTH);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();

        if (source == evaluateButton) 
        {
            //finish this button event to handle the evaluation and output to infix of the tree 
            if (root == null) 
            {
                JOptionPane.showMessageDialog(this, "Tree is null, not built", "INFO",
                        JOptionPane.ERROR_MESSAGE);
            } 
            else 
            {
                String infixString = toInfixString(root);

                if (root != null) {
                    JOptionPane.showMessageDialog(this, infixString, "INFO",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (source == buildTreeButton && postFixField.getText() != null) {   //Finish this button event and below methods to handle the building of the tree
            try {
                
                //handles bad input such as lower case letters and unwanted front and end spaces
                root = buildExpressionTree(postFixField.getText().toUpperCase().trim().toCharArray());

                //Update the nodeCounterLabel
                nodeCounterLabel.setText("Number of Nodes: " + numberNodes);
            } catch (ArithmeticException e) {
                JOptionPane.showMessageDialog(this, "Not a valid postfix expression", "INFO",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        drawPanel.repaint();
    }

    /**
     * reads symbols from postfix input expression and builds an expression tree
     *
     * @param postfix
     * @return
     * @throws ArithmeticException
     */
    public BoolExpNode buildExpressionTree(char[] postfix) throws ArithmeticException {
        //create stack S
        Stack stackS = new Stack();
        //create parent Node P
        BoolExpNode parentNodeP = null;
        //set numberNodes to 0
        numberNodes = 0;
        char symbol;

        //for(symbol in expression)
        for (int i = 0; i < postfix.length; i++) 
        {
            symbol = postfix[i];
            //if symbol is an operand T or F
            if (symbol == 'T' || symbol == 'F')
            {
                BoolExpNode operandSymbol = new BoolOperandNode(symbol);
                //push operand onto stack, increment numbernodes
                stackS.push(operandSymbol);
                numberNodes++;
            }
            else
            {
                //set parent node as new operator
                parentNodeP = new BoolOperatorNode(symbol);
                //if the operator is NOT
                if (symbol == '!') 
                {
                    //only pop one node
                    BoolExpNode node = (BoolExpNode)stackS.pop();
                    //if rightchild is null
                    if (parentNodeP.rightChild == null) 
                    {
                        //set node as right child node
                        parentNodeP.rightChild = node;
                    }
                    else
                    {
                        //otherwise set node as leftt child node
                        parentNodeP.leftChild = node;
                    }
                }
                //if symbol is any other operator
                else
                {
                    //pop two nodes off stack and set as left and right child nodes
                    parentNodeP.rightChild = (BoolExpNode)stackS.pop();
                    parentNodeP.leftChild = (BoolExpNode)stackS.pop();
                }
                //push parent node onto stack, increment numbernodes
                stackS.push(parentNodeP);
                numberNodes++;
            }
        }
        //return the parent node
        return parentNodeP;

    }

    /**
     * convert binary expression tree to infix notation using brackets
     *
     * @param node
     * @return
     */
    public String toInfixString(BoolExpNode node) 
    {
        //create empty string s
        String string = "";
        //if(current null)
        if (node == null) {
            //return "" empty string
            return "";
        }
        //if(current symbol is binary operator)
        if (node.symbol == '|' || node.symbol == '&' || node.symbol == '!' || node.symbol == '^') {
            //append "(" to s
            string = string + "(";
        }

        //append toInfixString(left subtree) to s
        string = string + toInfixString(node.leftChild);
        //append current symbol to s
        string = string + node.symbol;
        //WAS THERE A MISTAKE IN THE PSEUDOCODE??
        //append toInfixString(left subtree) to s 
        string = string + toInfixString(node.rightChild);

        //if(current symbol is binary operator)
        if (node.symbol == '|' || node.symbol == '&' || node.symbol == '!' || node.symbol == '^') {
            //append ")" to s
            string = string + ")";
        }
        //evaluates the tree and adds true or false to the end of the infix string
        if(node == root)
        {
            string = string + " = " + root.evaluate();
        }
        return string;
    }

    //DO NOT TOUCH THIS INNER CLASS!!!!!!!!!!!!!
    private class DrawPanel extends JPanel {

        public DrawPanel() {
            super();
            super.setBackground(Color.WHITE);
            super.setPreferredSize(new Dimension(PANEL_W, PANEL_H));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (root != null) {
                drawTree(g, getWidth());
            }
        }

        public void drawTree(Graphics g, int width) {
            int nodeCount = 0 - (numberNodes / 2);
            drawNode(g, root, width / 2, 0, nodeCount, new HashMap<BoolExpNode, Point>());
        }

        private int drawNode(Graphics g, BoolExpNode current,
                int x, int level, int nodeCount, Map<BoolExpNode, Point> map) {
            int RECT = 40;

            if (current.leftChild != null) {
                nodeCount = drawNode(g, current.leftChild, x, level + 1, nodeCount, map);
            }

            int currentX = x + nodeCount * RECT;
            int currentY = level * 2 * RECT + RECT;
            nodeCount++;
            map.put(current, new Point(currentX, currentY));

            if (current.rightChild != null) {
                nodeCount = drawNode(g, current.rightChild, x, level + 1, nodeCount, map);
            }

            g.setColor(Color.red);
            if (current.leftChild != null) {
                Point leftPoint = map.get(current.leftChild);
                g.drawLine(currentX, currentY, leftPoint.x, leftPoint.y - RECT / 2);
            }
            if (current.rightChild != null) {
                Point rightPoint = map.get(current.rightChild);
                g.drawLine(currentX, currentY, rightPoint.x, rightPoint.y - RECT / 2);

            }
            if (current instanceof BoolOperandNode) {
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.YELLOW);
            }

            Point currentPoint = map.get(current);
            g.fillRect(currentPoint.x - RECT / 2, currentPoint.y - RECT / 2, RECT, RECT);
            g.setColor(Color.BLACK);
            g.drawRect(currentPoint.x - RECT / 2, currentPoint.y - RECT / 2, RECT, RECT);
            Font f = new Font("courier new", Font.BOLD, 16);
            g.setFont(f);
            g.drawString(current.toString(), currentPoint.x - 3, currentPoint.y);
            return nodeCount;

        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Boolean Expression Tree builder and evaluator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new BETreeGUI());
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int screenHeight = dimension.height;
        int screenWidth = dimension.width;
        frame.pack();             //resize frame apropriately for its content
        //positions frame in center of screen
        frame.setLocation(new Point((screenWidth / 2) - (frame.getWidth() / 2),
                (screenHeight / 2) - (frame.getHeight() / 2)));
        frame.setVisible(true);
    }
}
