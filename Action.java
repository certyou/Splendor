public interface Action
{
    //Interface qui gère les actions des joueurs 
    
    void process(Player player, Board board) throws IllegalArgumentException;
    
    String toString();
}
