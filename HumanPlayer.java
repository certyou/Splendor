
/**
 * Décrivez votre classe HumanPlayer ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class HumanPlayer extends Player
{
    private Scanner keyBord = new Scanner(System.in);
    
    public HumanPlayer(int id, String name){
        super(id, name);
    }
    
    public Action chooseAction(Board board) throws IllegalArgumentException{
        int choice;
        System.out.println("Choisissez votre acction:\n -1 : Acheter une carte sur le plateau \n -2 : Prendre 2 jetons ressources de même type\n -3 : Prendre 3 jetons ressources de type différents\n -4 : Passer son tour");
        while(true){
            choice = keyBord.nextInt();
            try{
                if(choice >= 1 && choice <= 4){
                    throw IllegalArgumentException("Choix invalide");
                }
                if(choice == 1){
                    System.out.println("Entrez le numero de la ligne de la carte que vous souhaitez selectioner:");
                }
            }
            catch(InputMismatchException e1){
                System.out.println(e1.getMessage());
            }
            catch(IllegalArgumentException e2){
                System.out.println(e2.getMessage());
            }
        }
    
    }
    
    public Resources chooseDiscardingTokens(int nbTokenToDiscard){
 
    }
}
