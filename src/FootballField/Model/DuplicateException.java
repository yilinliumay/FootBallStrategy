package FootballField.Model;

// to avoid one player is placed in multiple positions.
public class DuplicateException extends Exception {

    private String msgDes;


    public DuplicateException() {
        super();
    }

    public DuplicateException (Player player) {
        super("You can not put "+player.getName()+" in more than one positions");
        msgDes="You can not put "+player.getName()+" in more than one positions";
    }

    public String getMsgDes() {
        return msgDes;
    }

}
