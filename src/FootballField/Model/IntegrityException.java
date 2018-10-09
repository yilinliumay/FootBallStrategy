package FootballField.Model;

// to avoid there is no player in one or more positions.
public class IntegrityException extends Exception {

    private String msgDes;


    public IntegrityException() {
        super("You miss one or more positions to add player");
        msgDes="You miss one or more positions to add player";
    }

    public String getMsgDes() {
        return msgDes;
    }

}
