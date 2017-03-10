package Chess;

import java.io.Serializable;

public class Player implements Serializable {

    private static final long serialVersionUID = 1L;
    private final String color;
    private final int team;
    
    public Player(String color, int team){
        this.color = color;
        this.team = team;
    }
    
    public int team(){
        return this.team;
    }
    
    public String color(){
        return this.color;
    }

}
