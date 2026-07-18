package classes;
public class InvalidAccountException extends Exception{
    public InvalidAccountException(String message){
        super(message);
    }
}