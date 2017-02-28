package io.github.jdl.formserver.exceptions;

/**
 * Created by ddjlo on 27/02/2017.
 */
public class FormServerException extends Exception {

    private final String[] args;

    public FormServerException(String message, String... args) {
        super(message);
        this.args = args;
    }

    public String[] getArgs() {
        return args;
    }
}
