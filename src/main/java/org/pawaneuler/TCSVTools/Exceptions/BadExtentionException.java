package org.pawaneuler.TCSVTools.Exceptions;

public class BadExtentionException extends Exception{
    private static final long serialVersionUID = 1L;

    public BadExtentionException(String errorMessage) {
        super(errorMessage);
    }

    public BadExtentionException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}