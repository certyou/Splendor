
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
    Board board;
    
    public HumanPlayer(int id, String name,Board board){
        super(id, name);
        this.board = board;
    }
    
    public Action chooseAction() throws IllegalArgumentException
    {
        int choice,i,j,cmp = 0;
        Resource resource;
        Resource[] resTab = {Resource.DIAMOND, Resource.SAPPHIRE, Resource.EMERALD, Resource.ONYX, Resource.RUBY};
        Resource[] recourceTabRec = new Resource[3];
        Resources resourcesRec = new Resources(0,0,0,0,0);
        String inputMessage, errorMessage;
        
        //Choix de l'acction à effectuer
        int[] validInput = {1,2,3,4};
        inputMessage = "\nChoisissez votre acction:\n -1 : Acheter une carte sur le plateau \n -2 : Prendre 2 jetons ressources de même type\n -3 : Prendre 3 jetons ressources de type différents\n -4 : Passer son tour";
        errorMessage = "Choix invalide";
        choice = PlayerChoice(inputMessage, errorMessage, validInput);
        
        // Acheter une carte sur le plateau
        if(choice == 1){
            validInput = new int[]{1, 2, 3};
            
            inputMessage = "\nEntrer la ligne de la carte que vous souhaitez acheter:\n -1 : Tier 1\n -2 : Tier 2\n -3 : Tier 3";
            errorMessage = "Le numero de ligne que vous avez entré n'est pas valide.";
            i = PlayerChoice(inputMessage, errorMessage, validInput);
            
            inputMessage = "\nEntrer la colone de la carte que vous souhaitez acheter:\n -1 : Colone de gauche\n -2 : Colone du milieu\n -3 : Colone de droite";
            errorMessage = "Le numero de colone que vous avez entré n'est pas valide.";
            j = PlayerChoice(inputMessage, errorMessage, validInput);
            
            return new BuyCardAction(board.getCard(i-1,j-1));
        }
        //Acheter deux jetons ressources de même type
        else{if(choice == 2){
            validInput = new int[]{1, 2, 3, 4, 5};
            inputMessage = "\nEntrer le numero de la ressource que vous souhaitez récupérer:\n -1 : DIAMANT \u2666\n -2 : SAPHIR \u2660\n -3 : EMERAUDE \u2663\n -4 : ONYX \u25CF\n -5 : RUBIS \u2665";
            errorMessage = "Le numero de la ressource que vous avez entré n'est pas valide.";
            while(true){
                choice = PlayerChoice(inputMessage, errorMessage, validInput);
                resource = resTab[choice-1];
                if(board.getNbResource(resource) >= 4 ){
                    return new PickSameTokensAction(resource);
                }
                else{
                    System.out.println("Choix invalide: vous avez choisit qui n'a plus assez de stock.");
                }
            }
        }
        //Acheter des jetons ressources de type différents
        else{if(choice == 3){
            validInput = new int[]{1, 2, 3, 4, 5};
            inputMessage = "\nEntrer le numero de la ressource que vous souhaitez récupérer:\n -1 : DIAMANT \u2666\n -2 : SAPHIR \u2660\n -3 : EMERAUDE \u2663\n -4 : ONYX \u25CF\n -5 : RUBIS \u2665";
            errorMessage = "Le numero de la ressource que vous avez entré n'est pas valide.";
            while(true){
                choice = PlayerChoice(inputMessage, errorMessage, validInput);
                resource = resTab[choice-1];
                if(board.getNbResource(resource) > 0 && resourcesRec.getNbResource(resource) == 0){
                    recourceTabRec[cmp] = resource;
                    resourcesRec.updateNbResource(resource, 1);
                    cmp ++;
                    if(cmp == 2){
                        return new PickDiffTokensAction(recourceTabRec[0],recourceTabRec[1],recourceTabRec[2]);
                    }
                }
                else{
                    System.out.println("Choix invalide: vous avez choisit une ressource déjà selectionné ou qui n'est plus en stock.");
                }
            }
        }
        }
        }
        //Passer son tour
        return new PassAction();
    }
    
    public Resources chooseDiscardingTokens(int nbTokenToDiscard) throws IllegalArgumentException{
        int choice;
        String inputMessage, errorMessage;
        Resource[] resTab = {Resource.DIAMOND, Resource.SAPPHIRE, Resource.EMERALD, Resource.ONYX, Resource.RUBY};
        Resources resourcesRec = new Resources(0,0,0,0,0);
        Resource resource;
        
        int[] validInput = {1, 2, 3, 4, 5};
        inputMessage = "\nEntrer le numero de la ressource que vous souhaitez récupérer:\n -1 : DIAMANT \u2666\n -2 : SAPHIR \u2660\n -3 : EMERAUDE \u2663\n -4 : ONYX \u25CF\n -5 : RUBIS \u2665";
        errorMessage = "Le numero de la ressource que vous avez entré n'est pas valide.";
        
        while(nbTokenToDiscard != 0){
            choice = PlayerChoice(inputMessage, errorMessage, validInput);
            resource = resTab[choice-1];
            if(super.getNbResource(resource) - resourcesRec.getNbResource(resource) <= 0){
                System.out.println("Choix invalide: vous avez choisit une ressource que vous n'avez plus en stock.");
            }
            else{
                resourcesRec.updateNbResource(resource, 1);
                nbTokenToDiscard --;
            }
        }
        return resourcesRec;
    }
    
    public int PlayerChoice(String inputMessage, String errorMessage, int[] tab) throws IllegalArgumentException
    {
        System.out.println(inputMessage);
        while(true){
            try{
                int choice;
                choice = keyBord.nextInt();
                if(!IntStream.of(tab).anyMatch(x -> x == choice)){
                    throw new IllegalArgumentException(errorMessage);
                }
                else{
                    return choice;
                }
            }         
            catch(InputMismatchException e1){
                System.out.println("Entrée invalide");
                keyBord.nextLine(); 
            }
            catch(IllegalArgumentException e2){
                System.out.println(e2.getMessage());
            }
        }
    }
}
