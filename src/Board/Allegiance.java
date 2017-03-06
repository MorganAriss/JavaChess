package Board;

public enum Allegiance {
    WHITE
    {
        @Override
        int getDirection() {
            return -1;
        }
        @Override
        String player(){
            return "White";
        }
        @Override
        int team(){
            return 0;
        }
    },
    BLACK
    {
        @Override
        int getDirection() {
            return 1;
        }
        @Override
        String player(){
            return "Black";
        }
        @Override
        int team(){
            return 1;
        }
    };   
    
    abstract int getDirection();
    abstract String player();
    abstract int team();
}