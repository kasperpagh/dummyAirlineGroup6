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
public class NoSeatsException extends Exception
{

    /**
     * Creates a new instance of <code>NoSeatsException</code> without detail
     * message.
     */
    public NoSeatsException()
    {
    }

    /**
     * Constructs an instance of <code>NoSeatsException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NoSeatsException(String msg)
    {
        super(msg);
    }
}
