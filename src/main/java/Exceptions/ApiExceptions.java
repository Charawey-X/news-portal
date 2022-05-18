package Exceptions;

public class ApiExceptions extends RuntimeException{
    private final int statusCode;

    public ApiExceptions(int statusCode, String msg) {
//        Super tells us to do what we are doing inside our superclass constructor
        super(msg);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}