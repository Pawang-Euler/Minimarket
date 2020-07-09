package org.pawaneuler.IOTools.Exceptions;

public class WrongTypeWriter extends Exception {
    private static final long serialVersionUID = 1L;

    public WrongTypeWriter(String errorMessage) {
        super(errorMessage);
    }
}
