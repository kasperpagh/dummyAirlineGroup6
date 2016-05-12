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
public class NoFlightFoundException extends Exception
{

    /**
     * Creates a new instance of <code>NoFlightFoundException</code> without
     * detail message.
     */
    public NoFlightFoundException()
    {
    }

    /**
     * Constructs an instance of <code>NoFlightFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NoFlightFoundException(String msg)
    {
        super(msg);
    }
}
