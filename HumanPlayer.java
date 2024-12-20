
/**
 * Décrivez votre classe HumanPlayer ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.InputMismatchException;

/**
 * Classe HumanPlayer représentant un joueur humain.
 * Hérite de la classe abstraite Player et permet au joueur humain de choisir ses actions via une interface console.
 */
public class HumanPlayer extends Player
{
    private Scanner keyBord = new Scanner(Game.display.in);
    private Board board;
    
    /**
     * Constructeur de la classe HumanPlayer.
     * Initialise un joueur humain avec un identifiant, un nom et une référence au plateau de jeu.
     *
     * parametre: id,    L'identifiant unique du joueur.
     * parametre: name,  Le nom du joueur.
     * parametre: board, Une instance du plateau de jeu (Board).
     */
    public HumanPlayer(int id, String name,Board board){
        super(id, name);
        this.board = board;
    }
    
    /**
     * Permet au joueur humain de choisir une action à effectuer parmi :
     * - Acheter une carte de développement.
     * - Prendre 2 jetons ressources de même type.
     * - Prendre 3 jetons ressources de types différents.
     * - Passer son tour.
     *
     * return: Une instance de la classe Action représentant l'action choisie par le joueur.
     * throws: IllegalArgumentException Si l'entrée utilisateur est invalide.
     */
    public Action chooseAction() throws IllegalArgumentException
    {
        int choice,i,j,cmp = 0;
        Resource resource;
        Resource[] resTab = {Resource.DIAMOND, Resource.SAPPHIRE, Resource.EMERALD, Resource.ONYX, Resource.RUBY};
        Resource[] recourceTabRec = new Resource[3];
        Resources resourcesRec = new Resources(0,0,0,0,0);
        String inputMessage, errorMessage;
        
        Game.display.out.println("\n\n==== Tour de " + super.getName() + " ====");
        
        //Choix de l'acction à effectuer
        int[] validInput = {1,2,3,4};
        inputMessage = "\nChoisissez votre action:\n -1 : Acheter une carte sur le plateau \n -2 : Prendre 2 jetons ressources de même type\n -3 : Prendre 3 jetons ressources de type différents\n -4 : Passer son tour";
        errorMessage = "Choix invalide";
        choice = PlayerChoice(inputMessage, errorMessage, validInput);
        
        // Acheter une carte sur le plateau
        if(choice == 1){
            validInput = new int[]{1, 2, 3, 4};
            
            while(true){
                inputMessage = "\nEntrer la ligne de la carte que vous souhaitez acheter:\n -1 : Tier 1\n -2 : Tier 2\n -3 : Tier 3";
                errorMessage = "Le numero de ligne que vous avez entré n'est pas valide.";
                i = PlayerChoice(inputMessage, errorMessage, validInput);
                
                inputMessage = "\nEntrer la colone de la carte que vous souhaitez acheter:\n -1 : Colone 1\n -2 : Colone 2\n -3 : Colone 3\n -4 : Colone 4";
                errorMessage = "Le numero de colone que vous avez entré n'est pas valide.";
                j = PlayerChoice(inputMessage, errorMessage, validInput);
                Game.display.out.println(board.getCard(3-i,j-1));
                if(canBuyCard(board.getCard(3-i,j-1))){
                    Game.display.out.println("\nVous avez acheté la carte suivante : " + board.getCard(3-i,j-1));
                    return new BuyCardAction(board.getCard(3-i,j-1));
                }
                else{
                    Game.display.out.println("Choix invalide: vous n'avez pas assez de ressource pour acheter cette carte.");
                    return chooseAction();
                }
            }
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
                    Game.display.out.println("\nVous récupéré les deux ressources suivantes : " + resource);
                    return new PickSameTokensAction(resource);
                }
                else{
                    Game.display.out.println("Choix invalide: vous avez choisit une ressource qui n'a plus assez de stock.");
                    return chooseAction();
                }
            }
        }
        //Acheter des jetons ressources de type différents
        else{if(choice == 3){
            validInput = new int[]{1, 2, 3, 4, 5};
            inputMessage = "\nEntrer le numero de la ressource que vous souhaitez récupérer:\n -1 : DIAMANT \u2666\n -2 : SAPHIR \u2660\n -3 : EMERAUDE \u2663\n -4 : ONYX \u25CF\n -5 : RUBIS \u2665";
            errorMessage = "Le numero de la ressource que vous avez entré n'est pas valide.";
            cmp = 0;
            while(true){
                choice = PlayerChoice(inputMessage, errorMessage, validInput);
                resource = resTab[choice-1];
                if(board.getNbResource(resource) > 0 && resourcesRec.getNbResource(resource) == 0){
                    recourceTabRec[cmp] = resource;
                    resourcesRec.updateNbResource(resource, 1);
                    cmp ++;
                    if(cmp == 3){
                        Game.display.out.println("\nVous récupéré les trois ressources suivantes : " + recourceTabRec[0] + ", " + recourceTabRec[1] + " et " + recourceTabRec[2]);
                        return new PickDiffTokensAction(recourceTabRec[0],recourceTabRec[1],recourceTabRec[2]);
                    }
                }
                else{
                    Game.display.out.println("Choix invalide: vous avez choisit une ressource déjà selectionné ou qui n'est plus en stock.");
                    return chooseAction();
                }
            }
        }
        }
        }
        //Passer son tour
        return new PassAction();
    }
    
    /**
     * Permet au joueur de choisir les jetons à défausser lorsqu'il dépasse la limite de 10 jetons.
     *
     * paramètre: nbTokenToDiscard, Le nombre de jetons à défausser.
     * return: Une instance de Resources représentant les jetons défaussés.
     * throws: IllegalArgumentException Si l'entrée utilisateur est invalide.
     */
    public Resources chooseDiscardingTokens(int nbTokenToDiscard) throws IllegalArgumentException{
        int choice;
        String inputMessage, errorMessage;
        Resource[] resTab = {Resource.DIAMOND, Resource.SAPPHIRE, Resource.EMERALD, Resource.ONYX, Resource.RUBY};
        Resources resourcesRec = new Resources(0,0,0,0,0);
        Resource resource;
        
        int[] validInput = {1, 2, 3, 4, 5};
        inputMessage = "\nVous avez plus de 10 jetons, entrer le numero de la ressource dont vous voulez vous débarasser:\n -1 : DIAMANT \u2666\n -2 : SAPHIR \u2660\n -3 : EMERAUDE \u2663\n -4 : ONYX \u25CF\n -5 : RUBIS \u2665";
        errorMessage = "Le numero de la ressource que vous avez entré n'est pas valide.";
        
        while(nbTokenToDiscard != 0){
            choice = PlayerChoice(inputMessage, errorMessage, validInput);
            
            resource = resTab[choice-1];
            if(super.getNbResource(resource) - resourcesRec.getNbResource(resource) <= 0){
                Game.display.out.println("Choix invalide: vous avez choisit une ressource que vous n'avez plus en stock.");
            }
            else{
                resourcesRec.updateNbResource(resource, 1);
                nbTokenToDiscard --;
            }
        }
        return resourcesRec;
    }
    
    /**
     * Méthode utilitaire pour gérer les choix du joueur.
     * Affiche un message au joueur et valide que l'entrée utilisateur seulement si le choix est valide.
     *
     * paramètre: inputMessage,  Le message affiché à l'utilisateur.
     *            errorMessage,  Le message d'erreur en cas d'entrée invalide.
     *            tab,           Un tableau des choix valides sous forme d'entiers.
     * return: L'entrée utilisateur valide, sous forme d'entier.
     * throws: IllegalArgumentException Si l'entrée utilisateur est invalide.
     */
    private int PlayerChoice(String inputMessage, String errorMessage, int[] tab) throws IllegalArgumentException
    {
        Game.display.out.println(inputMessage);
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
                Game.display.out.println("Entrée invalide");
                keyBord.nextLine(); 
            }
            catch(IllegalArgumentException e2){
                Game.display.out.println(e2.getMessage());
            }
        }
    }
}
