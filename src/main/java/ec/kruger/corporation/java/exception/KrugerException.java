package ec.kruger.corporation.java.exception;

/**
 * @author ${milton.cabrera} on 10/4/2022 12:35
 * @project app-vacunacion
 * @Version 1.0
 **/
public class KrugerException extends RuntimeException {

    public KrugerException() {
    }

    public KrugerException(String message) {
        super(message);
    }
}
