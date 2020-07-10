package org.pawaneuler.IOTools.Exceptions;

/**
 * Wrong writer for the type exception
 * 
 * @author fauh45
 */
public class WrongTypeWriter extends Exception {
    private static final long serialVersionUID = 1L;

    public WrongTypeWriter(String errorMessage) {
        super(errorMessage);
    }
}
