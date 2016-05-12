/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author kaspe
 */
public class IllegalDataException extends Exception
{

    /**
     * Creates a new instance of <code>IllegalDataException</code> without
     * detail message.
     */
    public IllegalDataException()
    {
    }

    /**
     * Constructs an instance of <code>IllegalDataException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public IllegalDataException(String msg)
    {
        super(msg);
        
    }
}
