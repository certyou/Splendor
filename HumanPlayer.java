
/**
 * Décrivez votre classe HumanPlayer ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.InputMismatchException;

public class HumanPlayer extends Player
{
    private Scanner keyBord = new Scanner(System.in);
    
    public HumanPlayer(int id, String name){
        super(id, name);
    }
    
    public Action chooseAction(Board board){
        int choice,i,j;
        String inputMessage, errorMessage;
        
        //Choix de l'acction à effectuer
        int[] validInput = {1,2,3,4};
        inputMessage = "Choisissez votre acction:\n -1 : Acheter une carte sur le plateau \n -2 : Prendre 2 jetons ressources de même type\n -3 : Prendre 3 jetons ressources de type différents\n -4 : Passer son tour";
        errorMessage = "Choix invalide";
        choice = PlayerChoice(inputMessage, errorMessage, validInput);
        
        // Acheter une carte sur le plateau
        if(choice == 1){
            validInput = new int[]{1, 2, 3};
            
            inputMessage = "Entrer la ligne de la carte que vous souhaitez acheter:\n -1 : Tier 1\n -2 : Tier 2\n -3 : Tier 3";
            errorMessage = "Le numero de ligne que vous avez entré n'est pas valide.";
            i = PlayerChoice(inputMessage, errorMessage, validInput);
            
            inputMessage = "Entrer la colone de la carte que vous souhaitez acheter:\n -1 : Colone de gauche\n -2 : Colone du milieu\n -3 : Colone de droite";
            errorMessage = "Le numero de colone que vous avez entré n'est pas valide.";
            j = PlayerChoice(inputMessage, errorMessage, validInput);
            
            return new BuyCardAction(board.getCard(i,j));
        }
        //Acheter deux jetons ressources de même type
        else{if(choice == 2){
            
        }
        //Acheter des jetons ressources de type différents
        else{if(choice == 3){
            
        }
        //Passer son tour
        else{
        return new PassAction();
        }
        }
        }
    }
    
    public Resources chooseDiscardingTokens(int nbTokenToDiscard){
 
    }
    
    private int PlayerChoice(String inputMessage, String errorMessage, int[] tab) throws IllegalArgumentException
    {
        int choice;
        System.out.println(inputMessage);
        while(true){
            try{
                choice = keyBord.nextInt();
                if(!IntStream.of(tab).anyMatch(x -> x == choice)){
                    throw new IllegalArgumentException(errorMessage);
                }
                else{
                    return choice;
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
}
