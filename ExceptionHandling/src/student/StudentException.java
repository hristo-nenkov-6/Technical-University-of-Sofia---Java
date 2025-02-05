package student;

public class StudentException extends Exception {
    private String message;
    public StudentException(String message) {
        super(message);

        setMessage(message);
    }

    private void setMessage(String Message){
        message = Message;
    }

    public String getMessage(){
        return message;
    }
}
