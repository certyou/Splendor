
/**
 * Décrivez votre classe HumanPlayer ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
import java.util.Scanner;
import java.util.ArrayList;

public class HumanPlayer extends Player
{
    private Scanner keyBord = new Scanner(System.in);
    
    public HumanPlayer(int id, String name){
        super(id, name);
    }
    
    public Action chooseAction(Board board){
        int choice;
        System.out.println("Choisissez votre acction:\n -1 : Acheter une carte sur le plateau \n -2 : Prendre 2 jetons ressources de même type\n -3 : Prendre 3 jetons ressources de type différents\n -4 : Passer son tour");
        while(true){
            choice = keyBord.nextInt();
            
            if(choice >= 1 && choice <= 4){
                if(
            }
        }
        
        
        
    
    }
    
    public Resources chooseDiscardingTokens(int nbTokenToDiscard){
 
    }
}
