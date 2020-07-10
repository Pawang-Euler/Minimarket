package org.pawaneuler.IOTools.Exceptions;

/**
 * Extension Exception 
 * 
 * @author fauh45
 */
public class BadExtentionException extends Exception{
    private static final long serialVersionUID = 1L;

    public BadExtentionException(String errorMessage) {
        super(errorMessage);
    }

    public BadExtentionException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}