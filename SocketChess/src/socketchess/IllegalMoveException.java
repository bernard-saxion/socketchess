/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketchess;

/**
 *
 * @author saxion
 */
public class IllegalMoveException extends Exception {

    /**
     * Creates a new instance of <code>IllegalMoveException</code> without
     * detail message.
     */
    public IllegalMoveException() {
    }

    /**
     * Constructs an instance of <code>IllegalMoveException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public IllegalMoveException(String msg) {
        super(msg);
    }
}
