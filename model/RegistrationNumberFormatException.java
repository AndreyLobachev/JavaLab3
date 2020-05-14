package model;

public class RegistrationNumberFormatException extends RuntimeException{
    public RegistrationNumberFormatException(){
        super();
    }
    public RegistrationNumberFormatException(String message){
        super(message);
    }
    public RegistrationNumberFormatException(String message, Throwable throwable){
        super(message,throwable);
    }
}
